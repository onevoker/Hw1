package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementorThread extends Thread {
    private final AtomicInteger counter;

    public IncrementorThread(AtomicInteger atomicCounter) {
        this.counter = atomicCounter;
    }

    @Override
    public void run() {
        counter.incrementAndGet();
    }
}
