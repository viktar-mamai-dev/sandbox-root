package edu.coursera.distributed.week3.mpi;

/**
 * An MPI_Request object for an asynchronous send.
 */
final class Send_MPI_Request extends MPI_Request {
    /**
     * Constructor.
     *
     * @param size Size in bytes of the receive
     */
    public Send_MPI_Request(final long size) {
        super(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void complete() {
    }
}
