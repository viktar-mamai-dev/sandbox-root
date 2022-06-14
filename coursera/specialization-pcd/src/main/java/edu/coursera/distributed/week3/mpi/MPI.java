package edu.coursera.distributed.week3.mpi;

import com.sun.jna.Pointer;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

/**
 * Wrapper class for all supported Java MPI APIs. For more detailed documentation of the wrapped MPI APIs, refer to
 * https://www.open-mpi.org/doc/v1.8/
 */
public final class MPI {
    /**
     * Constant wrapper for communicator MPI_COMM_WORLD.
     */
    public final MPI_Comm MPI_COMM_WORLD;
    /**
     * Constant wrapper for datatype MPI_INTEGER.
     */
    public final MPI_Datatype MPI_INTEGER;
    /**
     * Constant wrapper for datatype MPI_FLOAT.
     */
    public final MPI_Datatype MPI_FLOAT;
    /**
     * Constant wrapper for datatype MPI_DOUBLE.
     */
    public final MPI_Datatype MPI_DOUBLE;
    /**
     * Constant wrapper for MPI_STATUS_IGNORE.
     */
    public final MPI_Status MPI_STATUS_IGNORE;

    /**
     * Constructor.
     */
    public MPI() {
        Pointer ptr = MPILib.lib.getGlobalVariableAddress("ompi_mpi_comm_world");
        MPI_COMM_WORLD = new MPI_Comm(ptr);

        ptr = MPILib.lib.getGlobalVariableAddress("ompi_mpi_integer");
        MPI_INTEGER = new MPI_Datatype(ptr);

        ptr = MPILib.lib.getGlobalVariableAddress("ompi_mpi_float");
        MPI_FLOAT = new MPI_Datatype(ptr);

        ptr = MPILib.lib.getGlobalVariableAddress("ompi_mpi_double");
        MPI_DOUBLE = new MPI_Datatype(ptr);

        MPI_STATUS_IGNORE = new MPI_Status(Pointer.NULL);
    }

