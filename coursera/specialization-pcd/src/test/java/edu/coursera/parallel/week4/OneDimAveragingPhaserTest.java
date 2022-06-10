package edu.coursera.parallel.week4;

import junit.framework.TestCase;

import java.util.concurrent.Phaser;

public class OneDimAveragingPhaserTest extends TestCase {
    // Number of times to repeat each test, for consistent timing results.
    final static private int niterations = 40000;

    private static int getNCores() {
        String ncoresStr = System.getenv("COURSERA_GRADER_NCORES");
        if (ncoresStr == null) {
            return Runtime.getRuntime().availableProcessors();
        } else {
            return Integer.parseInt(ncoresStr);
        }
    }

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
        final double[] myNewRef = createArray(N, niterations);
        final double[] myValRef = createArray(N, niterations);

        long barrierTotalTime = 0;
        long fuzzyTotalTime = 0;

        for (int r = 0; r < 3; r++) {
            final long barrierStartTime = System.currentTimeMillis();
            OneDimAveragingPhaser.runParallelBarrier(niterations, myNew, myVal, N, ntasks);
            final long barrierEndTime = System.currentTimeMillis();

            final long fuzzyStartTime = System.currentTimeMillis();
            OneDimAveragingPhaser.runParallelFuzzyBarrier(niterations, myNewRef, myValRef, N, ntasks);
            final long fuzzyEndTime = System.currentTimeMillis();

            if (niterations % 2 == 0) {
                checkResult(myNew, myNewRef);
            } else {
                checkResult(myVal, myValRef);
            }

            barrierTotalTime += (barrierEndTime - barrierStartTime);
            fuzzyTotalTime += (fuzzyEndTime - fuzzyStartTime);
        }

        return (double) barrierTotalTime / (double) fuzzyTotalTime;
    }

    /**
     * Test on large input.
     */
    public void testFuzzyBarrier() {
        final double expected = 1.05;
        final double speedup = parTestHelper(2 * 1024 * 1024, getNCores() * 1);
        final String errMsg = String.format("It was expected that the fuzzy barrier parallel implementation would " +
                "run %fx faster than the barrier implementation, but it only achieved %fx speedup", expected, speedup);
        assertTrue(errMsg, speedup >= expected);
        final String successMsg = String.format("Fuzzy barrier parallel implementation " +
                "ran %fx faster than the barrier implementation", speedup);
        System.out.println(successMsg);
    }
}
