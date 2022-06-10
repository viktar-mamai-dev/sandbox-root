package edu.coursera.parallel.week1;

import java.util.concurrent.RecursiveAction;

/**
 * This class stub can be filled in to implement the body of each task
 * created to perform reciprocal array sum in parallel.
 */
public class ReciprocalArraySumTask extends RecursiveAction {
    private final static int THRESHOLD = 10_000;
    /**
     * Starting index for traversal done by this task.
     */
    private final int startIndexInclusive;
    /**
     * Ending index for traversal done by this task.
     */
    private final int endIndexExclusive;
    /**
     * Input array to reciprocal sum.
     */
    private final double[] input;
    /**
     * Intermediate value produced by this task.
     */
    private double value;

    /**
     * Constructor.
     *
     * @param setStartIndexInclusive Set the starting index to begin parallel traversal at.
     * @param setEndIndexExclusive   Set ending index for parallel traversal.
     * @param setInput               Input values
     */
    ReciprocalArraySumTask(final int setStartIndexInclusive, final int setEndIndexExclusive, final double[] setInput) {
        this.startIndexInclusive = setStartIndexInclusive;
        this.endIndexExclusive = setEndIndexExclusive;
        this.input = setInput;
    }

    /**
     * Getter for the value produced by this task.
     *
     * @return Value produced by this task
     */
    public double getValue() {
        return value;
    }

    @Override
    protected void compute() {
        // TODO
        if (endIndexExclusive - startIndexInclusive <= THRESHOLD) {
            this.value = calculateSum();
            return;
        }

        int mid = (endIndexExclusive - startIndexInclusive + 1) / 2 + startIndexInclusive;
        ReciprocalArraySumTask left = new ReciprocalArraySumTask(startIndexInclusive, mid, this.input);
        ReciprocalArraySumTask right = new ReciprocalArraySumTask(mid, endIndexExclusive, this.input);
        left.fork();
        right.compute();
        left.join();
        this.value = left.value + right.value;
    }

    private double calculateSum() {
        double sum = 0d;
        for (int i = startIndexInclusive; i < endIndexExclusive; i++) {
            sum += 1 / this.input[i];
        }
        return sum;
    }
}
