package edu.project2.Generators;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Direction;
import edu.project2.Maze;
import edu.project2.ValidationChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackingGenerator extends ValidationChecker implements Generator {
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
        Stack<Coordinate> stack = new Stack<>();
        maze.getMaze()[0][0] = Cell.PASSAGE.getValue();
        stack.push(new Coordinate(0, 0));

        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();
            int row = current.row();
            int col = current.col();
            Direction[] directions = Direction.values();
            List<Direction> availableDirections = new ArrayList<>();

            for (Direction direction : directions) {
                int newRow = row + direction.row;
                int newCol = col + direction.col;

                if (isInRangeOfMaze(newRow, newCol, maze) && maze.getMaze()[newRow][newCol] == Cell.WALL.getValue()) {
                    availableDirections.add(direction);
                }
            }

            if (!availableDirections.isEmpty()) {
                Direction randomDirection = availableDirections.get(RANDOM.nextInt(availableDirections.size()));
                int wallRow = row + randomDirection.row / TWO;
                int wallCol = col + randomDirection.col / TWO;
                maze.getMaze()[wallRow][wallCol] = Cell.PASSAGE.getValue();

                int newRow = row + randomDirection.row;
                int newCol = col + randomDirection.col;
                maze.getMaze()[newRow][newCol] = Cell.PASSAGE.getValue();

                stack.push(new Coordinate(newRow, newCol));
            } else {
                stack.pop();
            }
        }
    }
}
