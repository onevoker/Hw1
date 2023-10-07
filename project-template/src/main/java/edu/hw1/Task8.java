package edu.hw1;

public class Task8 {

    private static final int TWO = 2;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        int[][] moves = new int[][] {
            {1, TWO}, {1, -TWO}, {-1, TWO}, {-1, -TWO},
            {TWO, 1}, {TWO, -1}, {-TWO, 1}, {-TWO, -1}
        };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : moves) {
                        int row = i + move[0];
                        int column = j + move[1];
                        if (row >= 0 && row < board.length && column >= 0 && column < board.length
                            && board[row][column] == 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
