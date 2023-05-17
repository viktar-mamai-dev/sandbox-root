package com.mamay.leetcode.practice.medium1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SQL {

    private HashMap<String, HashMap<Integer, List<String>>> map;

    public SQL(List<String> names, List<Integer> columns) {
        map = new HashMap<>();
        for (String name : names) {
            map.put(name, new HashMap<>());
        }
    }

    public void insertRow(String name, List<String> row) {
        HashMap<Integer, List<String>> rowToValues = map.get(name);
        int rowId = rowToValues.size();
        rowToValues.put(rowId + 1, row);
    }

    public void deleteRow(String name, int rowId) {
        map.get(name).put(rowId, new ArrayList<>());
    }

    public String selectCell(String name, int rowId, int columnId) {
        List<String> values = map.get(name).get(rowId);
        if (values.isEmpty()) return null;
        return values.get(columnId);
    }
}