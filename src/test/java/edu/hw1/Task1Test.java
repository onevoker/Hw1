package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class Task1Test {

    @Test
    void testValidInput() {
        assertAll(
            () -> assertThat(Task1.minutesToSeconds("01:00")).isEqualTo(60),
            () -> assertThat(Task1.minutesToSeconds("13:56")).isEqualTo(836),
            () -> assertThat(Task1.minutesToSeconds("01:00")).isEqualTo(60),
            () -> assertThat(Task1.minutesToSeconds("999:59")).isEqualTo(59999),
            () -> assertThat(Task1.minutesToSeconds("102:00")).isEqualTo(6120),
            () -> assertThat(Task1.minutesToSeconds("10999:12")).isEqualTo(659952)
        );
    }

    @Test
    void testInvalidInput() {
        int max = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        assertAll(
            () -> assertThat(Task1.minutesToSeconds("max:59")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("min:59")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("10:60")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("13:599")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("10:-30")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("-10:30")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds(":")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("abdawq")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("ab:ju")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("11::::::::::::33")).isEqualTo(-1),
            () -> assertThat(Task1.minutesToSeconds("1026869212131231:00")).isEqualTo(-1)
        );
    }
}
