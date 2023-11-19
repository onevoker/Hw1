package edu.project2.Generators;

import edu.project2.Maze;

public interface Generator {
    void initializeMaze(Maze maze);

    void generateMaze(Maze maze);
}
