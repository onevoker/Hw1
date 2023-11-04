package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Direction;
import edu.project2.Maze;
import edu.project2.ValidationChecker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSolver extends ValidationChecker implements Solver {
    private static final int TWO = 2;

    @Override
    public List<Coordinate> solve(Coordinate start, Coordinate end, Maze maze) {
        if (!isCorrectStartAndEnd(start, end, maze)) {
            throw new IllegalArgumentException("Incorrect Coordinates");
        }
        if (isStartOrEndIsWall(start, end, maze)) {
            throw new IllegalArgumentException("You can't move to walls");
        }

        Queue<List<Coordinate>> ways = new LinkedList<>();
        ways.add(new ArrayList<>(List.of(start)));
        maze.getMaze()[start.row()][start.col()] = Cell.CHECKED.getValue();

        while (!ways.isEmpty()) {
            List<Coordinate> current = ways.remove();
            int currentRow = current.getLast().row();
            int currentCol = current.getLast().col();

            for (Direction direction : Direction.values()) {
                int newRow = currentRow + direction.row / TWO;
                int newCol = currentCol + direction.col / TWO;

                if (isInRangeOfMaze(newRow, newCol, maze)
                    && maze.getMaze()[newRow][newCol] == Cell.PASSAGE.getValue()) {

                    List<Coordinate> path = new ArrayList<>(current);
                    path.add(new Coordinate(newRow, newCol));
                    maze.getMaze()[newRow][newCol] = Cell.CHECKED.getValue();

                    if (newRow == end.row() && newCol == end.col()) {
                        for (Coordinate coordinate : path) {
                            maze.getMaze()[coordinate.row()][coordinate.col()] = Cell.PATH.getValue();
                        }

                        return path;
                    }
                    ways.add(path);
                }
            }
        }
        return Collections.emptyList();
    }
}
