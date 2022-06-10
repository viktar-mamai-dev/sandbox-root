package edu.coursera.concurrent.week3;

import edu.rice.pcdp.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * An actor class that helps implement the Sieve of Eratosthenes in parallel.
 */
public final class SieveActorActor extends Actor {
    private static final int MAX_LOCAL_PRIMES = 500;
    private List<Integer> primes;
    private int numPrimes;
    private SieveActorActor nextActor;

    public SieveActorActor(final int localPrime) {
        primes = new ArrayList<>();
        primes.add(localPrime);
        this.nextActor = null;
        this.numPrimes = 1;
    }

    public int getNumPrimes() {
        return numPrimes;
    }

    public SieveActorActor getNextActor() {
        return nextActor;
    }

    /**
     * Process a single message sent to this actor.
     * <p>
     * TODO complete this method.
     *
     * @param msg Received message
     */
    @Override
    public void process(final Object msg) {
        final int candidate = (Integer) msg;
        if (candidate <= 0) {

        } else {
            final boolean locallyPrime = isLocallyPrime(candidate);
            if (locallyPrime) {
                if (primes.size() <= MAX_LOCAL_PRIMES) {
                    primes.add(candidate);
                    numPrimes++;
                } else if (nextActor == null) {
                    nextActor = new SieveActorActor(candidate);
                } else {
                    nextActor.send(msg);
                }
            }
        }
    }

    private boolean isLocallyPrime(final Integer candidate) {
        return primes
                .stream()
                //.parallel()
                .noneMatch(prime -> candidate % prime == 0);
    }
}
