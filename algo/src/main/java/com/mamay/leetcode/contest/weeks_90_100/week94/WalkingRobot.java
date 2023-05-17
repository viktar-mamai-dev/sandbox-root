package com.mamay.leetcode.contest.weeks_90_100.week94;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WalkingRobot {

  public static int solve(int[] commands, int[][] obstacles) {
    int xCurrent = 0, yCurrent = 0, xNext = 0, yNext = 0;
    // 0 top, 1 - right, 2 - bottom, 3 - tothe left
    int direction = 10000;
    for (int com : commands) {
      if (com == -1) {
        direction++;
      } else if (com == -2) {
        direction--;
      } else {
        if (direction % 4 == 1) xNext = xCurrent + com;
        else if (direction % 4 == 3) xNext = xCurrent - com;
        else if (direction % 4 == 0) yNext = yCurrent + com;
        else if (direction % 4 == 2) yNext = yCurrent - com;

        for (int[] obstacle : obstacles) {
          if (direction % 4 == 0) {
            if (obstacle[0] == xCurrent && obstacle[1] > yCurrent) {
              yNext = Math.min(obstacle[1] - 1, yNext);
            }
          } else if (direction % 4 == 2) {
            if (obstacle[0] == xCurrent && obstacle[1] < yCurrent) {
              yNext = Math.max(obstacle[1] + 1, yNext);
            }
          } else if (direction % 4 == 1) {
            if (obstacle[1] == yCurrent && obstacle[0] > xCurrent) {
              xNext = Math.min(obstacle[0] - 1, xNext);
            }
          } else if (direction % 4 == 3) {
            if (obstacle[1] == yCurrent && obstacle[0] < xCurrent) {
              xNext = Math.max(obstacle[0] + 1, xNext);
            }
          }
        }
        xCurrent = xNext;
        yCurrent = yNext;
      }
    }
    return xCurrent * xCurrent + yCurrent * yCurrent;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File("input.txt"));
    String[] line = scanner.nextLine().split(",");
    int N = line.length;
    int commands[] = new int[N];
    for (int i = 0; i < N; i++) {
      commands[i] = Integer.parseInt(line[i]);
    }
    line = scanner.nextLine().split(",");
    N = line.length / 2;
    int obstacles[][] = new int[N][2];
    for (int i = 0; i < N; i++) {
      obstacles[i][0] = Integer.parseInt(line[i * 2]);
      obstacles[i][1] = Integer.parseInt(line[i * 2 + 1]);
    }

    System.out.println(solve(commands, obstacles));
    scanner.close();
  }
}
