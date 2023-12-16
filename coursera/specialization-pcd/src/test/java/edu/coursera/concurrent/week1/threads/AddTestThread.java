package edu.coursera.concurrent.week1.threads;

import edu.coursera.concurrent.week1.ListSet;
import edu.coursera.concurrent.week1.seq.SequenceGenerator;

/**
 * A Runnable class used to test the performance of each concurrent ListSet implementation. This
 * thread simply hits the list with a large number of adds.
 */
public class AddTestThread extends TestThread implements Runnable {
  public AddTestThread(final SequenceGenerator seq, final int seqToUse, final ListSet setL) {
    super(seq, seqToUse, setL);
  }

  @Override
  public void run() {
    for (Integer num : nums) {
      l.add(num);
    }
  }
}
