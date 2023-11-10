package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task5Test {
    private static final Task5 task5 = new Task5();

    @Test
    void testValidCarNumbers() {
        assertAll(
            () -> assertThat(task5.iSCarNumberCorrect("A123BE777")).isTrue(),
            () -> assertThat(task5.iSCarNumberCorrect("O777OO177")).isTrue(),
            () -> assertThat(task5.iSCarNumberCorrect("A123BC456")).isTrue(),
            () -> assertThat(task5.iSCarNumberCorrect("B999AA111")).isTrue(),
            () -> assertThat(task5.iSCarNumberCorrect("C777CC333")).isTrue()

        );
    }

    @Test
    void testInvalidCarNumbers() {
        assertAll(
            () -> assertThat(task5.iSCarNumberCorrect("123ABE777")).isFalse(),
            () -> assertThat(task5.iSCarNumberCorrect("A123ВГ77")).isFalse(),
            () -> assertThat(task5.iSCarNumberCorrect("A123AB79")).isFalse(),
            () -> assertThat(task5.iSCarNumberCorrect("A123BE7777")).isFalse(),
            () -> assertThat(task5.iSCarNumberCorrect("AB123BE777")).isFalse()
        );
    }
}