    /**
     * Java wrapper for MPI_Init.
     *
     * @throws MPIException On MPI error
     */
    public void MPI_Init() throws MPIException {
        final int err = MPILib.INSTANCE.MPI_Init(new int[0], new String[0]);
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Finalize.
     *
     * @throws MPIException On MPI error
     */
    public void MPI_Finalize() throws MPIException {
        final int err = MPILib.INSTANCE.MPI_Finalize();
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Comm_size.
     *
     * @param comm Communicator
     * @return Number of MPI ranks in the provided communicator
     * @throws MPIException On MPI error
     */
    public int MPI_Comm_size(final MPI_Comm comm) throws MPIException {
        int[] result = new int[1];
        final int err = MPILib.INSTANCE.MPI_Comm_size(comm.comm, result);
        if (err != 0) {
            throw new MPIException(err);
        }
        return result[0];
    }

    /**
     * Java wrapper for MPI_Comm_rank.
     *
     * @param comm Communicator
     * @return Integer rank of the current process in the specified communicator
     * @throws MPIException On MPI error
     */
    public int MPI_Comm_rank(final MPI_Comm comm) throws MPIException {
        int[] result = new int[1];
        final int err = MPILib.INSTANCE.MPI_Comm_rank(comm.comm, result);
        if (err != 0) {
            throw new MPIException(err);
        }
        return result[0];
    }

    /**
     * Java wrapper for MPI_Bcast.
     *
     * @param buf    JVM buffer to send from/receive to in bcast
     * @param offset Offset in buf to perform bcast from, in elements
     * @param count  Number of elements in buf to perform bcast with, starting at
     *               offset
     * @param root   Root rank of this broadcast
     * @param comm   Communicator to use
     * @throws MPIException On MPI error
     */
    public void MPI_Bcast(final double[] buf, final int offset, final int count,
                          final int root, final MPI_Comm comm) throws MPIException {
        final DoubleBuffer wrapper = DoubleBuffer.wrap(buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Bcast(wrapper, count, MPI_DOUBLE.datatype, root, comm.comm);
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Send.
     *
     * @param buf    JVM buffer to send from
     * @param offset Offset in buf to send from, in elements
     * @param count  Number of elements in buf to send, starting at offset
     * @param dst    MPI rank to send to
     * @param tag    Tag for this send
     * @param comm   Communicator to use
     * @throws MPIException On MPI error
     */
    public void MPI_Send(final int[] buf, final int offset, final int count,
                         final int dst, final int tag, final MPI_Comm comm)
            throws MPIException {
        final IntBuffer wrapper = IntBuffer.wrap(buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Send(wrapper, count, MPI_INTEGER.datatype, dst, tag, comm.comm);
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Send.
     *
     * @param buf    JVM buffer to send from
     * @param offset Offset in buf to send from, in elements
     * @param count  Number of elements in buf to send, starting at offset
     * @param dst    MPI rank to send to
     * @param tag    Tag for this send
     * @param comm   Communicator to use
     * @throws MPIException On MPI error
     */
    public void MPI_Send(final double[] buf, final int offset, final int count,
                         final int dst, final int tag, final MPI_Comm comm)
            throws MPIException {
        final DoubleBuffer wrapper = DoubleBuffer.wrap(buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Send(wrapper, count, MPI_DOUBLE.datatype, dst, tag, comm.comm);
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Recv.
     *
     * @param buf    JVM buffer to receive into
     * @param offset Offset in buf to receive to, in elements
     * @param count  Number of elements to receive into buf, starting at offset
     * @param src    MPI rank to receive from
     * @param tag    Tag for this receive
     * @param comm   Communicator to use
     * @throws MPIException On MPI error
     */
    public void MPI_Recv(final int[] buf, final int offset, final int count,
                         final int src, final int tag, final MPI_Comm comm)
            throws MPIException {
        final IntBuffer wrapper = IntBuffer.wrap(buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Recv(wrapper, count, MPI_INTEGER.datatype, src, tag, comm.comm,
                MPI_STATUS_IGNORE.status);
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Recv.
     *
     * @param buf    JVM buffer to receive into
     * @param offset Offset in buf to receive to, in elements
     * @param count  Number of elements to receive into buf, starting at offset
     * @param src    MPI rank to receive from
     * @param tag    Tag for this receive
     * @param comm   Communicator to use
     * @throws MPIException On MPI error
     */
    public void MPI_Recv(final double[] buf, final int offset, final int count,
                         final int src, final int tag, final MPI_Comm comm)
            throws MPIException {
        final DoubleBuffer wrapper = DoubleBuffer.wrap(buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Recv(wrapper, count, MPI_DOUBLE.datatype, src, tag, comm.comm,
                MPI_STATUS_IGNORE.status);
        if (err != 0) {
            throw new MPIException(err);
        }
    }

    /**
     * Java wrapper for MPI_Irecv.
     *
     * @param buf    JVM buffer to receive into
     * @param offset Offset in buf to receive to, in elements
     * @param count  Number of elements to receive into buf, starting at offset
     * @param src    MPI rank to receive from
     * @param tag    Tag for this receive
     * @param comm   Communicator to use
     * @return A request handle that can be used to wait on this async operation
     * @throws MPIException On MPI error
     */
    public MPI_Request MPI_Irecv(final double[] buf, final int offset,
                                 final int count, final int src, final int tag, final MPI_Comm comm)
            throws MPIException {
        Recv_MPI_Request request = new Recv_MPI_Request(count * 8, buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Irecv(request.buf, count, MPI_DOUBLE.datatype, src, tag, comm.comm,
                request.request);
        if (err != 0) {
            throw new MPIException(err);
        }
        return request;
    }

    /**
     * Java wrapper for MPI_Isend.
     *
     * @param buf    JVM buffer to send from
     * @param offset Offset in buf to send from, in elements
     * @param count  Number of elements in buf to send, starting at offset
     * @param dst    MPI rank to send to
     * @param tag    Tag for this send
     * @param comm   Communicator to use
     * @return A request handle that can be used to wait on this async operation
     * @throws MPIException On MPI error
     */
    public MPI_Request MPI_Isend(final double[] buf, final int offset,
                                 final int count, final int dst, final int tag, final MPI_Comm comm)
            throws MPIException {
        Send_MPI_Request request = new Send_MPI_Request(count * 8);
        request.buf.write(0, buf, offset, count);
        final int err = MPILib.INSTANCE.MPI_Isend(request.buf, count, MPI_DOUBLE.datatype, dst, tag, comm.comm,
                request.request);
        if (err != 0) {
            throw new MPIException(err);
        }
        return request;
    }

    /**
     * Java wrapper for MPI_Wait.
     *
     * @param request Request handle to wait on
     * @throws MPIException On MPI error
     */
    public void MPI_Wait(final MPI_Request request) throws MPIException {
        final int err = MPILib.INSTANCE.MPI_Wait(request.request, MPI_STATUS_IGNORE.status);
        if (err != 0) {
            throw new MPIException(err);
        }
        request.complete();
    }

    /**
     * Java wrapper for MPI_Waitall.
     *
     * @param requests Request handles to wait on
     * @throws MPIException On MPI error
     */
    public void MPI_Waitall(final MPI_Request[] requests) throws MPIException {
        for (int i = 0; i < requests.length; i++) {
            MPI_Wait(requests[i]);
        }
    }

    /**
     * Java wrapper for MPI_Barrier.
     *
     * @param comm Communicator to use in the MPI_Barrier
     * @throws MPIException On MPI error
     */
    public void MPI_Barrier(final MPI_Comm comm) throws MPIException {
        final int err = MPILib.INSTANCE.MPI_Barrier(comm.comm);
        if (err != 0) {
            throw new MPIException(err);
        }
    }
}
