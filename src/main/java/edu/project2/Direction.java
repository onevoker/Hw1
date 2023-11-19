package edu.project2;

public enum Direction {
    UP(-2, 0),
    DOWN(2, 0),
    LEFT(0, -2),
    RIGHT(0, 2);

    public final int row;
    public final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
