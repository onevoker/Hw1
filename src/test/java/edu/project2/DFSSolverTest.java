package edu.project2;

import edu.project2.Solvers.DFSSolver;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DFSSolverTest {
    private static final DFSSolver DFS_SOLVER = new DFSSolver();

    @Test
    void testMazeWithoutPath() {
        int[][] myMaze = new int[7][7];
        for (int[] row : myMaze) {
            Arrays.fill(row, 1);
        }

        assertAll(
            () -> {
                Maze mazeWithoutPath = new Maze(myMaze);
                Coordinate start = new Coordinate(1, 1);
                Coordinate end = new Coordinate(5, 5);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> DFS_SOLVER.solve(start, end, mazeWithoutPath));

                assertThat(exception.getMessage()).isEqualTo("You can't move to walls");
            },
            () -> {
                myMaze[1][1] = 0;
                myMaze[4][4] = 0;

                Maze mazeWithoutPath = new Maze(myMaze);
                Coordinate start = new Coordinate(1, 1);
                Coordinate end = new Coordinate(4, 4);
                List<Coordinate> result = DFS_SOLVER.solve(start, end, mazeWithoutPath);

                assertThat(result).isEmpty();
            }
        );
    }

    @Test
    void testSolve() {
        assertAll(
            () -> {
                int[][] myMaze = new int[][] {
                    {0, 0, 0, 1},
                    {1, 1, 0, 1},
                    {0, 1, 0, 1},
                    {0, 0, 0, 1}
                };
                Maze maze = new Maze(myMaze);
                Coordinate start = new Coordinate(0, 0);
                Coordinate end = new Coordinate(2, 0);
                List<Coordinate> result = DFS_SOLVER.solve(start, end, maze);

                List<Coordinate> expected = List.of(
                    new Coordinate(0, 0),
                    new Coordinate(0, 1),
                    new Coordinate(0, 2),
                    new Coordinate(1, 2),
                    new Coordinate(2, 2),
                    new Coordinate(3, 2),
                    new Coordinate(3, 1),
                    new Coordinate(3, 0),
                    new Coordinate(2, 0)
                );

                assertThat(result).isEqualTo(expected);
            },
            () -> {
                int[][] myMaze = new int[][] {
                    {0, 1, 1, 1},
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
                };
                Maze maze = new Maze(myMaze);
                Coordinate start = new Coordinate(0, 0);
                Coordinate end = new Coordinate(1, 3);
                List<Coordinate> result = DFS_SOLVER.solve(start, end, maze);

                List<Coordinate> expected = List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(2, 0),
                    new Coordinate(3, 0),
                    new Coordinate(3, 1),
                    new Coordinate(3, 2),
                    new Coordinate(3, 3),
                    new Coordinate(2, 3),
                    new Coordinate(1, 3)

                    );

                assertThat(result).isEqualTo(expected);
            }
        );
    }
    @Test
    void testIncorrectCoordinates() {
        int[][] myMaze = new int[][] {
            {0, 1, 1, 1},
            {0, 1, 1, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 0}
        };
        Maze maze = new Maze(myMaze);
        assertAll(
            () -> {
                Coordinate start = new Coordinate(-1, 0);
                Coordinate end = new Coordinate(1, 0);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> DFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            },
            () -> {
                Coordinate start = new Coordinate(2, -1);
                Coordinate end = new Coordinate(0, 0);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> DFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            },
            () -> {
                Coordinate start = new Coordinate(1000, 1000);
                Coordinate end = new Coordinate(0, 0);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> DFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            },
            () -> {
                Coordinate start = new Coordinate(100, 100);
                Coordinate end = new Coordinate(-5, -5);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> DFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            }
        );
    }
}
