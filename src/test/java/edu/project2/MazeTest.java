package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeTest {

    @Test
    void testGettersOfSize() {
        Maze maze = new Maze(5, 4);
        assertAll(
            () -> assertThat(maze.getHeight()).isEqualTo(5),
            () -> assertThat(maze.getWidth()).isEqualTo(4)
        );
    }

    @Test
    void testGetPath() {
        int[][] myMaze1 = new int[][] {
            {0, 1, 1, 1},
            {0, 0, 1, 1},
            {1, 0, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        Maze maze1 = new Maze(myMaze1);
        Coordinate start1 = new Coordinate(0, 0);
        Coordinate end1 = new Coordinate(3, 2);

        List<Coordinate> result1 = maze1.getPath(start1, end1, "BFS");
        List<Coordinate> expected1 = List.of(
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(3, 1),
            new Coordinate(3, 2)
        );

        int[][] myMaze2 = new int[][] {
            {1, 0, 0, 1},
            {1, 1, 0, 1},
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 0}
        };
        Maze maze2 = new Maze(myMaze2);
        Coordinate start2 = new Coordinate(0, 1);
        Coordinate end2 = new Coordinate(4, 3);

        List<Coordinate> result2 = maze2.getPath(start2, end2, "DFS");
        List<Coordinate> expected2 = List.of(
            new Coordinate(0, 1),
            new Coordinate(0, 2),
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(2, 1),
            new Coordinate(2, 0),
            new Coordinate(3, 0),
            new Coordinate(4, 0),
            new Coordinate(4, 1),
            new Coordinate(4, 2),
            new Coordinate(4, 3)
        );

        assertAll(
            () -> assertThat(result1).isEqualTo(expected1),
            () -> assertThat(maze1.getHeight()).isEqualTo(5),
            () -> assertThat(maze1.getWidth()).isEqualTo(4),

            () -> assertThat(result2).isEqualTo(expected2),
            () -> assertThat(maze2.getHeight()).isEqualTo(5),
            () -> assertThat(maze2.getWidth()).isEqualTo(4)
        );
    }

    @Test
    void testGenerateMaze() {
        Maze maze = new Maze(10, 10);
        String recursiveBacktracking = "Recursive Backtracking";
        String recursiveDivision = "Recursive Division";
        String invalidTypeGeneration = "HAHA";
        assertAll(
            () -> {
                maze.generateMaze(recursiveBacktracking);
                assertThat(maze.getMaze()).isNotEmpty();
            },
            () -> {
                maze.generateMaze(recursiveDivision);
                assertThat(maze.getMaze()).isNotEmpty();
            },
            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> maze.generateMaze(invalidTypeGeneration));
                assertThat(exception.getMessage()).isEqualTo("No this generator");
            }
        );
    }

}
