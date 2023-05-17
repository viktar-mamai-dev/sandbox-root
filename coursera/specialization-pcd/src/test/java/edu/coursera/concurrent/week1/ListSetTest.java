package edu.coursera.concurrent.week1;

import edu.coursera.Util;
import edu.coursera.concurrent.week1.seq.RandomSequenceGenerator;
import edu.coursera.concurrent.week1.seq.RepeatingSequenceGenerator;
import edu.coursera.concurrent.week1.seq.ReversedSequenceGenerator;
import edu.coursera.concurrent.week1.seq.SequenceGenerator;
import edu.coursera.concurrent.week1.threads.AddTestThread;
import edu.coursera.concurrent.week1.threads.ContainsTestThread;
import edu.coursera.concurrent.week1.threads.RemoveTestThread;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;
import junit.framework.TestCase;

public class ListSetTest extends TestCase {
  private static final int nCores = Util.getNCores();
  private final int randNumsLength = 10_000;
  private final int randNumRange = 80_000;

  private static void printStats(
      TestResults ref, TestResults test, final SequenceGenerator seq, final String datasetName) {
    System.out.println("=========================================================");
    System.out.println(
        test.lbl + " vs. " + ref.lbl + " (" + datasetName + " " + seq.getLabel() + ")");
    System.out.println("=========================================================");
    System.out.println("# threads = " + nCores);
    System.out.println(
        (test.addRate / ref.addRate)
            + "x improvement in add throughput ("
            + ref.addRate
            + " -> "
            + test.addRate
            + ")");
    System.out.println(
        (test.containsRate / ref.containsRate)
            + "x improvement in contains throughput ("
            + ref.containsRate
            + " -> "
            + test.containsRate
            + ")");
    System.out.println(
        (test.removeRate / ref.removeRate)
            + "x improvement in remove throughput ("
            + ref.removeRate
            + " -> "
            + test.removeRate
            + ")");
    System.out.println("=========================================================");
  }

  private static TestResultsPair runKernel(
      final ListFactory factoryA,
      final String lblA,
      final ListFactory factoryB,
      final String lblB,
      final SequenceGenerator addSeq,
      final SequenceGenerator containsSeq,
      final SequenceGenerator removeSeq)
      throws InterruptedException {
    final int numThreads = nCores;

    /*
     * Require several warm-ups to ensure JIT does not interfere with thread
     * scheduling. We interleave runs of the baseline and the implementation
     * we're testing to try to make any interference on Coursera's
     * autograding platform the same across both.
     */
    TestResults resultsA = null;
    TestResults resultsB = null;
    for (int r = 0; r < 5; r++) {
      addSeq.reset();
      containsSeq.reset();
      removeSeq.reset();

      TestResults newResults =
          mainKernel(numThreads, factoryA.construct(), lblA, addSeq, containsSeq, removeSeq);
      if (resultsA == null || newResults.containsRate > resultsA.containsRate) {
        resultsA = newResults;
      }

      addSeq.reset();
      containsSeq.reset();
      removeSeq.reset();

      newResults =
          mainKernel(numThreads, factoryB.construct(), lblB, addSeq, containsSeq, removeSeq);
      if (resultsB == null || newResults.containsRate > resultsB.containsRate) {
        resultsB = newResults;
      }
    }

    return new TestResultsPair(resultsA, resultsB);
  }

