package edu.hw1;

public class Task2 {

    private static final int TEN = 10;

    private Task2() {
    }

    public static int countDigits(int num) {
        if (num == 0) {
            return 1;
        }
        int n = num;
        int counter = 0;
        if (n < 0) {
            n *= -1;
        }
        while (n > 0) {
            n /= TEN;
            counter++;
        }
        return counter;
    }
}
