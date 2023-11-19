package edu.project2;

import edu.project2.Generators.RecursiveBacktrackingGenerator;
import edu.project2.Generators.RecursiveDivisionGenerator;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import java.util.List;

public final class Maze {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final static String INCORRECT_PARAMETERS = "Incorrect parameters";
    private final static String INCORRECT_GENERATOR = "No this generator";
    private final static String INCORRECT_SOLVER = "No this solver";
    private final static String RECURSIVE_BACKTRACKING = "Recursive Backtracking";
    private final static String RECURSIVE_DIVISION = "Recursive Division";
    private final static String DFS = "DFS";
    private final static String BFS = "BFS";

    public Maze(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException(INCORRECT_PARAMETERS);
        }

        this.height = height;
        this.width = width;
        maze = new int[height][width];
    }

    public Maze(int[][] userMaze) {
        this.maze = userMaze;
        this.height = userMaze.length;
        this.width = userMaze[0].length;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    public void generateMaze(String typeOfGeneration) {
        if (typeOfGeneration.equals(RECURSIVE_BACKTRACKING)) {
            RecursiveBacktrackingGenerator recursiveBacktrackingGenerator = new RecursiveBacktrackingGenerator();
            recursiveBacktrackingGenerator.generateMaze(this);
        } else if (typeOfGeneration.equals(RECURSIVE_DIVISION)) {
            RecursiveDivisionGenerator recursiveDivisionGenerator = new RecursiveDivisionGenerator();
            recursiveDivisionGenerator.generateMaze(this);
        } else {
            throw new IllegalArgumentException(INCORRECT_GENERATOR);
        }
    }

    public List<Coordinate> getPath(Coordinate start, Coordinate end, String solver) {
        List<Coordinate> way;
        if (solver.equals(BFS)) {
            BFSSolver bfsSolver = new BFSSolver();
            way = bfsSolver.solve(start, end, this);
        } else if (solver.equals(DFS)) {
            DFSSolver dfsSolver = new DFSSolver();
            way = dfsSolver.solve(start, end, this);
        } else {
            throw new IllegalArgumentException(INCORRECT_SOLVER);
        }
        return way;
    }
}
