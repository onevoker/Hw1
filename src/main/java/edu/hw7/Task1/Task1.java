package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {
    }

    public static int multiIncrement(int startNumber, int increase) {
        if (increase < 0) {
            throw new IllegalArgumentException("Increase must be >= 0");
        }

        AtomicInteger atomicNum = new AtomicInteger(startNumber);
        Thread[] threads = new Thread[increase];

        for (int i = 0; i < increase; i++) {
            threads[i] = new IncrementorThread(atomicNum);
            threads[i].start();
        }

        for (int i = 0; i < increase; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new IndexOutOfBoundsException("Can't join threads");
            }
        }

        return atomicNum.get();
    }
}
