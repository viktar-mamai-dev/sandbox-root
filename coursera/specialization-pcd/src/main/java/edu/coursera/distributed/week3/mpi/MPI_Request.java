package edu.coursera.distributed.week3.mpi;

import com.sun.jna.Memory;

/**
 * Wrapper class for an MPI_Request representing a handle to an asynchronous
 * MPI communication.
 */
public abstract class MPI_Request {
    /**
     * Wrapped request object.
     */
    protected final Memory request;
    /**
     * Pointer to the buffer that the asynchronous operation is copying to
     * or from. This must be a native buffer to prevent JVM GC from moving
     * it around during the asynchronous communication.
     */
    protected final Memory buf;

    /**
     * Constructor.
     *
     * @param size Size of the copy being performed
     */
    public MPI_Request(final long size) {
        request = new Memory(8);
        buf = new Memory(size);
    }

    /**
     * Method to be called when the asynchronous communication completes.
     */
    protected abstract void complete();
}
