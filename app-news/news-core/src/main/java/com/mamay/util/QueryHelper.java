package com.mamay.util;

import java.util.List;
import java.util.stream.Collectors;

public class QueryHelper {

  public static String convertListToString(List<Long> tagIdList) {
    return tagIdList.stream().map(String::valueOf).collect(Collectors.joining(", "));
  }
}
