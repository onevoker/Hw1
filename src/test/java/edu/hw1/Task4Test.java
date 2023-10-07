package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task4Test {
    @Test
    void test() {
        assertAll(
            () -> assertThat(Task4.fixString("123456")).isEqualTo("214365"),
            () -> assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string."),
            () -> assertThat(Task4.fixString("badce")).isEqualTo("abcde"),
            () -> assertThat(Task4.fixString("")).isEqualTo(""),
            () -> assertThat(Task4.fixString("ab")).isEqualTo("ba"),
            () -> assertThat(Task4.fixString("a")).isEqualTo("a"),
            () -> assertThat(Task4.fixString("+--+")).isEqualTo("-++-")
        );
    }
}

