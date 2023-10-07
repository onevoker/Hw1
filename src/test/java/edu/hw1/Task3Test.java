package edu.hw1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Nested
    class isNestableTest {
        @Test
        void testIsNestableTrue() {
            assertAll(
                () -> assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6})).isTrue(),
                () -> assertThat(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0})).isTrue(),
                () -> assertThat(Task3.isNestable(new int[] {-3, -1}, new int[] {-4, 0})).isTrue(),
                () -> assertThat(Task3.isNestable(new int[] {9}, new int[] {8, 10})).isTrue()
            );
        }

        @Test
        void testIsNestableFalse() {
            assertAll(
                () -> assertThat(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {-11, -7}, new int[] {-6, -8, -2})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {4, 4}, new int[] {4, 4})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {8}, new int[] {9})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {8}, new int[] {8})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {9}, new int[] {8})).isFalse(),
                () -> assertThat(Task3.isNestable(new int[] {8, 10}, new int[] {9})).isFalse()
            );
        }

        @Test
        void throwInvalidInputTest() {
            assertAll(
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.isNestable(new int[] {}, new int[] {11}));
                    assertThat(exception.getMessage()).isEqualTo("Some array length equals zero");
                },
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.isNestable(new int[] {3, 2}, new int[] {}));
                    assertThat(exception.getMessage()).isEqualTo("Some array length equals zero");
                },
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.isNestable(new int[] {}, new int[] {}));
                    assertThat(exception.getMessage()).isEqualTo("Some array length equals zero");
                }
            );
        }

        @Test
        void throwNullArrayTest() {
            assertAll(
                () -> {
                    var exception =
                        assertThrows(NullPointerException.class, () -> Task3.isNestable(null, new int[] {11}));
                    assertThat(exception.getMessage()).isEqualTo("Some array is null");
                },
                () -> {
                    var exception =
                        assertThrows(NullPointerException.class, () -> Task3.isNestable(new int[] {22, 11}, null));
                    assertThat(exception.getMessage()).isEqualTo("Some array is null");
                },
                () -> {
                    var exception =
                        assertThrows(NullPointerException.class, () -> Task3.isNestable(null, null));
                    assertThat(exception.getMessage()).isEqualTo("Some array is null");
                }
            );
        }
    }

    @Nested
    class maxTest {
        @Test
        void testMax() {
            assertAll(
                () -> assertThat(Task3.max(new int[] {1, 3, 9, 5})).isEqualTo(9),
                () -> assertThat(Task3.max(new int[] {0})).isEqualTo(0),
                () -> assertThat(Task3.max(new int[] {-5, -6, -10, -2})).isEqualTo(-2)
            );
        }

        @Test
        void throwInvalidInputTest() {
            assertAll(
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.max(new int[] {}));
                    assertThat(exception.getMessage()).isEqualTo("Cant find max, array length equals zero");
                },
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.max(null));
                    assertThat(exception.getMessage()).isEqualTo("Array is null, cant find max");
                }
            );
        }
    }

    @Nested
    class minTest {
        @Test
        void testMin() {
            assertAll(
                () -> assertThat(Task3.min(new int[] {1, 3, 9, 5})).isEqualTo(1),
                () -> assertThat(Task3.min(new int[] {0})).isEqualTo(0),
                () -> assertThat(Task3.min(new int[] {-5, -6, -10, -2})).isEqualTo(-10)
            );
        }

        @Test
        void throwInvalidInputTest() {
            assertAll(
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.min(new int[] {}));
                    assertThat(exception.getMessage()).isEqualTo("Cant find min, array length equals zero");
                },
                () -> {
                    var exception =
                        assertThrows(Exception.class, () -> Task3.min(null));
                    assertThat(exception.getMessage()).isEqualTo("Array is null, cant find min");
                }
            );
        }
    }
}
