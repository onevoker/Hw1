package edu.hw7.Task4Test;

import org.junit.jupiter.api.Test;
import static edu.hw7.Task4.Task4.getPiByMultiThreads;
import static edu.hw7.Task4.Task4.getPiByOneThread;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task4Test {
    @Test
    void testGettersOfPi() throws InterruptedException {
        double acceptableInaccuracy = 0.1D;
        double actualInaccuracyByOneThread = Math.PI - getPiByOneThread(1000000L);
        double actualInaccuracyBySixThreads = Math.PI - getPiByMultiThreads(1000000L, 6);

        assertAll(
            () -> assertThat(actualInaccuracyByOneThread).isLessThanOrEqualTo(acceptableInaccuracy),
            () -> assertThat(actualInaccuracyBySixThreads).isLessThanOrEqualTo(acceptableInaccuracy)
        );
    }
}
