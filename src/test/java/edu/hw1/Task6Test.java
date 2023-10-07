package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task6Test {

    @Test
    void testValidInputTest() {
        assertAll(
            () -> assertThat(Task6.countK(3524)).isEqualTo(3),
            () -> assertThat(Task6.countK(6621)).isEqualTo(5),
            () -> assertThat(Task6.countK(6554)).isEqualTo(4),
            () -> assertThat(Task6.countK(1234)).isEqualTo(3)
        );
    }

    @Test
    void testInvalidInputTest() {
        assertAll(
            () -> assertThat(Task6.countK(999)).isEqualTo(-1),
            () -> assertThat(Task6.countK(5555)).isEqualTo(-1),
            () -> assertThat(Task6.countK(10000)).isEqualTo(-1),
            () -> assertThat(Task6.countK(-10000)).isEqualTo(-1)
        );
    }
}
