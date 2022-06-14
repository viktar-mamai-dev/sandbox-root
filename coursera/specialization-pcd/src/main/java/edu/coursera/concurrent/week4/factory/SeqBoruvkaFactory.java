package edu.coursera.concurrent.week4.factory;

import edu.coursera.concurrent.week4.algo.AbstractBoruvka;
import edu.coursera.concurrent.week4.algo.ParBoruvka;
import edu.coursera.concurrent.week4.algo.SeqBoruvka;
import edu.coursera.concurrent.week4.components.ParComponent;
import edu.coursera.concurrent.week4.factory.BoruvkaFactory;
import edu.coursera.concurrent.week4.components.SeqComponent;
import edu.coursera.concurrent.week4.edges.SeqEdge;
import scala.collection.Seq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A factory for generating components and edges when performing a sequential
 * traversal.
 */
public final class SeqBoruvkaFactory implements BoruvkaFactory<SeqComponent, SeqEdge> {
    /**
     * {@inheritDoc}
     */
    @Override
    public SeqComponent newComponent(final int nodeId) {
        return new SeqComponent(nodeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SeqEdge newEdge(final SeqComponent from, final SeqComponent to, final double weight) {
        return new SeqEdge(from, to, weight);
    }

    @Override
    public AbstractBoruvka<SeqComponent> generateBoruvka() {
        return new SeqBoruvka();
    }

    @Override
    public Queue<SeqComponent> createQueue() {
        return new LinkedList<>();
    }
}
