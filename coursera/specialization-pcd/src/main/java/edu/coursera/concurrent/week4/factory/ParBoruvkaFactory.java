package edu.coursera.concurrent.week4.factory;

import edu.coursera.concurrent.week4.algo.AbstractBoruvka;
import edu.coursera.concurrent.week4.algo.ParBoruvka;
import edu.coursera.concurrent.week4.components.ParComponent;
import edu.coursera.concurrent.week4.edges.ParEdge;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A factory for generating components and edges when performing a parallel
 * traversal.
 */
public final class ParBoruvkaFactory implements BoruvkaFactory<ParComponent, ParEdge> {
    /**
     * {@inheritDoc}
     */
    @Override
    public ParComponent newComponent(final int nodeId) {
        return new ParComponent(nodeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParEdge newEdge(final ParComponent from, final ParComponent to, final double weight) {
        return new ParEdge(from, to, weight);
    }

    @Override
    public AbstractBoruvka<ParComponent> generateBoruvka() {
        return new ParBoruvka();
    }

    @Override
    public Queue<ParComponent> createQueue() {
        return new ConcurrentLinkedQueue<>();
    }
}
