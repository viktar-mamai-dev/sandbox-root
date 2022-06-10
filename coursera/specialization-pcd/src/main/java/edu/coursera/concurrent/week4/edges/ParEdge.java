package edu.coursera.concurrent.week4.edges;

import edu.coursera.concurrent.week4.components.ParComponent;

/**
 * A ParEdge represents a weighted edge between two ParComponents.
 */
public final class ParEdge extends Edge<ParComponent> implements Comparable<Edge> {
    /**
     * Weight of this edge.
     */
    public double weight;
    /**
     * Source component.
     */
    private ParComponent fromComponent;
    /**
     * Destination component.
     */
    private ParComponent toComponent;

    /**
     * Constructor.
     *
     * @param from From edge.
     * @param to   To edges.
     * @param w    Weight of this edge.
     */
    public ParEdge(final ParComponent from, final ParComponent to, final double w) {
        fromComponent = from;
        toComponent = to;
        weight = w;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParComponent fromComponent() {
        return fromComponent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParComponent toComponent() {
        return toComponent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double weight() {
        return weight;
    }

    /**
     * {@inheritDoc}
     */
    public ParComponent getOther(final ParComponent from) {
        if (fromComponent == from) {
            assert (toComponent != from);
            return toComponent;
        }

        if (toComponent == from) {
            assert (fromComponent != from);
            return fromComponent;
        }
        assert (false);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Edge e) {
        return Double.compare(weight, e.weight());
    }

    /**
     * {@inheritDoc}
     */
    public ParEdge replaceComponent(final ParComponent from, final ParComponent to) {
        if (fromComponent == from) {
            fromComponent = to;
        }
        if (toComponent == from) {
            toComponent = to;
        }
        return this;
    }
}