package edu.coursera.distributed.week1;

import edu.coursera.TestExecutor;
import edu.coursera.TestResults;
import edu.coursera.Util;
import junit.framework.TestCase;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class PageRankTest extends TestCase {

    private static final int nCores = Util.getNCores();

    private static JavaSparkContext getSparkContext(final int nCores) {
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        final SparkConf conf = new SparkConf()
                .setAppName("edu.coursera.distributed.week1.PageRank")
                .setMaster("local[" + nCores + "]")
                .set("spark.ui.showConsoleProgress", "false");
        JavaSparkContext ctx = new JavaSparkContext(conf);
        ctx.setLogLevel("OFF");
        return ctx;
    }

    private static Website generateWebsite(final int i, final int nNodes,
                                           final int minEdgesPerNode, final int maxEdgesPerNode,
                                           final EdgeDistribution edgeConfig) {
        Random r = new Random(i);

        Website site = new Website(i);

        final int nEdges;
        switch (edgeConfig) {
            case INCREASING:
                double frac = (double) i / (double) nNodes;
                double offset = (double) (maxEdgesPerNode - minEdgesPerNode) * frac;
                nEdges = minEdgesPerNode + (int) offset;
                break;
            case RANDOM:
                nEdges = minEdgesPerNode + r.nextInt(maxEdgesPerNode - minEdgesPerNode);
                break;
            case UNIFORM:
                nEdges = maxEdgesPerNode;
                break;
            default:
                throw new RuntimeException();
        }

        for (int j = 0; j < nEdges; j++) {
            site.addEdge(r.nextInt(nNodes));
        }

        return site;
    }

    private static JavaPairRDD<Integer, Website> generateGraphRDD(final int nNodes, final int minEdgesPerNode,
                                                                  final int maxEdgesPerNode,
                                                                  final EdgeDistribution edgeConfig,
                                                                  final JavaSparkContext context) {
        List<Integer> nodes = new ArrayList<Integer>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            nodes.add(i);
        }

        return context.parallelize(nodes).mapToPair(i -> new Tuple2<Integer, Website>(i,
                generateWebsite(i, nNodes, minEdgesPerNode, maxEdgesPerNode, edgeConfig)));
    }

    private static JavaPairRDD<Integer, Double> generateRankRDD(final int nNodes, final JavaSparkContext context) {
        List<Integer> nodes = new ArrayList<Integer>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            nodes.add(i);
        }

        return context.parallelize(nodes).mapToPair(i -> {
            Random rand = new Random(i);
            return new Tuple2<Integer, Double>(i, 100.0 * rand.nextDouble());
        });
    }

    private static Website[] generateGraphArr(final int nNodes, final int minEdgesPerNode, final int maxEdgesPerNode,
                                              final EdgeDistribution edgeConfig) {
        Website[] sites = new Website[nNodes];
        for (int i = 0; i < sites.length; i++) {
            sites[i] = generateWebsite(i, nNodes, minEdgesPerNode, maxEdgesPerNode, edgeConfig);
        }
        return sites;
    }

    private static double[] generateRankArr(final int nNodes) {
        double[] ranks = new double[nNodes];
        for (int i = 0; i < ranks.length; i++) {
            Random r = new Random(i);
            ranks[i] = 100.0 * r.nextDouble();
        }
        return ranks;
    }

    static void testDriver(final int nNodes, final int minEdgesPerNode, final int maxEdgesPerNode,
                           final int niterations, final EdgeDistribution edgeConfig) {
        System.err.println("Running the PageRank algorithm for " + niterations +
                " iterations on a website graph of " + nNodes + " websites");
        System.err.println();

        final int repeats = 2;
        Website[] nodesArr = generateGraphArr(nNodes, minEdgesPerNode, maxEdgesPerNode, edgeConfig);
        double[] ranksArr = generateRankArr(nNodes);
        for (int i = 0; i < niterations; i++) {
            ranksArr = PageRank.seqPageRank(nodesArr, ranksArr);
        }

        final TestExecutor testExecutor = new TestExecutor(1);

        Function<Integer, List<Tuple2<Integer, Double>>> func = (cores) ->
                runPageRank(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig, repeats, cores);

        TestResults<List<Tuple2<Integer, Double>>> pageRankResults = testExecutor.execute(1, func);
        TestResults<List<Tuple2<Integer, Double>>> pageRankMultipleResults = testExecutor.execute(nCores, func);

        Map<Integer, Double> keyed = new HashMap<Integer, Double>();
        List<Tuple2<Integer, Double>> tuple2List = pageRankMultipleResults.getResults().get(0);
        for (Tuple2<Integer, Double> site : tuple2List) {
            assert (!keyed.containsKey(site._1()));
            keyed.put(site._1(), site._2());
        }

        assertEquals(nodesArr.length, tuple2List.size());
        for (int i = 0; i < tuple2List.size(); i++) {
            assertTrue(keyed.containsKey(nodesArr[i].getId()));
            final double delta = Math.abs(ranksArr[i] - keyed.get(nodesArr[i].getId()));
            assertTrue(delta < 1E-9);
        }

        double singleElapsed = pageRankResults.getExecutionTime(), parElapsed = pageRankMultipleResults.getExecutionTime();
        double speedup = singleElapsed / parElapsed;
        System.err.println("Single-core execution ran in " + singleElapsed + " ms");
        System.err.println(nCores + "-core execution ran in " + parElapsed + " ms, yielding a speedup of " + speedup + "x");
        System.err.println();

        final double expectedSpeedup = 1.35;
        final String msg = "Expected at least " + expectedSpeedup + "x speedup, but only saw " + speedup +
                "x. Sequential time = " + singleElapsed + " ms, parallel time = " + parElapsed + " ms";
        assertTrue(msg, speedup >= expectedSpeedup);
    }

    private static List<Tuple2<Integer, Double>> runPageRank(int nNodes, int minEdgesPerNode, int maxEdgesPerNode,
                                                             int niterations, EdgeDistribution edgeConfig, int repeats,
                                                             int nCores) {
        JavaSparkContext context = getSparkContext(nCores);
        JavaPairRDD<Integer, Website> nodes = null;
        JavaPairRDD<Integer, Double> ranks = null;

        List<Tuple2<Integer, Double>> parResult = null;
        for (int r = 0; r < repeats; r++) {
            nodes = generateGraphRDD(nNodes, minEdgesPerNode, maxEdgesPerNode, edgeConfig, context);
            ranks = generateRankRDD(nNodes, context);
            for (int i = 0; i < niterations; i++) {
                ranks = PageRank.sparkPageRank(nodes, ranks);
            }
            parResult = ranks.collect();
        }
        context.stop();
        return parResult;
    }

    public void testUniformTwentyThousand() {
        final int nNodes = 20000;
        final int minEdgesPerNode = 20;
        final int maxEdgesPerNode = 40;
        final int niterations = 5;
        final EdgeDistribution edgeConfig = EdgeDistribution.UNIFORM;

        testDriver(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig);
    }

    public void testUniformFiftyThousand() {
        final int nNodes = 50000;
        final int minEdgesPerNode = 20;
        final int maxEdgesPerNode = 40;
        final int niterations = 5;
        final EdgeDistribution edgeConfig = EdgeDistribution.UNIFORM;

        testDriver(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig);
    }

    public void testIncreasingTwentyThousand() {
        final int nNodes = 20000;
        final int minEdgesPerNode = 20;
        final int maxEdgesPerNode = 40;
        final int niterations = 5;
        final EdgeDistribution edgeConfig = EdgeDistribution.INCREASING;

        testDriver(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig);
    }

    public void testIncreasingFiftyThousand() {
        final int nNodes = 50000;
        final int minEdgesPerNode = 20;
        final int maxEdgesPerNode = 40;
        final int niterations = 5;
        final EdgeDistribution edgeConfig = EdgeDistribution.INCREASING;

        testDriver(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig);
    }

    public void testRandomTwentyThousand() {
        final int nNodes = 20000;
        final int minEdgesPerNode = 20;
        final int maxEdgesPerNode = 40;
        final int niterations = 5;
        final EdgeDistribution edgeConfig = EdgeDistribution.RANDOM;

        testDriver(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig);
    }

    public void testRandomFiftyThousand() {
        final int nNodes = 50000;
        final int minEdgesPerNode = 20;
        final int maxEdgesPerNode = 40;
        final int niterations = 5;
        final EdgeDistribution edgeConfig = EdgeDistribution.RANDOM;

        testDriver(nNodes, minEdgesPerNode, maxEdgesPerNode, niterations, edgeConfig);
    }

    private static enum EdgeDistribution {
        INCREASING,
        RANDOM,
        UNIFORM
    }
}
