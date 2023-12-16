package edu.coursera.distributed.week3.mpi;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

interface MPILib extends Library {
  MPILib INSTANCE = (MPILib) Native.loadLibrary("mpi", MPILib.class);
  NativeLibrary lib = NativeLibrary.getInstance("mpi");

  int MPI_Send(IntBuffer buf, int count, Pointer datatype, int dest, int tag, Pointer comm);

  int MPI_Send(DoubleBuffer buf, int count, Pointer datatype, int dest, int tag, Pointer comm);

  int MPI_Recv(
      IntBuffer buf, int count, Pointer datatype, int src, int tag, Pointer comm, Pointer status);

  int MPI_Recv(
      DoubleBuffer buf,
      int count,
      Pointer datatype,
      int src,
      int tag,
      Pointer comm,
      Pointer status);

  int MPI_Isend(
      Memory buf, int count, Pointer datatype, int dest, int tag, Pointer comm, Memory request);

  int MPI_Irecv(
      Memory buf, int count, Pointer datatype, int src, int tag, Pointer comm, Memory request);

  int MPI_Bcast(DoubleBuffer buf, int count, Pointer datatype, int root, Pointer comm);

  int MPI_Wait(Memory request, Pointer status);

  int MPI_Barrier(Pointer comm);

  int MPI_Init(int[] argc, String[] argv);

  int MPI_Finalize();

  int MPI_Comm_size(Pointer comm, int[] size);

  int MPI_Comm_rank(Pointer comm, int[] rank);
}
