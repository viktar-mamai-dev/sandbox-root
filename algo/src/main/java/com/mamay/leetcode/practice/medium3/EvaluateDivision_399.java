package com.mamay.leetcode.practice.medium3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluateDivision_399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        List<String> vertices = new ArrayList<>();
        List<List<Double>> edges = new ArrayList<>();

        for (List<String> equation : equations) {
            for (String variable : equation) {
                if (!vertices.contains(variable)) {
                    vertices.add(variable);
                }
            }
        }

        int vertLen = vertices.size();
        Double[][] graph = new Double[vertLen][vertLen];

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String s1 = equation.get(0);
            String s2 = equation.get(1);

            int idx1 = vertices.indexOf(s1);
            int idx2 = vertices.indexOf(s2);

            graph[idx1][idx2] = values[i];
            graph[idx2][idx1] = 1 / values[i];
        }

        int qLen = queries.size();
        double answer[] = new double[qLen];
        for (int i = 0; i < qLen; i++) {
            double val = solve(vertices, graph, queries.get(i));
            answer[i] = val != 0 ? val : -1;
        }
        return answer;
    }

    public Double solve(List<String> vertices, Double[][] graph, List<String> query) {
        int idx1 = vertices.indexOf(query.get(0));
        if (idx1 == -1) return 0d;
        int idx2 = vertices.indexOf(query.get(1));
        if (idx2 == -1) return 0d;

        boolean used[] = new boolean[vertices.size()];
        Arrays.fill(used, false);
        return solve(vertices, graph, idx1, idx2, used);


    }

    public Double solve(List<String> vertices, Double[][] graph, int idx1, int idx2, boolean used[]) {
        if (idx1 == idx2) return 1d;

        if (graph[idx1][idx2] != null) return graph[idx1][idx2];

        used[idx1] = true;
        for (int idx3 = 0; idx3 < vertices.size(); idx3++) {
            if (idx3 != idx1 && !used[idx3] && graph[idx1][idx3] != null) {
                Double val1 = graph[idx1][idx3] * solve(vertices, graph, idx3, idx2, used);
                if (val1 != 0) {
                    return val1;
                }
            }
        }
        return 0d;
    }


}
