package edu.coursera.concurrent.week3;

import edu.rice.pcdp.PCDP;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 *
 * <p>TODO Fill in the empty SieveActorActor actor class below and use it from countPrimes to
 * determine the number of primes <= limit.
 */
public final class SieveActor extends Sieve {
  /**
   * {@inheritDoc}
   *
   * <p>TODO Use the SieveActorActor class to calculate the number of primes <= limit in parallel.
   * You might consider how you can model the Sieve of Eratosthenes as a pipeline of actors, each
   * corresponding to a single prime number.
   */
  @Override
  public int countPrimes(final int limit) {
    final SieveActorActor sieveActor = new SieveActorActor(2);
    PCDP.finish(
        () -> {
          for (int i = 3; i <= limit; i += 2) {
            sieveActor.send(i);
          }
          sieveActor.send(0);
        });

    int numPrimes = 0;
    SieveActorActor loopActor = sieveActor;
    while (loopActor != null) {
      numPrimes += loopActor.getNumPrimes();
      loopActor = loopActor.getNextActor();
    }
    return numPrimes;
  }
}
