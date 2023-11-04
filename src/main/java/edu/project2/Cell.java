package edu.project2;

public enum Cell {

    PASSAGE(0),
    WALL(1),
    CHECKED(3),
    PATH(5);
    private final int value;

    Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
