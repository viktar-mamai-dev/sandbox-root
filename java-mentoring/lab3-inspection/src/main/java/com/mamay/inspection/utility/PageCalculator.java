package com.mamay.inspection.utility;

import com.mamay.inspection.service.MagazineService;

public class PageCalculator {
  public static final int DEFAULT_PER_PAGE = 9;

  public static int findPageCount() {
    int recordCount = MagazineService.calculateCount();
    return (recordCount - 1) / DEFAULT_PER_PAGE + 1;
  }
}
