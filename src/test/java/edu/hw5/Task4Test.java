package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task4Test {
    private static final Task4 task4 = new Task4();

    @Test
    void testValidPasswords() {
        assertAll(
            () -> assertThat(task4.isPasswordCorrect("1@")).isTrue(),
            () -> assertThat(task4.isPasswordCorrect("1!5")).isTrue(),
            () -> assertThat(task4.isPasswordCorrect("@3122")).isTrue(),
            () -> assertThat(task4.isPasswordCorrect("@@321")).isTrue(),
            () -> assertThat(task4.isPasswordCorrect("1234@$!")).isTrue(),
            () -> assertThat(task4.isPasswordCorrect("~!@#$%^&*|")).isTrue(),
            () -> assertThat(task4.isPasswordCorrect("~abc!@#qwe$%^&*|xyz")).isTrue()
        );
    }

    @Test
    void testInvalidPasswords() {
        assertAll(
            () -> assertThat(task4.isPasswordCorrect("1234")).isFalse(),
            () -> assertThat(task4.isPasswordCorrect("1234...")).isFalse(),
            () -> assertThat(task4.isPasswordCorrect("qwerty")).isFalse(),
            () -> assertThat(task4.isPasswordCorrect("z x c")).isFalse(),
            () -> assertThat(task4.isPasswordCorrect("w+a+s+d = wasd")).isFalse(),
            () -> assertThat(task4.isPasswordCorrect("")).isFalse()
        );
    }
}
