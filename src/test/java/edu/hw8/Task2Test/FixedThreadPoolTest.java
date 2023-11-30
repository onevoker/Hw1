package edu.hw8.Task2Test;

import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedThreadPoolTest {
    @Test
    void testExecuteTasks() throws InterruptedException {
        try (FixedThreadPool threadPool = FixedThreadPool.create(2)) {
            StringBuilder result = new StringBuilder();
            threadPool.start();

            threadPool.execute(() -> result.append("Task1 "));
            threadPool.execute(() -> result.append("Task2"));

            Thread.sleep(777);

            assertThat(result.toString()).isEqualTo("Task1 Task2");
        }
    }

    @Nested
    class FibonacciTest {
        private static long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }

            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }

        @Test
        void testCalculateFibonacci() throws InterruptedException {
            long[] results = new long[7];
            long[] expected = new long[] {0, 1, 1, 2, 3, 5, 8};

            try (FixedThreadPool threadPool = FixedThreadPool.create(6)) {
                for (int i = 0; i < 7; i++) {
                    final int n = i;
                    threadPool.execute(() -> {
                        results[n] = calculateFibonacci(n);
                    });
                }

                threadPool.start();
                sleep(777);
            }

            assertThat(results).isEqualTo(expected);
        }
    }
}
