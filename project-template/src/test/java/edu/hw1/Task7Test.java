package edu.hw1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @Nested
    class rotateLeftTest {
        @Test
        void validInputLeftTest() {
            assertAll(
                () -> assertThat(Task7.rotateLeft(16, 1)).isEqualTo(1),
                () -> assertThat(Task7.rotateLeft(16, 5)).isEqualTo(16),
                () -> assertThat(Task7.rotateLeft(16, 100)).isEqualTo(16),
                () -> assertThat(Task7.rotateLeft(17, 2)).isEqualTo(6),
                () -> assertThat(Task7.rotateLeft(17, 3)).isEqualTo(12),
                () -> assertThat(Task7.rotateLeft(500, 0)).isEqualTo(500)
            );
        }

        @Test
        void throwInvalidInputLeftTest() {
            assertAll(
                () -> {
                    var exception = assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(0, 3));
                    assertThat(exception.getMessage()).isEqualTo("Cant rotate left, invalid input");
                },
                () -> {
                    var exception = assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(-10, 10));
                    assertThat(exception.getMessage()).isEqualTo("Cant rotate left, invalid input");
                },
                () -> {
                    var exception = assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(8, -3));
                    assertThat(exception.getMessage()).isEqualTo("Cant rotate left, invalid input");
                }
            );
        }
    }

    @Nested
    class rotateRightTest {
        @Test
        void validInputRightTest() {
            assertAll(
                () -> assertThat(Task7.rotateRight(8, 1)).isEqualTo(4),
                () -> assertThat(Task7.rotateRight(8, 4)).isEqualTo(8),
                () -> assertThat(Task7.rotateRight(16, 0)).isEqualTo(16),
                () -> assertThat(Task7.rotateRight(28, 3)).isEqualTo(19)
            );
        }

        @Test
        void throwInvalidInputRightTest() {
            assertAll(
                () -> {
                    var exception = assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(0, 5));
                    assertThat(exception.getMessage()).isEqualTo("Cant rotate right, invalid input");
                },
                () -> {
                    var exception = assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(-4, 4));
                    assertThat(exception.getMessage()).isEqualTo("Cant rotate right, invalid input");
                },
                () -> {
                    var exception = assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(3, -10));
                    assertThat(exception.getMessage()).isEqualTo("Cant rotate right, invalid input");
                }
            );
        }
    }
}
