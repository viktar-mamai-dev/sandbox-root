package edu.coursera.parallel.week3;

import edu.coursera.TestExecutor;
import edu.coursera.TestResults;
import edu.coursera.Util;
import java.util.List;
import java.util.Random;
import junit.framework.TestCase;

public class MatrixMultiplyTest extends TestCase {
  // Number of times to repeat each test, for consistent timing results.
  private static final int REPEATS = 20;

  private static final double nCores = Util.getNCores();
  private final TestExecutor testExecutor = new TestExecutor(REPEATS);

  /**
   * Create a double[] of length N to use as input for the tests.
   *
   * @param N Size of the array to create
   * @return Initialized double array of length N
   */
  private double[][] createMatrix(final int N) {
    final double[][] input = new double[N][N];
    final Random rand = new Random(314);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        input[i][j] = rand.nextInt(100);
      }
    }

    return input;
  }

  /** Check if there is any difference in the correct and generated outputs. */
  private void checkResult(final double[][] ref, final double[][] output) {
    assertEquals(ref.length, output.length);
    final int N = ref.length;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        String msg = "Error detected on cell (" + i + ", " + j + ")";
        assertEquals(msg, ref[i][j], output[i][j]);
      }
    }
  }

  private void checkResult(final List<double[][]> ref, final List<double[][]> output, final int N) {
    assertEquals(ref.size(), output.size());
    for (int i = 0; i < ref.size(); i++) {
      checkResult(ref.get(i), output.get(i));
    }
  }

  /**
   * A helper function for tests of the two-task parallel implementation.
   *
   * @param N The size of the array to test
   * @return The speedup achieved, not all tests use this information
   */
  private double parTestHelper(final int N) {
    // Create a random input
    final double[][] A = createMatrix(N);
    final double[][] B = createMatrix(N);

    // Use a reference sequential version to compute the correct result
    TestResults<double[][]> seqResults =
        testExecutor.execute(() -> MatrixMultiply.seqMatrixMultiply(A, B, N));

    TestResults<double[][]> parResults =
        testExecutor.execute(() -> MatrixMultiply.parMatrixMultiply(A, B, N));

    checkResult(parResults.getResults(), seqResults.getResults(), N);

    return seqResults.getExecutionTime() / parResults.getExecutionTime();
  }

  /** Tests the performance of the parallel implementation on a 512x512 matrix. */
  public void testPar512_x_512() {
    double speedup = parTestHelper(512);
    double minimalExpectedSpeedup = nCores * 0.6;
    final String errMsg =
        String.format(
            "It was expected that the parallel implementation would run at "
                + "least %fx faster, but it only achieved %fx speedup",
            minimalExpectedSpeedup, speedup);
    assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
  }

  /** Tests the performance of the parallel implementation on a 768x768 matrix. */
  public void testPar768_x_768() {
    double speedup = parTestHelper(768);
    double minimalExpectedSpeedup = nCores * 0.6;
    final String errMsg =
        String.format(
            "It was expected that the parallel implementation would run at "
                + "least %fx faster, but it only achieved %fx speedup",
            minimalExpectedSpeedup, speedup);
    assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
  }
}
