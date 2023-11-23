package edu.hw7.Task1Test;

import org.junit.jupiter.api.Test;
import static edu.hw7.Task1.Task1.multiIncrement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    void testInvalidInput() {
        assertAll(
            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> multiIncrement(100, -1));

                assertThat(exception.getMessage()).isEqualTo("Increase must be >= 0");
            },

            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> multiIncrement(100, -999));

                assertThat(exception.getMessage()).isEqualTo("Increase must be >= 0");
            },

            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> multiIncrement(100, Integer.MIN_VALUE));

                assertThat(exception.getMessage()).isEqualTo("Increase must be >= 0");
            }
        );
    }

    @Test
    void testPositiveStartNumber() {
        assertAll(
            () -> assertThat(multiIncrement(0, 10)).isEqualTo(10),
            () -> assertThat(multiIncrement(3, 15)).isEqualTo(18),
            () -> assertThat(multiIncrement(100, 11)).isEqualTo(111)
        );
    }

    @Test
    void testNegativeStartNumber() {
        assertAll(
            () -> assertThat(multiIncrement(-10, 10)).isEqualTo(0),
            () -> assertThat(multiIncrement(-111, 11)).isEqualTo(-100),
            () -> assertThat(multiIncrement(-3, 21)).isEqualTo(18)
        );
    }
}
