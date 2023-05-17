package edu.coursera.distributed.week3;

import edu.coursera.TestExecutor;
import edu.coursera.TestResults;
import edu.coursera.Util;
import edu.coursera.distributed.week3.mpi.MPI;
import edu.coursera.distributed.week3.mpi.MPIException;
import java.util.Random;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MpiJavaTest extends TestCase {

  private static final int nCores = Util.getNCores();
  private static MPI mpi = null;
  private final TestExecutor testExecutor = new TestExecutor(1);

  public static Test suite() {
    TestSetup setup =
        new TestSetup(new TestSuite(MpiJavaTest.class)) {
          protected void setUp() throws Exception {
            assert (mpi == null);
            mpi = new MPI();
            mpi.MPI_Init();
          }

          protected void tearDown() throws Exception {
            assert (mpi != null);
            mpi.MPI_Finalize();
          }
        };
    return setup;
  }

  private Matrix createRandomMatrix(final int rows, final int cols) {
    Matrix matrix = new Matrix(rows, cols);
    final Random rand = new Random(314);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix.set(i, j, rand.nextInt(100));
      }
    }

    return matrix;
  }

  private Matrix copyMatrix(Matrix input) {
    return new Matrix(input);
  }

  private void testDriver(final int M, final int N, final int P) throws MPIException {
    final int myrank = mpi.MPI_Comm_rank(mpi.MPI_COMM_WORLD);

    Matrix a, b, c;
    if (myrank == 0) {
      a = createRandomMatrix(M, N);
      b = createRandomMatrix(N, P);
      c = createRandomMatrix(M, P);
    } else {
      a = new Matrix(M, N);
      b = new Matrix(N, P);
      c = new Matrix(M, P);
    }

    Matrix copy_a = copyMatrix(a);
    Matrix copy_b = copyMatrix(b);
    Matrix copy_c = copyMatrix(c);

    if (myrank == 0) {
      System.err.println(
          "Testing matrix multiply: ["
              + M
              + " x "
              + N
              + "] * ["
              + N
              + " x "
              + P
              + "] = ["
              + M
              + " x "
              + P
              + "]");
    }

    TestResults<Matrix> seqResults =
        testExecutor.execute(
            () -> {
              MatrixMult.seqMatrixMultiply(copy_a, copy_b, copy_c);
              return copy_c;
            });

    final double seqElapsed = seqResults.getExecutionTime();

    if (myrank == 0) {
      System.err.println("Sequential implementation ran in " + seqElapsed + " ms");
    }

    mpi.MPI_Barrier(mpi.MPI_COMM_WORLD);

    TestResults<Matrix> parResults =
        testExecutor.execute(
            () -> {
              MatrixMult.parallelMatrixMultiply(a, b, c, mpi);
              return c;
            });

    if (myrank == 0) {
      final double parallelElapsed = parResults.getExecutionTime();
      final double speedup = seqElapsed / parallelElapsed;
      System.err.println(
          "MPI implementation ran in "
              + parallelElapsed
              + " ms, yielding a speedup of "
              + speedup
              + "x");
      System.err.println();

      Matrix seqMatrix = seqResults.getResults().get(0);
      Matrix parMatrix = parResults.getResults().get(0);
      for (int i = 0; i < parMatrix.getNRows(); i++) {
        for (int j = 0; j < parMatrix.getNCols(); j++) {
          final String msg =
              "Expected "
                  + seqMatrix.get(i, j)
                  + " at ("
                  + i
                  + ", "
                  + j
                  + ") but found "
                  + parMatrix.get(i, j);
          assertEquals(msg, seqMatrix.get(i, j), parMatrix.get(i, j));
        }
      }

      final double expectedSpeedup = 0.75D * nCores;
      String msg = "Expected a speedup of at least " + expectedSpeedup + ", but saw " + speedup;
      assertTrue(msg, speedup >= expectedSpeedup);
    }
  }

  public void testMatrixMultiplySquareSmall() throws MPIException {
    testDriver(800, 800, 800);
  }

  public void testMatrixMultiplySquareLarge() throws MPIException {
    testDriver(1200, 1200, 1200);
  }

  public void testMatrixMultiplyRectangular1Small() throws MPIException {
    testDriver(800, 1600, 500);
  }

  public void testMatrixMultiplyRectangular2Small() throws MPIException {
    testDriver(1600, 800, 500);
  }

  public void testMatrixMultiplyRectangularLarge() throws MPIException {
    testDriver(1800, 1400, 1000);
  }
}
