package edu.coursera.distributed.week3.mpi;

/** An MPI_Request object for an asynchronous receive. */
final class Recv_MPI_Request extends MPI_Request {
  /** The destination buffer in the JVM. */
  private final double[] dst;
  /** The offset in the destination buffer to receive at. */
  private final int offset;
  /** An element count for the receive. */
  private final int count;

  /**
   * Constructor.
   *
   * @param setSize Size in bytes of the receive
   * @param setDst Destination JVM buffer
   * @param setOffset Offset into dst
   * @param setCount Number of elements in dst to receive
   */
  public Recv_MPI_Request(
      final long setSize, final double[] setDst, final int setOffset, final int setCount) {
    super(setSize);
    this.dst = setDst;
    this.offset = setOffset;
    this.count = setCount;
  }

  /** {@inheritDoc} */
  @Override
  protected void complete() {
    buf.read(0, dst, offset, count);
  }
}
