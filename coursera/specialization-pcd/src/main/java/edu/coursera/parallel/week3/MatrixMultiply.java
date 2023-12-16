package edu.coursera.parallel.week3;

import static edu.rice.pcdp.PCDP.forall2dChunked;
import static edu.rice.pcdp.PCDP.forseq2d;

/** Wrapper class for implementing matrix multiply efficiently in parallel. */
public final class MatrixMultiply {
  /** Default constructor. */
  private MatrixMultiply() {}

  /**
   * Perform a two-dimensional matrix multiply (A x B = C) sequentially.
   *
   * @param A An input matrix with dimensions NxN
   * @param B An input matrix with dimensions NxN
   * @param N Size of each dimension of the input matrices
   */
  public static double[][] seqMatrixMultiply(final double[][] A, final double[][] B, final int N) {
    final double[][] C = new double[N][N];
    forseq2d(
        0,
        N - 1,
        0,
        N - 1,
        (i, j) -> {
          C[i][j] = 0.0;
          for (int k = 0; k < N; k++) {
            C[i][j] += A[i][k] * B[k][j];
          }
        });
    return C;
  }

  /**
   * Perform a two-dimensional matrix multiply (A x B = C) in parallel.
   *
   * @param A An input matrix with dimensions NxN
   * @param B An input matrix with dimensions NxN
   * @param N Size of each dimension of the input matrices
   */
  public static double[][] parMatrixMultiply(final double[][] A, final double[][] B, final int N) {
    /*
     * TODO Parallelize this outermost two-dimension sequential loop to achieve performance improvement.
     */
    final double[][] C = new double[N][N];
    forall2dChunked(
        0,
        N - 1,
        0,
        N - 1,
        (i, j) -> {
          C[i][j] = 0.0;
          for (int k = 0; k < N; k++) {
            C[i][j] += A[i][k] * B[k][j];
          }
        });
    return C;
  }
}
