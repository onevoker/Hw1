package edu.project2;

import java.util.List;

@SuppressWarnings({"RegexpSinglelineJava"})
public class ConsoleOutput {

    private final static String RESET = "\u001B[0m";

    private final static String RED = "\u001B[31m";
    private final static String BLACK = "\u001B[30m";
    private final static int PASSAGE = 0;
    private final static int WALL = 1;
    private final static int CHECKED = 3;
    private final static int PATH = 5;

    private ConsoleOutput() {
    }

    public static void printPassageToChose(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getMaze()[i][j] == Cell.PASSAGE.getValue()) {
                    System.out.println(i + " " + j);
                }
            }
        }
    }

    public static void printMaze(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                int cellValue = maze.getMaze()[i][j];

                switch (cellValue) {
                    case WALL -> System.out.print("█");
                    case PASSAGE, CHECKED -> System.out.print(BLACK + "█" + RESET);
                    case PATH -> System.out.print(RED + "█" + RESET);
                    default -> throw new IllegalArgumentException("Something went wrong");
                }
            }

            System.out.println();
        }
    }

    public static void printCoordinatesOfPath(List<Coordinate> way) {
        if (way.isEmpty()) {
            System.out.println("Have no solution of this maze");
        } else {
            for (Coordinate coordinate : way) {
                System.out.println(coordinate.row() + " " + coordinate.col());
            }
        }
    }
}
