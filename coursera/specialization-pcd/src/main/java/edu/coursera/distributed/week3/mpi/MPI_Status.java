package edu.coursera.distributed.week3.mpi;

import com.sun.jna.Pointer;

/** Wrapper for the native pointer to an MPI_Status object. */
final class MPI_Status {
  /** Wrapped MPI_Status object. */
  final Pointer status;

  /**
   * Constructor.
   *
   * @param setStatus Wrapped MPI_Status object
   */
  public MPI_Status(final Pointer setStatus) {
    this.status = setStatus;
  }
}