  private static long launchAndJoinAll(final Runnable[] runners) throws InterruptedException {
    final AtomicLong elapsedTime = new AtomicLong(0);
    CyclicBarrier barrier = new CyclicBarrier(runners.length);
    Thread[] threads = new Thread[runners.length];

    for (int t = 0; t < threads.length; t++) {
      final int tid = t;

      threads[t] =
          new Thread(
              () -> {
                try {
                  barrier.await();
                } catch (InterruptedException | BrokenBarrierException ie) {
                  throw new RuntimeException(ie);
                }

                final long startTime = System.currentTimeMillis();
                runners[tid].run();
                final long endTime = System.currentTimeMillis();

                elapsedTime.addAndGet(endTime - startTime);
              });
      threads[t].setPriority(Thread.MAX_PRIORITY);
      threads[t].start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

    return elapsedTime.get();
  }

  private static TestResults mainKernel(
      final int numThreads,
      final ListSet list,
      final String lbl,
      final SequenceGenerator addSeq,
      final SequenceGenerator containsSeq,
      final SequenceGenerator removeSeq)
      throws InterruptedException {

    AddTestThread[] addRunnables = new AddTestThread[numThreads];
    ContainsTestThread[] containsRunnables = new ContainsTestThread[numThreads];
    RemoveTestThread[] removeRunnables = new RemoveTestThread[numThreads];

    // Test add bandwidth
    for (int t = 0; t < numThreads; t++) {
      addRunnables[t] = new AddTestThread(addSeq, addSeq.sequenceLength() / numThreads, list);
    }
    final long addTime = launchAndJoinAll(addRunnables);

    // request GC
    requestGarbageCollection();

    /*
     * Assert that the resulting list is sorted and save the length of the list after the initial adds.
     */
    int listLengthAfterAdds = 1;
    Entry prev = list.getHead();
    Entry curr = prev.next;
    while (curr != null) {
      assertTrue(
          "List was not sorted, index "
              + (listLengthAfterAdds - 1)
              + " is "
              + prev.object
              + " and index "
              + listLengthAfterAdds
              + " is "
              + curr.object,
          curr.object > prev.object);

      prev = curr;
      curr = curr.next;
      listLengthAfterAdds++;
    }

    // Test contains bandwidth
    for (int t = 0; t < numThreads; t++) {
      containsRunnables[t] =
          new ContainsTestThread(containsSeq, containsSeq.sequenceLength() / numThreads, list);
    }
    final long containsTime = launchAndJoinAll(containsRunnables);

    // request GC
    requestGarbageCollection();

    // Record the total number of successes and failures
    int totalContainsSuccesses = 0;
    int totalContainsFailures = 0;
    for (int t = 0; t < numThreads; t++) {
      totalContainsSuccesses += containsRunnables[t].getNSuccessful();
      totalContainsFailures += containsRunnables[t].getNFailed();
    }

    // Test remove bandwidth
    for (int t = 0; t < numThreads; t++) {
      removeRunnables[t] =
          new RemoveTestThread(removeSeq, removeSeq.sequenceLength() / numThreads, list);
    }
    final long removeTime = launchAndJoinAll(removeRunnables);

    // request GC
    requestGarbageCollection();

    /*
     * Verify list is still sorted and record length, successes, and
     * failures.
     */
    int listLengthAfterRemoves = 1;
    prev = list.getHead();
    curr = prev.next;
    while (curr != null) {
      assertTrue("List was not sorted", curr.object > prev.object);

      prev = curr;
      curr = curr.next;
      listLengthAfterRemoves++;
    }

    int totalRemovesSuccesses = 0;
    int totalRemovesFailures = 0;
    for (int t = 0; t < numThreads; t++) {
      totalRemovesSuccesses += removeRunnables[t].getNSuccessful();
      totalRemovesFailures += removeRunnables[t].getNFailed();
    }

    // Ops per second
    final double addRate = (double) (numThreads * addSeq.sequenceLength()) / (double) addTime;
    final double containsRate =
        (double) (numThreads * containsSeq.sequenceLength()) / (double) containsTime;
    final double removeRate =
        (double) (numThreads * removeSeq.sequenceLength()) / (double) removeTime;
    return new TestResults(
        lbl,
        addRate,
        containsRate,
        removeRate,
        listLengthAfterAdds,
        totalContainsSuccesses,
        totalContainsFailures,
        listLengthAfterRemoves,
        totalRemovesSuccesses,
        totalRemovesFailures);
  }

  private static void requestGarbageCollection() {
    tryGarbageCollection();
    tryGarbageCollection();
  }

  private static void tryGarbageCollection() {
    System.gc();
    for (int t = 0; t < 10_000; t++) {
      Math.random();
    }
  }

  public void testCoarseGrainedLockingRandomLarge() throws InterruptedException {
    final SequenceGenerator addSeq =
        new RandomSequenceGenerator(0, nCores * randNumsLength, randNumRange);
    final SequenceGenerator containsSeq =
        new RandomSequenceGenerator(1, nCores * randNumsLength, randNumRange);
    final SequenceGenerator removeSeq =
        new ReversedSequenceGenerator(
            new RandomSequenceGenerator(2, nCores * randNumsLength, randNumRange));

    final double expectedAdd = 0.5;
    final double expectedContains = 0.7;
    final double expectedRemove = 0.7;

    assertImprovementStats(
        new ImprovementStats(expectedAdd, expectedContains, expectedRemove),
        testCoarseGrainedLockingHelper(addSeq, containsSeq, removeSeq, "Large"));
  }

  public void testCoarseGrainedLockingRepeatingLarge() throws InterruptedException {
    final SequenceGenerator addSeq =
        new RepeatingSequenceGenerator(nCores * 6 * randNumsLength, randNumsLength);
    final SequenceGenerator containsSeq =
        new RepeatingSequenceGenerator(nCores * 6 * randNumsLength, randNumsLength);
    final SequenceGenerator removeSeq =
        new ReversedSequenceGenerator(
            new RepeatingSequenceGenerator(nCores * 6 * randNumsLength, randNumsLength));

    final double expectedAdd = 0.5;
    final double expectedContains = 0.6;
    final double expectedRemove = 0.6;

    assertImprovementStats(
        new ImprovementStats(expectedAdd, expectedContains, expectedRemove),
        testCoarseGrainedLockingHelper(addSeq, containsSeq, removeSeq, "Large"));
  }

  public void testReadWriteLocksRandomLarge() throws InterruptedException {
    final SequenceGenerator addSeq =
        new RandomSequenceGenerator(0, nCores * randNumsLength, randNumRange);
    final SequenceGenerator containsSeq =
        new RandomSequenceGenerator(1, nCores * randNumsLength, randNumRange);
    final SequenceGenerator removeSeq =
        new ReversedSequenceGenerator(
            new RandomSequenceGenerator(2, nCores * randNumsLength, randNumRange));

    final double expectedAdd = 0.5;
    final double expectedRemove = 0.5;
    final double expectedContains = 1.8;

    assertImprovementStats(
        new ImprovementStats(expectedAdd, expectedContains, expectedRemove),
        testReadWriteLocksHelper(addSeq, containsSeq, removeSeq, "Large"));
  }

  public void testReadWriteLocksRandomSmall() throws InterruptedException {
    final SequenceGenerator addSeq =
        new RandomSequenceGenerator(0, nCores * randNumsLength / 2, randNumRange);
    final SequenceGenerator containsSeq =
        new RandomSequenceGenerator(1, nCores * randNumsLength / 2, randNumRange);
    final SequenceGenerator removeSeq =
        new ReversedSequenceGenerator(
            new RandomSequenceGenerator(2, nCores * randNumsLength / 2, randNumRange));

    final double expectedAdd = 0.5;
    final double expectedRemove = 0.5;
    final double expectedContains = 1.8;

    assertImprovementStats(
        new ImprovementStats(expectedAdd, expectedContains, expectedRemove),
        testReadWriteLocksHelper(addSeq, containsSeq, removeSeq, "Small"));
  }

  public void testReadWriteLocksRepeatingLarge() throws InterruptedException {
    final SequenceGenerator addSeq =
        new RepeatingSequenceGenerator(nCores * 6 * randNumsLength, randNumsLength);
    final SequenceGenerator containsSeq =
        new RepeatingSequenceGenerator(nCores * 6 * randNumsLength, randNumsLength);
    final SequenceGenerator removeSeq =
        new ReversedSequenceGenerator(
            new RepeatingSequenceGenerator(nCores * 6 * randNumsLength, randNumsLength));

    final double expectedAdd = 0.5;
    final double expectedRemove = 0.5;
    final double expectedContains = 1.8;

    assertImprovementStats(
        new ImprovementStats(expectedAdd, expectedContains, expectedRemove),
        testReadWriteLocksHelper(addSeq, containsSeq, removeSeq, "Large"));
  }

  public void testReadWriteLocksRepeatingSmall() throws InterruptedException {
    final SequenceGenerator addSeq =
        new RepeatingSequenceGenerator(nCores * 3 * randNumsLength, randNumsLength);
    final SequenceGenerator containsSeq =
        new RepeatingSequenceGenerator(nCores * 3 * randNumsLength, randNumsLength);
    final SequenceGenerator removeSeq =
        new ReversedSequenceGenerator(
            new RepeatingSequenceGenerator(nCores * 3 * randNumsLength, randNumsLength));

    final double expectedAdd = 0.4;
    final double expectedRemove = 0.4;
    final double expectedContains = 1.8;

    assertImprovementStats(
        new ImprovementStats(expectedAdd, expectedContains, expectedRemove),
        testReadWriteLocksHelper(addSeq, containsSeq, removeSeq, "Small"));
  }

  private ImprovementStats testCoarseGrainedLockingHelper(
      final SequenceGenerator addSeq,
      final SequenceGenerator containsSeq,
      final SequenceGenerator removeSeq,
      final String datasetName)
      throws InterruptedException {

    final TestResultsPair results =
        runKernel(
            CoarseList::new,
            "CoarseList",
            SyncList::new,
            "SyncList",
            addSeq,
            containsSeq,
            removeSeq);
    final TestResults lockResults = results.A;
    final TestResults syncResults = results.B;
    printStats(syncResults, lockResults, addSeq, datasetName);

    assertEquals(syncResults.listLengthAfterAdds, lockResults.listLengthAfterAdds);
    assertEquals(syncResults.totalContainsSuccesses, lockResults.totalContainsSuccesses);
    assertEquals(syncResults.totalContainsFailures, lockResults.totalContainsFailures);
    assertEquals(syncResults.listLengthAfterRemoves, lockResults.listLengthAfterRemoves);
    assertEquals(syncResults.totalRemovesSuccesses, lockResults.totalRemovesSuccesses);
    assertEquals(syncResults.totalRemovesFailures, lockResults.totalRemovesFailures);

    final double addImprovement = lockResults.addRate / syncResults.addRate;
    final double containsImprovement = lockResults.containsRate / syncResults.containsRate;
    final double removeImprovement = lockResults.removeRate / syncResults.removeRate;

    return new ImprovementStats(addImprovement, containsImprovement, removeImprovement);
  }

  public ImprovementStats testReadWriteLocksHelper(
      final SequenceGenerator addSeq,
      final SequenceGenerator containsSeq,
      final SequenceGenerator removeSeq,
      final String datasetName)
      throws InterruptedException {
    final TestResultsPair results =
        runKernel(
            RWCoarseList::new,
            "RWCoarseList",
            SyncList::new,
            "SyncList",
            addSeq,
            containsSeq,
            removeSeq);
    final TestResults rwResults = results.A;
    final TestResults syncResults = results.B;
    printStats(syncResults, rwResults, addSeq, datasetName);

    assertEquals(syncResults.listLengthAfterAdds, rwResults.listLengthAfterAdds);
    assertEquals(syncResults.totalContainsSuccesses, rwResults.totalContainsSuccesses);
    assertEquals(syncResults.totalContainsFailures, rwResults.totalContainsFailures);
    assertEquals(syncResults.listLengthAfterRemoves, rwResults.listLengthAfterRemoves);
    assertEquals(syncResults.totalRemovesSuccesses, rwResults.totalRemovesSuccesses);
    assertEquals(syncResults.totalRemovesFailures, rwResults.totalRemovesFailures);

    final double addImprovement = rwResults.addRate / syncResults.addRate;
    final double containsImprovement = rwResults.containsRate / syncResults.containsRate;
    final double removeImprovement = rwResults.removeRate / syncResults.removeRate;

    return new ImprovementStats(addImprovement, containsImprovement, removeImprovement);
  }

  private void assertImprovementStats(ImprovementStats expected, ImprovementStats actual) {
    final String addmsg =
        String.format(
            "Expected add throughput " + "improvement to be %fx, but found %fx",
            expected.getAddImprovement(), actual.getAddImprovement());
    assertTrue(addmsg, actual.getAddImprovement() >= expected.getAddImprovement());

    final String containsmsg =
        String.format(
            "Expected contains throughput "
                + "improvement to be %fx with read-write locks, but found %fx",
            expected.getContainsImprovement(), actual.getContainsImprovement());
    assertTrue(containsmsg, actual.getContainsImprovement() >= expected.getContainsImprovement());

    final String removemsg =
        String.format(
            "Expected remove throughput improvement to be %fx with read-write locks, "
                + "but found %fx",
            expected.getRemoveImprovement(), actual.getRemoveImprovement());
    assertTrue(removemsg, actual.getRemoveImprovement() >= expected.getRemoveImprovement());
  }

  private interface ListFactory {
    ListSet construct();
  }
}
