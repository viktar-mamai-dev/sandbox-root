package edu.coursera.parallel.week1;

import java.util.ArrayList;
import java.util.List;

/** Class wrapping methods for implementing reciprocal array sum in parallel. */
public final class ReciprocalArraySum {

  /** Default constructor. */
  private ReciprocalArraySum() {}

  /**
   * Sequentially compute the sum of the reciprocal values for a given array.
   *
   * @param input Input array
   * @return The sum of the reciprocals of the array input
   */
  public static double seqArraySum(final double[] input) {
    double sum = 0;

    // Compute sum of reciprocals of array elements
    for (double v : input) {
      sum += 1 / v;
    }

    return sum;
  }

  /**
   * Computes the size of each chunk, given the number of chunks to create across a given number of
   * elements.
   *
   * @param nChunks The number of chunks to create
   * @param nElements The number of elements to chunk across
   * @return The default chunk size
   */
  private static int getChunkSize(final int nChunks, final int nElements) {
    // Integer ceil
    return (nElements + nChunks - 1) / nChunks;
  }

  /**
   * Computes the inclusive element index that the provided chunk starts at, given there are a
   * certain number of chunks.
   *
   * @param chunk The chunk to compute the start of
   * @param nChunks The number of chunks created
   * @param nElements The number of elements to chunk across
   * @return The inclusive index that this chunk starts at in the set of nElements
   */
  private static int getChunkStartInclusive(
      final int chunk, final int nChunks, final int nElements) {
    final int chunkSize = getChunkSize(nChunks, nElements);
    return chunk * chunkSize;
  }

  /**
   * Computes the exclusive element index that the provided chunk ends at, given there are a certain
   * number of chunks.
   *
   * @param chunk The chunk to compute the end of
   * @param nChunks The number of chunks created
   * @param nElements The number of elements to chunk across
   * @return The exclusive end index for this chunk
   */
  private static int getChunkEndExclusive(final int chunk, final int nChunks, final int nElements) {
    final int chunkSize = getChunkSize(nChunks, nElements);
    final int end = (chunk + 1) * chunkSize;
    return Math.min(end, nElements);
  }

  /**
   * TODO: Modify this method to compute the same reciprocal sum as seqArraySum, but use two tasks
   * running in parallel under the Java Fork Join framework. You may assume that the length of the
   * input array is evenly divisible by 2.
   *
   * @param input Input array
   * @return The sum of the reciprocals of the array input
   */
  protected static double parArraySum(final double[] input) {
    return parManyTaskArraySum(input, 2);
  }

  /**
   * TODO: Extend the work you did to implement parArraySum to use a set number of tasks to compute
   * the reciprocal array sum. You may find the above utilities getChunkStartInclusive and
   * getChunkEndExclusive helpful in computing the range of element indices that belong to each
   * chunk.
   *
   * @param input Input array
   * @param numTasks The number of tasks to create
   * @return The sum of the reciprocals of the array input
   */
  protected static double parManyTaskArraySum(final double[] input, final int numTasks) {
    List<ReciprocalArraySumTask> tasks = new ArrayList<>();

    for (int i = 0; i < numTasks - 1; i++) {
      ReciprocalArraySumTask subTask =
          new ReciprocalArraySumTask(
              getChunkStartInclusive(i, numTasks, input.length),
              getChunkEndExclusive(i, numTasks, input.length),
              input);
      tasks.add(subTask);
      subTask.fork();
    }
    ReciprocalArraySumTask subTask =
        new ReciprocalArraySumTask(
            getChunkStartInclusive(numTasks - 1, numTasks, input.length),
            getChunkEndExclusive(numTasks - 1, numTasks, input.length),
            input);

    subTask.compute();
    double sum = subTask.getValue();

    for (int j = 0; j < numTasks - 1; j++) {
      tasks.get(j).join();
    }

    for (int j = 0; j < numTasks - 1; j++) {
      sum += tasks.get(j).getValue();
    }
    return sum;
  }
}
