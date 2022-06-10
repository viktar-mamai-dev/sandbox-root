package edu.coursera.concurrent.week4.algo;

import edu.coursera.concurrent.week4.components.ParComponent;
import edu.coursera.concurrent.week4.edges.Edge;

import java.util.Queue;

/**
 * A parallel implementation of Boruvka's algorithm to compute a Minimum Spanning Tree.
 */
public final class ParBoruvka extends AbstractBoruvka<ParComponent> {

    /**
     * Constructor.
     */
    public ParBoruvka() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeBoruvka(final Queue<ParComponent> nodesLoaded, final SolutionToBoruvka<ParComponent> solution) {
        ParComponent loopNode = null;
        // START OF EDGE CONTRACTION ALGORITHM
        while (!nodesLoaded.isEmpty()) {
            /*
             * poll() removes first element (node loopNode) from the nodesLoaded work-list.
             */
            loopNode = nodesLoaded.poll();

            if (loopNode == null || loopNode.isDead) continue;

            if (!loopNode.lock.tryLock()) {
                continue;
            }

            // retrieve loopNode's edge with minimum cost
            final Edge<ParComponent> e = loopNode.getMinEdge();
            if (e == null) {
                solution.setSolution(loopNode);
                loopNode.lock.unlock();
                break; // done - we've contracted the graph to a single node
            }

            final ParComponent other = e.getOther(loopNode);

            if (!other.lock.tryLock()) {
                loopNode.lock.unlock();
                nodesLoaded.add(loopNode);
                continue;
            }

            if (other.isDead) {
                loopNode.lock.unlock();
                other.lock.unlock();
                nodesLoaded.add(loopNode);
                continue;
            }
            other.isDead = true;
            // merge node other into node loopNode
            loopNode.merge(other, e.weight());

            loopNode.lock.unlock();
            other.lock.unlock();
            // add newly merged loopNode back in the work-list
            nodesLoaded.add(loopNode);
        }
    }
}
