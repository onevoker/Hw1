package edu.project2.Generators;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.Random;

public class RecursiveDivisionGenerator implements Generator {
    private final static int TWO = 2;
    private final static Random RANDOM = new Random();

    @Override
    public void initializeMaze(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                maze.getMaze()[i][j] = 1;
            }
        }
    }

    @Override
    public void generateMaze(Maze maze) {
        initializeMaze(maze);

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(maze.getHeight() - 1, maze.getWidth() - 1);

        divide(start, end, maze);
    }

    private void divide(Coordinate start, Coordinate end, Maze maze) {
        int row1 = start.row();
        int col1 = start.col();
        int row2 = end.row();
        int col2 = end.col();

        if (row2 - row1 < TWO || col2 - col1 < TWO) {
            return;
        }

        int wallRow = RANDOM.nextInt((row2 - row1) / TWO) * TWO + row1 + 1;
        int wallCol = RANDOM.nextInt((col2 - col1) / TWO) * TWO + col1 + 1;

        for (int row = row1; row <= row2; row++) {
            for (int col = col1; col <= col2; col++) {
                if (row == wallRow || col == wallCol) {
                    maze.getMaze()[row][col] = 1;
                } else {
                    maze.getMaze()[row][col] = 0;
                }
            }
        }

        int gapRow = RANDOM.nextInt((row2 - row1) / TWO) * TWO + row1 + 1;
        int gapCol = RANDOM.nextInt((col2 - col1) / TWO) * TWO + col1 + 1;
        maze.getMaze()[gapRow][gapCol] = 0;

        divide(new Coordinate(row1, col1), new Coordinate(wallRow - 1, wallCol - 1), maze);
        divide(new Coordinate(wallRow + 1, col1), new Coordinate(row2, wallCol - 1), maze);
        divide(new Coordinate(row1, wallCol + 1), new Coordinate(wallRow - 1, col2), maze);
        divide(new Coordinate(wallRow + 1, wallCol + 1), new Coordinate(row2, col2), maze);
    }
}
