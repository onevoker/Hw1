package edu.hw1;

public class Task1 {

    private static final int SIXTY = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String str) {
        String[] parts = str.split(":");
        if (parts.length != 2) {
            return -1;
        }
        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            if (seconds >= SIXTY || seconds < 0 || minutes < 0) {
                return -1;
            }
            return minutes * SIXTY + seconds;
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}
