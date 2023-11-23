package edu.hw7.Task2;

import java.util.stream.LongStream;

public class Task2 {
    private static final long MAX_VALUE = 20;

    private Task2() {
    }

    public static long getFactorial(long number) {
        if (number < 0 || number > MAX_VALUE) {
            throw new IllegalArgumentException("Number !∈ [0, 20]");
        }

        return LongStream
            .range(1, number + 1)
            .parallel()
            .reduce(Math::multiplyExact)
            .orElse(1);
    }
}
