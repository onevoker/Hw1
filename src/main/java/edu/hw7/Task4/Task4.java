package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Task4 {
    private static final int WIDTH = 10000;
    private static final int RADIUS = WIDTH / 2;
    private static final double COEFFICIENT = 4.0D;

    private Task4() {
    }

    public static double getPiByOneThread(long totalPoints) {
        int circleCount = getCircleCount(totalPoints);

        return getPi(circleCount, totalPoints);
    }

    public static double getPiByMultiThreads(long totalPoints, int countThreads) throws InterruptedException {
        AtomicInteger circleCount = new AtomicInteger(0);
        Thread[] threads = new Thread[countThreads];

        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(() -> circleCount.addAndGet(getCircleCount(totalPoints / countThreads)));
            threads[i].start();
        }

        for (int i = 0; i < countThreads; ++i) {
            threads[i].join();
        }

        return getPi(circleCount.get(), totalPoints);
    }

    private static int getCircleCount(long totalPoints) {
        int count = 0;

        for (int j = 0; j < totalPoints; ++j) {
            int x = ThreadLocalRandom.current().nextInt(0, WIDTH + 1);
            int y = ThreadLocalRandom.current().nextInt(0, WIDTH + 1);

            double distance = Math.pow((RADIUS - x), 2) + Math.pow((RADIUS - y), 2);

            if (Math.sqrt(distance) < RADIUS) {
                ++count;
            }
        }

        return count;
    }

    private static double getPi(int circleCount, long totalPoints) {
        return COEFFICIENT * circleCount / totalPoints;
    }
}
