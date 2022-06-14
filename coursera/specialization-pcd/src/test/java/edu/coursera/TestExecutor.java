package edu.coursera;

import java.util.function.Function;
import java.util.function.Supplier;

public class TestExecutor {

    private final int nRepeats;

    public TestExecutor(int nRepeates) {
        this.nRepeats = nRepeates;
    }

    public <T> TestResults<T> execute(Supplier<T> supplier) {
        long startTime = System.currentTimeMillis();
        TestResults<T> testResults = new TestResults<>(nRepeats);
        for (int i = 0; i < nRepeats; i++) {
            T result = supplier.get();
            testResults.add(result);
        }
        testResults.setExecutionTime(System.currentTimeMillis() - startTime);
        return testResults;
    }

    public <S, T> TestResults<T> execute(S input, Function<S, T> func) {
        long startTime = System.currentTimeMillis();
        TestResults<T> testResults = new TestResults<>(nRepeats);
        for (int i = 0; i < nRepeats; i++) {
            T result = func.apply(input);
            testResults.add(result);
        }
        testResults.setExecutionTime(System.currentTimeMillis() - startTime);
        return testResults;
    }
}
