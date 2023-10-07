package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task2Test {
    @Test
    void testPositiveNumber() {
        assertAll(
            () -> assertThat(Task2.countDigits(4666)).isEqualTo(4),
            () -> assertThat(Task2.countDigits(544)).isEqualTo(3),
            () -> assertThat(Task2.countDigits(0)).isEqualTo(1),
            () -> assertThat(Task2.countDigits(1000000)).isEqualTo(7)
        );
    }

    @Test
    void testNegativeNumber() {
        assertAll(
            () -> assertThat(Task2.countDigits(-4666)).isEqualTo(4),
            () -> assertThat(Task2.countDigits(-12)).isEqualTo(2),
            () -> assertThat(Task2.countDigits(-1)).isEqualTo(1),
            () -> assertThat(Task2.countDigits(-55555)).isEqualTo(5)
        );
    }
}
