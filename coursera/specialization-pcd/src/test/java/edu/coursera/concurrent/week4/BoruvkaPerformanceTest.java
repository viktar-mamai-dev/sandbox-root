package edu.coursera.concurrent.week4;

import edu.coursera.concurrent.week4.algo.SolutionToBoruvka;
import edu.coursera.concurrent.week4.algo.AbstractBoruvka;
import edu.coursera.concurrent.week4.algo.ParBoruvka;
import edu.coursera.concurrent.week4.factory.BoruvkaFactory;
import edu.coursera.concurrent.week4.components.Component;
import edu.coursera.concurrent.week4.edges.Edge;
import edu.coursera.concurrent.week4.factory.ParBoruvkaFactory;
import edu.coursera.concurrent.week4.algo.SeqBoruvka;
import edu.coursera.concurrent.week4.factory.SeqBoruvkaFactory;
import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BoruvkaPerformanceTest extends TestCase {
    // From http://www.dis.uniroma1.it/challenge9/download.shtml
    public static final String[] inputs = {
            "src/main/resources/boruvka/USA-road-d.FLA.gr.gz",
            "src/main/resources/boruvka/USA-road-d.NE.gr.gz"
    };
    static final double expectedSpeedup = 1.7;

    private static int getNCores() {
        String ncoresStr = System.getenv("COURSERA_GRADER_NCORES");
        if (ncoresStr == null) {
            return Runtime.getRuntime().availableProcessors();
        } else {
            return Integer.parseInt(ncoresStr);
        }
    }

    private static <C extends Component, E extends Edge> ExperimentResults driver(final String fileName,
                                                                                  final BoruvkaFactory<C, E> factory,
                                                                                  final AbstractBoruvka<C> boruvkaImpl)
            throws InterruptedException {
        SolutionToBoruvka finalSolution = null;
        long minElapsed = 0;
        for (int r = 0; r < 5; r++) {
            final Queue<C> nodesLoaded;
            if (boruvkaImpl instanceof SeqBoruvka) {
                nodesLoaded = new LinkedList<>();
            } else {
                nodesLoaded = new ConcurrentLinkedQueue<>();
            }
            final SolutionToBoruvka solution = new SolutionToBoruvka();
            Loader.read(fileName, factory, nodesLoaded);

            final long start;
            if (boruvkaImpl instanceof SeqBoruvka) {
                start = System.currentTimeMillis();
                boruvkaImpl.computeBoruvka(nodesLoaded, solution);
            } else {
                final Thread[] threads = new Thread[getNCores()];
                for (int i = 0; i < threads.length; i++) {
                    threads[i] = new Thread(() -> {
                        boruvkaImpl.computeBoruvka(nodesLoaded, solution);
                    });
                }

                start = System.currentTimeMillis();
                for (Thread thread : threads) {
                    thread.start();
                }
                for (Thread thread : threads) {
                    thread.join();
                }
            }
            final long elapsed = System.currentTimeMillis() - start;
            System.err.println("  " + fileName + " - " + boruvkaImpl.getClass().getName() + " - " + elapsed);

            if (r == 0 || elapsed < minElapsed) {
                minElapsed = elapsed;
            }
            finalSolution = solution;
        }

        assert (finalSolution.getSolution() != null);
        return new ExperimentResults(minElapsed, finalSolution.getSolution().totalEdges(),
                finalSolution.getSolution().totalWeight());
    }

    private void assertReasonablePercentError(final double expected, final double found) {
        final double delta = Math.abs(expected - found);
        final double percError = delta / expected;
        final double reasonablePercError = 0.2;
        assertTrue(String.format("Expected a percent error less than %f percent but got %f percent",
                reasonablePercError * 100.0, percError * 100.0), percError <= reasonablePercError);
    }

    public void testInputUSAroadFLA() throws InterruptedException {
        final ExperimentResults seqResults = driver(inputs[0], new SeqBoruvkaFactory(), new SeqBoruvka());
        final ExperimentResults parResults = driver(inputs[0], new ParBoruvkaFactory(), new ParBoruvka());
        assertEquals(seqResults.totalEdges, parResults.totalEdges);
        assertReasonablePercentError(seqResults.totalWeight, parResults.totalWeight);
        final double speedup = seqResults.elapsedTime / parResults.elapsedTime;
        assertTrue(String.format("Expected speedup of at least %fx, but was %fx", expectedSpeedup, speedup),
                speedup >= expectedSpeedup);
    }

    public void testInputUSAroadNE() throws InterruptedException {
        final ExperimentResults seqResults = driver(inputs[1], new SeqBoruvkaFactory(), new SeqBoruvka());
        final ExperimentResults parResults = driver(inputs[1], new ParBoruvkaFactory(), new ParBoruvka());
        assertEquals(seqResults.totalEdges, parResults.totalEdges);
        assertReasonablePercentError(seqResults.totalWeight, parResults.totalWeight);
        final double speedup = seqResults.elapsedTime / parResults.elapsedTime;
        assertTrue(String.format("Expected speedup of at least %fx, but was %fx", expectedSpeedup, speedup),
                speedup >= expectedSpeedup);
    }

    static class ExperimentResults {
        public final double elapsedTime;
        public final long totalEdges;
        public final double totalWeight;

        public ExperimentResults(final double elapsedTime, final long totalEdges, final double totalWeight) {
            this.elapsedTime = elapsedTime;
            this.totalEdges = totalEdges;
            this.totalWeight = totalWeight;
        }
    }
}
