package edu.coursera.distributed.week3.mpi;

import com.sun.jna.Pointer;

/**
 * Wrapper for an MPI_Datatype.
 */
final class MPI_Datatype {
    /**
     * Pointer to native memory storing the actual datatype info.
     */
    final Pointer datatype;

    /**
     * Constructor.
     *
     * @param setDatatype Initialize the wrapped datatype
     */
    public MPI_Datatype(final Pointer setDatatype) {
        this.datatype = setDatatype;
    }
}
