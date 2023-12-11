package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int countThreads;
    private final BlockingQueue<Runnable> tasks;
    private final Thread[] threads;

    private FixedThreadPool(int countThreads) {
        this.countThreads = countThreads;
        this.tasks = new LinkedBlockingQueue<>();
        this.threads = new Thread[countThreads];
    }

    @Override
    public void start() {
        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(() -> {
                synchronized (tasks) {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            Runnable task = tasks.take();
                            task.run();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });

            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }
}

