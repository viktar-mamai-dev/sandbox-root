package edu.coursera.parallel.week1;

import edu.coursera.TestExecutor;
import edu.coursera.Util;
import edu.coursera.TestResults;
import junit.framework.TestCase;

import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.function.Supplier;

public class ReciprocalArraySumTest extends TestCase {
    // Number of times to repeat each test, for consistent timing results.
    final static private int REPEATS = 60;
    private static final int nCores = Util.getNCores();

    private final TestExecutor testExecutor = new TestExecutor(REPEATS);

    /**
     * Create a double[] of length N to use as input for the tests.
     *
     * @param N Size of the array to create
     * @return Initialized double array of length N
     */
    private double[] createArray(final int N) {
        final double[] input = new double[N];
        final Random rand = new Random(314);

        for (int i = 0; i < N; i++) {
            input[i] = rand.nextInt(100);
            // Don't allow zero values in the input array to prevent divide-by-zero
            if (input[i] == 0.0) {
                i--;
            }
        }

        return input;
    }

    /**
     * A helper function for tests of the two-task parallel implementation.
     *
     * @param N                  The size of the array to test
     * @param useManyTaskVersion Switch between two-task and many-task versions of the code
     * @param ntasks             Number of tasks to use
     * @return The speedup achieved, not all tests use this information
     */
    private double parTestHelper(final int N, final boolean useManyTaskVersion, final int ntasks) {
        // Create a random input
        final double[] input = createArray(N);

        TestResults<Double> seqResults = testExecutor.execute(() -> ReciprocalArraySum.seqArraySum(input));

        Supplier<Double> supplier = null;
        if (useManyTaskVersion) {
            supplier = () -> ReciprocalArraySum.parManyTaskArraySum(input, ntasks);
        } else {
            assert ntasks == 2;
            supplier = () -> ReciprocalArraySum.parArraySum(input);
        }

        TestResults<Double> parResults = testExecutor.execute(supplier);

        final double eps = 1E-2;

        DoubleSummaryStatistics seqStat = seqResults.getResults().stream().mapToDouble(d -> d).summaryStatistics();
        DoubleSummaryStatistics parStat = parResults.getResults().stream().mapToDouble(d -> d).summaryStatistics();

        assertTrue("Mismatch in result", Math.abs(seqStat.getMin() - parStat.getMin()) < eps);
        assertTrue("Mismatch in result", Math.abs(seqStat.getMax() - parStat.getMax()) < eps);
        assertTrue("Mismatch in result", Math.abs(seqStat.getAverage() - parStat.getAverage()) < eps);
        assertTrue("Mismatch in result", Math.abs(seqStat.getSum() - parStat.getSum()) < eps);

        return seqResults.getExecutionTime() / parResults.getExecutionTime();
    }

    /**
     * Test that the two-task parallel implementation properly computes the results for a million-element array.
     */
    public void testParSimpleTwoMillion() {
        final double minimalExpectedSpeedup = 1.5;
        final double speedup = parTestHelper(2_000_000, false, 2);
        final String errMsg = String.format("It was expected that the two-task parallel implementation would run at " +
                "least %fx faster, but it only achieved %fx speedup", minimalExpectedSpeedup, speedup);
        assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
    }

    /**
     * Test that the two-task parallel implementation properly computes the results for a hundred million-element array.
     */
    public void testParSimpleTwoHundredMillion() {
        final double speedup = parTestHelper(200_000_000, false, 2);
        final double minimalExpectedSpeedup = 1.5;
        final String errMsg = String.format("It was expected that the two-task parallel implementation would run at " +
                "least %fx faster, but it only achieved %fx speedup", minimalExpectedSpeedup, speedup);
        assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
    }

    /**
     * Test that the many-task parallel implementation properly computes the results for a million-element array.
     */
    public void testParManyTaskTwoMillion() {
        final double minimalExpectedSpeedup = (double) nCores * 0.6;
        final double speedup = parTestHelper(2_000_000, true, nCores);
        final String errMsg = String.format("It was expected that the many-task parallel implementation would run at " +
                "least %fx faster, but it only achieved %fx speedup", minimalExpectedSpeedup, speedup);
        assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
    }

    /**
     * Test that the many-task parallel implementation properly computes the results for a hundred million-element array.
     */
    public void testParManyTaskTwoHundredMillion() {
        final double speedup = parTestHelper(200_000_000, true, nCores);
        final double minimalExpectedSpeedup = (double) nCores * 0.8;
        final String errMsg = String.format("It was expected that the many-task parallel implementation would run at " +
                "least %fx faster, but it only achieved %fx speedup", minimalExpectedSpeedup, speedup);
        assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
    }
}
