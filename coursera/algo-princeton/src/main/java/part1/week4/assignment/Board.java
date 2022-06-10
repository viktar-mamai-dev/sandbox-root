package part1.week4.assignment;

import java.util.ArrayList;

public class Board {
    private final int[][] table;
    private int size;
    private int emptyI;
    private int emptyJ;
    private int manhattanSum;
    private boolean isGoal = true;

    /*public static int movesCount1 = 0;
    public static int movesCount2 = 0;
    public static long movesCount3 = 0;*/

    public Board(int[][] blocks) {
        //movesCount1++;
        size = blocks.length;
        table = new int[size][size];

        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = blocks[i][j];
                if (table[i][j] == 0) {
                    emptyI = i;
                    emptyJ = j;
                }

                if (table[i][j] != 0) {
                    int row = (table[i][j] - 1) / size;
                    int col = (table[i][j] - 1) % size;
                    sum += Math.abs(i - row) + Math.abs(j - col);
                }

                if ((i != size - 1 || j != size - 1) && table[i][j] != i * size + j + 1) {
                    isGoal = false;
                }
            }
        }
        this.manhattanSum = sum;
    }

    public static void main(String[] args) {

    }

    public int dimension() {
        //movesCount2++;
        return size;
    }

    public int hamming() {
        //movesCount2++;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += hamming(i, j);
            }
        }
        return sum;
    }

    private int hamming(int indexI, int indexJ) {
        //movesCount2++;
        int b = table[indexI][indexJ];
        int row = (b - 1) / size;
        int col = (b - 1) % size;
        if (b == 0 || (indexI == row && indexJ == col)) {
            return 0;
        }

        return 1;
    }

    public int manhattan() {
        // movesCount1++;
        return manhattanSum;
    }

    public boolean isGoal() {
        // movesCount2++;
        return isGoal;
    }

    public Board twin() {
        // movesCount2++;
        if (table[0][0] != 0) {
            if (table[0][1] != 0) {
                return twin(0, 0, 0, 1);
            }
            return twin(0, 0, 1, 0);
        }
        return twin(1, 0, 0, 1);
    }

    private Board twin(int i1, int j1, int i2, int j2) {
        // movesCount2++;
        Board board = new Board(table);
        board.swap(i1, j1, i2, j2);
        return board;
    }

    public boolean equals(Object y) {
        // movesCount2++;
        if (this == y) return true;
        if (y == null) return false;
        if (getClass() != y.getClass()) return false;

        Board that = (Board) y;

        if (this.table.length != that.table.length || this.table[0].length != that.table[0].length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (that.table[i][j] != table[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        //  movesCount3++;
        ArrayList<Board> neighbors = new ArrayList<>();
        if (emptyI > 0) {
            Board neiBoard = createNeighbor(emptyI - 1, emptyJ);
            neighbors.add(neiBoard);
        }
        if (emptyJ > 0) {
            Board neiBoard = createNeighbor(emptyI, emptyJ - 1);
            neighbors.add(neiBoard);
        }
        if (emptyI < size - 1) {
            Board neiBoard = createNeighbor(emptyI + 1, emptyJ);
            neighbors.add(neiBoard);
        }
        if (emptyJ < size - 1) {
            Board neiBoard = createNeighbor(emptyI, emptyJ + 1);
            neighbors.add(neiBoard);
        }

        return neighbors;
    }

    private Board createNeighbor(int newEmptyI, int newEmptyJ) {
        //  movesCount3++;
        Board neiBoard = twin(emptyI, emptyJ, newEmptyI, newEmptyJ);
        neiBoard.emptyJ = newEmptyJ;
        neiBoard.emptyI = newEmptyI;
        return neiBoard;
    }

    private void swap(int i1, int j1, int i2, int j2) {
        // movesCount3++;
        if (i1 == i2 && j1 == j2) {
            return;
        }
        int temp = table[i1][j1];
        table[i1][j1] = table[i2][j2];
        table[i2][j2] = temp;
    }

    public String toString() {
        //countt++;
        StringBuilder builder = new StringBuilder();
        builder.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.append(table[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}