package practice.hard;

import com.mamay.leetcode.practice.hard.FlowersFullBloom;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class FlowersFullBloomTest {

  @Test
  void fullBloomFlowers() {
    FlowersFullBloom main = new FlowersFullBloom();
    System.out.println(
        Arrays.toString(
            main.fullBloomFlowers(
                new int[][] {{1, 6}, {3, 7}, {9, 12}, {4, 13}}, new int[] {2, 3, 7, 11})));

    System.out.println(
        Arrays.toString(main.fullBloomFlowers(new int[][] {{1, 10}, {3, 3}}, new int[] {3, 3, 2})));
  }
}
