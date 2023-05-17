package edu.coursera.distributed.week3.mpi;

import com.sun.jna.Pointer;

/** Wrapper for an MPI_Comm. */
public class MPI_Comm {
  /** Pointer to the native communicator. */
  protected final Pointer comm;

  /**
   * Constructor.
   *
   * @param setComm Initialize the communicator to use
   */
  public MPI_Comm(final Pointer setComm) {
    this.comm = setComm;
  }
}
