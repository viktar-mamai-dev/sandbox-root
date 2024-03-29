package part1.week1.iq;

/**
 * Egg drop. Suppose that you have an n-story building (with floors 1 through nn) and plenty of
 * eggs. An egg breaks if it is dropped from floor TT or higher and does not break otherwise. Return
 * minimum number of eggs needed to define the floor in general case
 */
public class Task6 {

  public int eggDrop(int buildingHeight, int eggCount) {
    int[][] dp = new int[eggCount + 1][buildingHeight + 1];
    int dropCount = 0; // Number of moves
    while (dp[dropCount][buildingHeight] < eggCount) {
      dropCount++;
      for (int x = 1; x <= buildingHeight; x++) {
        dp[dropCount][x] = 1 + dp[dropCount - 1][x - 1] + dp[dropCount - 1][x];
      }
    }
    return dropCount;
  }
}
