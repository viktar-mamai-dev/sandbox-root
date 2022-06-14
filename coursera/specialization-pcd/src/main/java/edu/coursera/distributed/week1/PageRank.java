package edu.coursera.distributed.week1;

import edu.coursera.distributed.week1.Website;
import org.apache.spark.api.java.JavaPairRDD;
import scala.Tuple2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A wrapper class for the implementation of a single iteration of the iterative
 * PageRank algorithm.
 */
public final class PageRank {
    /**
     * Default constructor.
     */
    private PageRank() {
    }

    /**
     * TODO Given an RDD of websites and their ranks, compute new ranks for all
     * websites and return a new RDD containing the updated ranks.
     * <p>
     * Recall from lectures that given a website B with many other websites linking to it, the updated rank for B is
     * the sum over all source websites of the rank of the source website divided by the number of outbound links
     * from the source website. This new rank is damped by multiplying it by 0.85 and adding 0.15. Put more simply:
     * <p>
     * new_rank(B) = 0.15 + 0.85 * sum(rank(A) / out_count(A)) for all A linking to B
     * <p>
     * For this assignment, you are responsible for implementing this PageRank algorithm using the Spark Java APIs.
     * <p>
     * The reference solution of sparkPageRank uses the following Spark RDD APIs. However, you are free to develop
     * whatever solution makes the most sense to you which also demonstrates speedup on multiple threads.
     * <p>
     * 1) JavaPairRDD.join
     * 2) JavaRDD.flatMapToPair
     * 3) JavaPairRDD.reduceByKey
     * 4) JavaRDD.mapValues
     *
     * @param sites The connectivity of the website graph, keyed on unique website IDs.
     * @param ranks The current ranks of each website, keyed on unique website IDs.
     * @return The new ranks of the websites graph, using the PageRank algorithm to update site ranks.
     */
    public static JavaPairRDD<Integer, Double> sparkPageRank(final JavaPairRDD<Integer, Website> sites,
                                                             final JavaPairRDD<Integer, Double> ranks) {
        JavaPairRDD<Integer, Double> newRanks = sites.join(ranks)
                .flatMapToPair((Tuple2<Integer, Tuple2<Website, Double>> kv) -> {
                    Integer websiteId = kv._1();
                    Tuple2<Website, Double> value = kv._2();
                    Website website = value._1();
                    Double currentRank = value._2();

                    List<Tuple2<Integer, Double>> contributions = new LinkedList<>();
                    Iterator<Integer> iterator = website.edgeIterator();
                    while (iterator.hasNext()) {
                        int target = iterator.next();
                        contributions.add(new Tuple2<>(target, currentRank / (double) website.getNEdges()));
                    }
                    return contributions;
                });
        return newRanks.reduceByKey(Double::sum).mapValues(val -> 0.15 + 0.85 * val);
    }

    public static double[] seqPageRank(Website[] sites, double[] ranks) {
        double[] newRanks = new double[ranks.length];

        for (int j = 0; j < sites.length; j++) {
            Iterator<Integer> iter = sites[j].edgeIterator();
            while (iter.hasNext()) {
                int target = iter.next();
                newRanks[target] += ranks[j] / (double) sites[j].getNEdges();
            }
        }

        for (int j = 0; j < newRanks.length; j++) {
            newRanks[j] = 0.15 + 0.85 * newRanks[j];
        }

        return newRanks;
    }
}
