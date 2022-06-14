package edu.coursera;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestResults<S> {
    private final int nRepeats;
    private final List<S> results;
    private double executionTime;

    public TestResults(int nRepeats) {
        this.nRepeats = nRepeats;
        results = new ArrayList<>(nRepeats);
    }

    public void add(S result) {
        this.results.add(result);
    }
}