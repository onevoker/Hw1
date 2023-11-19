package edu.project2;

public abstract class ValidationChecker {
    public boolean isInRangeOfMaze(int row, int col, Maze maze) {
        return row >= 0 && row < maze.getHeight() && col >= 0 && col < maze.getWidth();
    }

    public boolean isStartOrEndIsWall(Coordinate start, Coordinate end, Maze maze) {
        return maze.getMaze()[start.row()][start.col()] == Cell.WALL.getValue()
            || maze.getMaze()[end.row()][end.col()] == Cell.WALL.getValue();
    }

    public boolean isCorrectStartAndEnd(Coordinate start, Coordinate end, Maze maze) {
        int startRow = start.row();
        int startCol = start.col();
        int endRow = end.row();
        int endCol = end.col();

        return isInRangeOfMaze(startRow, startCol, maze) && isInRangeOfMaze(endRow, endCol, maze);
    }
}
