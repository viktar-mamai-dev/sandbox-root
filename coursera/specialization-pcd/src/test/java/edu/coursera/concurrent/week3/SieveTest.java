package edu.coursera.concurrent.week3;

import edu.coursera.Util;
import edu.coursera.concurrent.week3.SieveActor;
import edu.coursera.concurrent.week3.SieveSequential;
import junit.framework.TestCase;

public class SieveTest extends TestCase {
    static final double expectedScalability = 1.6;
    private static final int nCores = Util.getNCores();

    private static long driver(final int limit, final int ref) {
        new SieveActor().countPrimes(limit); // warmup
        System.gc();
        new SieveActor().countPrimes(limit); // warmup
        System.gc();
        new SieveActor().countPrimes(limit); // warmup
        System.gc();

        final long parStart = System.currentTimeMillis();
        final int parCount = new SieveActor().countPrimes(limit);
        final long parElapsed = System.currentTimeMillis() - parStart;

        assertEquals("Mismatch in computed number of primes for limit " + limit, ref, parCount);
        return parElapsed;
    }

    public void testActorSieveOneHundredThousand() throws InterruptedException {
        final int limit = 100_000;
        final int ref = new SieveSequential().countPrimes(limit);

        long prev = -1;
        int cores = 2;
        while (cores <= nCores) {
            edu.rice.pcdp.runtime.Runtime.resizeWorkerThreads(cores);
            final long elapsed = driver(limit, ref);

            if (prev > 0) {
                double scalability = (double) prev / (double) elapsed;
                assertTrue(String.format("Expected scalability of %fx going from %d cores to %d cores, but found %fx",
                        expectedScalability, cores / 2, cores, scalability), scalability >= expectedScalability);
            }

            cores *= 2;
            prev = elapsed;
        }
    }


    public void testActorSieveTwoHundredThousand() throws InterruptedException {
        final int limit = 200_000;
        final int ref = new SieveSequential().countPrimes(limit);

        long prev = -1;
        int cores = 2;
        while (cores <= nCores) {
            edu.rice.pcdp.runtime.Runtime.resizeWorkerThreads(cores);
            final long elapsed = driver(limit, ref);

            if (prev > 0) {
                double scalability = (double) prev / (double) elapsed;
                assertTrue(String.format("Expected scalability of %fx going from %d cores to %d cores, but found %fx",
                        expectedScalability, cores / 2, cores, scalability), scalability >= expectedScalability);
            }

            cores *= 2;
            prev = elapsed;
        }
    }
}
