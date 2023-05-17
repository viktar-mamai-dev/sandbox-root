package com.mamay.leetcode.practice.medium1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group_1282 {
  public List<List<Integer>> groupThePeople(int[] groupSizes) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < groupSizes.length; i++) {
      int g = groupSizes[i];
      List<Integer> valueList = map.get(g);
      if (valueList == null) valueList = new ArrayList<>();
      valueList.add(i);
      map.put(g, valueList);
    }
    for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
      List<Integer> valueList = entry.getValue();
      Integer key = entry.getKey();
      int groupCount = valueList.size() / key;
      int idx = 0;
      for (int i = 1; i <= groupCount; i++) {
        List<Integer> group = new ArrayList<>();
        int k;
        for (k = idx; k < Math.min(valueList.size(), idx + key); k++) {
          group.add(valueList.get(k));
        }
        list.add(group);
        idx = k;
      }
    }
    return list;
  }
}
