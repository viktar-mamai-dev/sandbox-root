package com.mamay.multithreading.jmp2022.task1;

import com.mamay.multithreading.jmp2022.Stoppable;
import com.mamay.multithreading.jmp2022.ThreadUtil;
import java.util.Map;

public class SummingSyncThread extends Stoppable {
  private final Map<Integer, Integer> map;

  public SummingSyncThread(Map<Integer, Integer> map) {
    this.map = map;
  }

  @Override
  public void run() {
    while (runFlag) {
      long sumValues = 0L;
      synchronized (this.map) { // required, without it - CMException
        for (Integer key : map.keySet()) {
          sumValues += map.get(key);
        }
      }
      ThreadUtil.sleep(100);
    }
  }
}
