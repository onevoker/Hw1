package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Direction;
import edu.project2.Maze;
import edu.project2.ValidationChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DFSSolver extends ValidationChecker implements Solver {
    private static final int TWO = 2;

    @Override
    public List<Coordinate> solve(Coordinate start, Coordinate end, Maze maze) {
        if (!isCorrectStartAndEnd(start, end, maze)) {
            throw new IllegalArgumentException("Incorrect Coordinates");
        }
        if (isStartOrEndIsWall(start, end, maze)) {
            throw new IllegalArgumentException("You can't move to walls");
        }

        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];

        ForkJoinPool pool = new ForkJoinPool();
        List<Coordinate> path = pool.invoke(new ParallelDFSTask(start, end, maze, visited));

        for (Coordinate coordinate : path) {
            maze.getMaze()[coordinate.row()][coordinate.col()] = Cell.PATH.getValue();
        }

        return path.isEmpty() ? List.of() : new ArrayList<>(path).reversed();
    }

    private class ParallelDFSTask extends RecursiveTask<List<Coordinate>> {
        private final Coordinate current;
        private final Coordinate end;
        private final Maze maze;
        private final boolean[][] visited;

        private ParallelDFSTask(Coordinate current, Coordinate end, Maze maze, boolean[][] visited) {
            this.current = current;
            this.end = end;
            this.maze = maze;
            this.visited = visited;
        }

        @Override
        protected List<Coordinate> compute() {
            List<Coordinate> path = new CopyOnWriteArrayList<>();
            int row = current.row();
            int col = current.col();

            if (row == end.row() && col == end.col()) {
                path.add(current);
            } else {
                visited[row][col] = true;

                List<ParallelDFSTask> tasks = new ArrayList<>();

                for (Direction direction : Direction.values()) {
                    int newRow = row + direction.row / TWO;
                    int newCol = col + direction.col / TWO;

                    if (isInRangeOfMaze(newRow, newCol, maze) && maze.getMaze()[newRow][newCol] == 0
                        && !visited[newRow][newCol]) {

                        ParallelDFSTask task = new ParallelDFSTask(
                            new Coordinate(newRow, newCol),
                            end,
                            maze,
                            visited
                        );

                        tasks.add(task);
                        task.fork();
                    }
                }

                for (var task : tasks) {
                    List<Coordinate> subPath = task.join();
                    if (!subPath.isEmpty()) {
                        path.addAll(subPath);
                        path.add(current);

                        return path;
                    }
                }

            }
            return path;
        }
    }
}
