package edu.project2;

import edu.project2.Solvers.BFSSolver;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BFSSolverTest {
    private static final BFSSolver BFS_SOLVER = new BFSSolver();

    @Test
    void testMazeWithoutPath() {
        int[][] myMaze = new int[10][10];
        for (int[] row : myMaze) {
            Arrays.fill(row, 1);
        }

        assertAll(
            () -> {
                Maze mazeWithoutPath = new Maze(myMaze);
                Coordinate start = new Coordinate(1, 1);
                Coordinate end = new Coordinate(8, 8);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> BFS_SOLVER.solve(start, end, mazeWithoutPath));

                assertThat(exception.getMessage()).isEqualTo("You can't move to walls");
            },
            () -> {
                myMaze[1][1] = 0;
                myMaze[5][4] = 0;

                Maze mazeWithoutPath = new Maze(myMaze);
                Coordinate start = new Coordinate(1, 1);
                Coordinate end = new Coordinate(5, 4);
                List<Coordinate> result = BFS_SOLVER.solve(start, end, mazeWithoutPath);

                assertThat(result).isEmpty();
            }
        );
    }

    @Test
    void testSolve() {
        assertAll(
            () -> {
                int[][] myMaze = new int[][] {
                    {1, 1, 1, 0},
                    {1, 1, 0, 0},
                    {1, 1, 0, 1},
                    {1, 1, 0, 0}
                };
                Maze maze = new Maze(myMaze);
                Coordinate start = new Coordinate(0, 3);
                Coordinate end = new Coordinate(3, 3);
                List<Coordinate> result = BFS_SOLVER.solve(start, end, maze);

                List<Coordinate> expected = List.of(
                    new Coordinate(0, 3),
                    new Coordinate(1, 3),
                    new Coordinate(1, 2),
                    new Coordinate(2, 2),
                    new Coordinate(3, 2),
                    new Coordinate(3, 3)
                );

                assertThat(result).isEqualTo(expected);
            },
            () -> {
                int[][] myMaze = new int[][] {
                    {0, 1, 1, 1},
                    {0, 1, 1, 1},
                    {0, 1, 1, 1},
                    {0, 0, 0, 0}
                };
                Maze maze = new Maze(myMaze);
                Coordinate start = new Coordinate(0, 0);
                Coordinate end = new Coordinate(3, 3);
                List<Coordinate> result = BFS_SOLVER.solve(start, end, maze);

                List<Coordinate> expected = List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(2, 0),
                    new Coordinate(3, 0),
                    new Coordinate(3, 1),
                    new Coordinate(3, 2),
                    new Coordinate(3, 3)

                );

                assertThat(result).isEqualTo(expected);
            }
        );
    }

    @Test
    void testIncorrectCoordinates() {
        int[][] myMaze = new int[][] {
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 1, 1, 0},
            {1, 1, 1, 0}
        };
        Maze maze = new Maze(myMaze);
        assertAll(
            () -> {
                Coordinate start = new Coordinate(-3, 0);
                Coordinate end = new Coordinate(0, 2);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> BFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            },
            () -> {
                Coordinate start = new Coordinate(0, -3);
                Coordinate end = new Coordinate(0, 1);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> BFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            },
            () -> {
                Coordinate start = new Coordinate(42, 42);
                Coordinate end = new Coordinate(0, 1);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> BFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            },
            () -> {
                Coordinate start = new Coordinate(33, 33);
                Coordinate end = new Coordinate(-3, -3);
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> BFS_SOLVER.solve(start, end, maze));

                assertThat(exception.getMessage()).isEqualTo("Incorrect Coordinates");
            }
        );
    }
}
