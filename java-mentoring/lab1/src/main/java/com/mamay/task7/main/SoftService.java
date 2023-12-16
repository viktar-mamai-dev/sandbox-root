package com.mamay.task7.main;

import com.mamay.task7.model.BasicSoft;
import java.util.List;

public class SoftService {
  public double calculateTotalPrice(List<BasicSoft> programs) {
    return programs.stream().mapToDouble(BasicSoft::getPrice).sum();
  }

  public double calculateTotalSize(List<BasicSoft> programs) {
    return programs.stream().mapToDouble(BasicSoft::getSize).sum();
  }
}
