package edu.project2.Solvers;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Coordinate start, Coordinate end, Maze maze);
}
