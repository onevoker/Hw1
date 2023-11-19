package edu.hw5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task7Test {
    private static final Task7 task7 = new Task7();
    @Nested
    class SubTask1Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task7.subTask1("110")).isTrue(),
                () -> assertThat(task7.subTask1("000")).isTrue(),
                () -> assertThat(task7.subTask1("11011")).isTrue(),
                () -> assertThat(task7.subTask1("000000")).isTrue(),
                () -> assertThat(task7.subTask1("010101")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task7.subTask1("1")).isFalse(),
                () -> assertThat(task7.subTask1("0")).isFalse(),
                () -> assertThat(task7.subTask1("99085")).isFalse(),
                () -> assertThat(task7.subTask1("001")).isFalse(),
                () -> assertThat(task7.subTask1("00")).isFalse(),
                () -> assertThat(task7.subTask1("990101")).isFalse(),
                () -> assertThat(task7.subTask1("100777")).isFalse(),
                () -> assertThat(task7.subTask1("1010000000000")).isFalse()

            );
        }
    }

    @Nested
    class SubTask2Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task7.subTask2("100000001")).isTrue(),
                () -> assertThat(task7.subTask2("000000000")).isTrue(),
                () -> assertThat(task7.subTask2("1011")).isTrue(),
                () -> assertThat(task7.subTask2("1000101")).isTrue(),
                () -> assertThat(task7.subTask2("1")).isTrue(),
                () -> assertThat(task7.subTask2("00")).isTrue()
                );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task7.subTask2("100")).isFalse(),
                () -> assertThat(task7.subTask2("000000001")).isFalse(),
                () -> assertThat(task7.subTask2("011")).isFalse(),
                () -> assertThat(task7.subTask2("10000010")).isFalse(),
                () -> assertThat(task7.subTask2("989")).isFalse(),
                () -> assertThat(task7.subTask2("080")).isFalse()
            );
        }
    }

    @Nested
    class SubTask3Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task7.subTask3("1")).isTrue(),
                () -> assertThat(task7.subTask3("00")).isTrue(),
                () -> assertThat(task7.subTask3("111")).isTrue(),
                () -> assertThat(task7.subTask3("101")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task7.subTask3("")).isFalse(),
                () -> assertThat(task7.subTask3("1111")).isFalse(),
                () -> assertThat(task7.subTask3("191")).isFalse(),
                () -> assertThat(task7.subTask3("911")).isFalse(),
                () -> assertThat(task7.subTask3("999")).isFalse()
            );
        }
    }
}
