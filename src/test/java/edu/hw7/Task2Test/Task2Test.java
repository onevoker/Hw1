package edu.hw7.Task2Test;

import org.junit.jupiter.api.Test;
import static edu.hw7.Task2.Task2.getFactorial;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    void testInvalidInput() {
        assertAll(
            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> getFactorial(-1L));

                assertThat(exception.getMessage()).isEqualTo("Number !∈ [0, 20]");
            },

            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> getFactorial(21L));

                assertThat(exception.getMessage()).isEqualTo("Number !∈ [0, 20]");
            },

            () -> {
                var exception =
                    assertThrows(IllegalArgumentException.class, () -> getFactorial(Long.MAX_VALUE));

                assertThat(exception.getMessage()).isEqualTo("Number !∈ [0, 20]");
            }
        );
    }

    @Test
    void testGetFactorialOfZero() {
        assertThat(getFactorial(0L)).isEqualTo(1L);
    }

    @Test
    void testValidInput() {
        assertAll(
            () -> assertThat(getFactorial(1L)).isEqualTo(1L),
            () -> assertThat(getFactorial(2L)).isEqualTo(2L),
            () -> assertThat(getFactorial(3L)).isEqualTo(6L),
            () -> assertThat(getFactorial(4L)).isEqualTo(24L),
            () -> assertThat(getFactorial(5L)).isEqualTo(120L),
            () -> assertThat(getFactorial(6L)).isEqualTo(720L)
        );
    }
}
