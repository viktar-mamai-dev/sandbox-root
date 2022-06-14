package edu.coursera.parallel.week4;

import edu.coursera.TestExecutor;
import edu.coursera.TestResults;
import edu.coursera.Util;
import junit.framework.TestCase;

public class OneDimAveragingPhaserTest extends TestCase {
    // Number of times to repeat each test, for consistent timing results.
    final static private int niterations = 40000;

    private static final int nCores = Util.getNCores();

    private double[] createArray(final int N, final int iterations) {
        final double[] input = new double[N + 2];
        int index = N + 1;
        while (index > 0) {
            input[index] = 1.0;
            index -= (iterations / 4);
        }
        return input;
    }

    private void checkResult(final double[] ref, final double[] output) {
        for (int i = 0; i < ref.length; i++) {
            String msg = "Mismatch on output at element " + i;
            assertEquals(msg, ref[i], output[i]);
        }
    }

    /**
     * A helper function for tests of the two-task parallel implementation.
     *
     * @param N The size of the array to test
     * @return The speedup achieved, not all tests use this information
     */
    private double parTestHelper(final int N, final int ntasks) {
        // Create a random input
        double[] myNew = createArray(N, niterations);
        double[] myVal = createArray(N, niterations);

        final int REPEATS = 3;
        final TestExecutor testExecutor = new TestExecutor(REPEATS);

        TestResults<double[]> parResults = testExecutor.execute(() ->
                OneDimAveragingPhaser.runParallelBarrier(niterations, myNew, myVal, N, ntasks));

        TestResults<double[]> parFuzzyResults = testExecutor.execute(() ->
                OneDimAveragingPhaser.runParallelFuzzyBarrier(niterations, myNew, myVal, N, ntasks));

        int size = parFuzzyResults.getResults().size();
        assertEquals(size, parResults.getResults().size());
        for (int i = 0; i < size; i++) {
            checkResult(parFuzzyResults.getResults().get(i), parResults.getResults().get(i));
        }

        return parResults.getExecutionTime() / parFuzzyResults.getExecutionTime();
    }

    /**
     * Test on large input.
     */
    public void testFuzzyBarrier() {
        final double expected = 1.05;
        final double speedup = parTestHelper(2 * 1024 * 1024, nCores * 1);
        final String errMsg = String.format("It was expected that the fuzzy barrier parallel implementation would " +
                "run %fx faster than the barrier implementation, but it only achieved %fx speedup", expected, speedup);
        assertTrue(errMsg, speedup >= expected);
        final String successMsg = String.format("Fuzzy barrier parallel implementation " +
                "ran %fx faster than the barrier implementation", speedup);
        System.out.println(successMsg);
    }
}
