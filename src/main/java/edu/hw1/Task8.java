package edu.hw1;

public class Task8 {

    private Task8() {
    }

    private static final int KNIGHT_MOVE_ROW = -2;
    private static final int KNIGHT_MOVE_COL = 1;
    private static final int BOARD_SIZE = 8;

    public static boolean knightBoardCapture(int[][] board) {
        int[][] moves = {
            {KNIGHT_MOVE_ROW, KNIGHT_MOVE_COL},
            {KNIGHT_MOVE_ROW, -KNIGHT_MOVE_COL},
            {-KNIGHT_MOVE_ROW, KNIGHT_MOVE_COL},
            {-KNIGHT_MOVE_ROW, -KNIGHT_MOVE_COL},
            {KNIGHT_MOVE_COL, KNIGHT_MOVE_ROW},
            {KNIGHT_MOVE_COL, -KNIGHT_MOVE_ROW},
            {-KNIGHT_MOVE_COL, KNIGHT_MOVE_ROW},
            {-KNIGHT_MOVE_COL, -KNIGHT_MOVE_ROW}
        };
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : moves) {
                        int newRow = i + move[0];
                        int newCol = j + move[1];

                        if (isValidMove(newRow, newCol) && board[newRow][newCol] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
}
