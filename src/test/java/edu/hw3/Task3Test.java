package edu.hw3;

import edu.hw3.Task3.Task3;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task3Test {
    private final static Task3 task3 = new Task3();

    @Test
    void freqDictStringTest() {
        assertAll(
            () -> assertThat(task3.freqDict(List.of("a", "bb", "a", "bb"))).isEqualTo(Map.of("bb", 2, "a", 2)),
            () -> assertThat(task3.freqDict(List.of("this", "and", "that", "and"))).isEqualTo(Map.of(
                "that",
                1,
                "and",
                2,
                "this",
                1
            )),
            () -> assertThat(task3.freqDict(List.of("код", "код", "код", "bug"))).isEqualTo(Map.of("код", 3, "bug", 1))
        );
    }

    @Test
    void freqDictIntegerTest() {
        assertAll(
            () -> assertThat(task3.freqDict(List.of(1, 2, 3, 4))).isEqualTo(Map.of(1, 1, 2, 1, 3, 1, 4, 1)),
            () -> assertThat(task3.freqDict(List.of(1, 1, 2, 2))).isEqualTo(Map.of(1, 2, 2, 2)),
            () -> assertThat(task3.freqDict(List.of("a", 4, "a", 4))).isEqualTo(Map.of("a", 2, 4, 2))
        );
    }

    @Test
    void freqDictNullInputTest() {
        assertThat(task3.freqDict(null)).isEqualTo(null);
    }
}
