package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Direction;
import edu.project2.Maze;
import edu.project2.ValidationChecker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFSSolver extends ValidationChecker implements Solver {
    private final static int TWO = 2;

    @Override
    public List<Coordinate> solve(Coordinate start, Coordinate end, Maze maze) {
        if (!isCorrectStartAndEnd(start, end, maze)) {
            throw new IllegalArgumentException("Incorrect Coordinates");
        }
        if (isStartOrEndIsWall(start, end, maze)) {
            throw new IllegalArgumentException("You can't move to walls");
        }

        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        List<Coordinate> path = new ArrayList<>();

        dfs(start, end, maze, visited, path);

        for (Coordinate coordinate : path) {
            maze.getMaze()[coordinate.row()][coordinate.col()] = Cell.PATH.getValue();
        }

        return path.isEmpty() ? Collections.emptyList() : path.reversed();
    }

    private boolean dfs(Coordinate current, Coordinate end, Maze maze, boolean[][] visited, List<Coordinate> path) {
        int row = current.row();
        int col = current.col();

        if (row == end.row() && col == end.col()) {
            path.add(current);
            return true;
        }

        visited[row][col] = true;

        for (Direction direction : Direction.values()) {
            int newRow = row + direction.row / TWO;
            int newCol = col + direction.col / TWO;

            if (isInRangeOfMaze(newRow, newCol, maze) && maze.getMaze()[newRow][newCol] == 0
                && !visited[newRow][newCol]) {

                if (dfs(new Coordinate(newRow, newCol), end, maze, visited, path)) {
                    path.add(current);
                    return true;
                }
            }
        }
        return false;
    }
}
