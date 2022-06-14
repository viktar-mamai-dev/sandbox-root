package edu.coursera.distributed.week3.mpi;

/**
 * A Java exception wrapping an MPI error code, thrown in the case of some
 * MPI error.
 */
public final class MPIException extends Exception {
    /**
     * MPI Error code.
     */
    private final int code;

    /**
     * Constructor.
     *
     * @param setCode MPI error code
     */
    public MPIException(final int setCode) {
        this.code = setCode;
    }

    /**
     * Get the MPI error code.
     *
     * @return MPI error code
     */
    public int getErrCode() {
        return code;
    }
}