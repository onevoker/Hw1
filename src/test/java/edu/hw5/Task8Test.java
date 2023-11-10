package edu.hw5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task8Test {
    private static final Task8 task8 = new Task8();

    @Nested
    class SubTask1Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task8.subTask1("0")).isTrue(),
                () -> assertThat(task8.subTask1("1")).isTrue(),
                () -> assertThat(task8.subTask1("101")).isTrue(),
                () -> assertThat(task8.subTask1("111")).isTrue(),
                () -> assertThat(task8.subTask1("1111101")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task8.subTask1("10")).isFalse(),
                () -> assertThat(task8.subTask1("1001")).isFalse(),
                () -> assertThat(task8.subTask1("3")).isFalse(),
                () -> assertThat(task8.subTask1("333")).isFalse(),
                () -> assertThat(task8.subTask1("0110")).isFalse()
            );
        }
    }

    @Nested
    class SubTask2Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task8.subTask2("0")).isTrue(),
                () -> assertThat(task8.subTask2("011")).isTrue(),
                () -> assertThat(task8.subTask2("011011100")).isTrue(),
                () -> assertThat(task8.subTask2("10")).isTrue(),
                () -> assertThat(task8.subTask2("100011")).isTrue(),
                () -> assertThat(task8.subTask2("1000")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task8.subTask2("099")).isFalse(),
                () -> assertThat(task8.subTask2("010101")).isFalse(),
                () -> assertThat(task8.subTask2("01")).isFalse(),
                () -> assertThat(task8.subTask2("1999")).isFalse(),
                () -> assertThat(task8.subTask2("1")).isFalse(),
                () -> assertThat(task8.subTask2("10101")).isFalse()
            );
        }
    }

    @Nested
    class SubTask4Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task8.subTask4("")).isTrue(),
                () -> assertThat(task8.subTask4("1")).isTrue(),
                () -> assertThat(task8.subTask4("1111")).isTrue(),
                () -> assertThat(task8.subTask4("0110")).isTrue(),
                () -> assertThat(task8.subTask4("1110")).isTrue(),
                () -> assertThat(task8.subTask4("1100")).isTrue(),
                () -> assertThat(task8.subTask4("01110011")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task8.subTask4("11")).isFalse(),
                () -> assertThat(task8.subTask4("111")).isFalse(),
                () -> assertThat(task8.subTask4("9")).isFalse(),
                () -> assertThat(task8.subTask4("333")).isFalse(),
                () -> assertThat(task8.subTask4("012345")).isFalse()
            );
        }
    }

    @Nested
    class SubTask5Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task8.subTask5("1")).isTrue(),
                () -> assertThat(task8.subTask5("101")).isTrue(),
                () -> assertThat(task8.subTask5("111111")).isTrue(),
                () -> assertThat(task8.subTask5("101010111")).isTrue(),
                () -> assertThat(task8.subTask5("101011111111111111111111")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task8.subTask5("0")).isFalse(),
                () -> assertThat(task8.subTask5("001010101010101")).isFalse(),
                () -> assertThat(task8.subTask5("191919191")).isFalse(),
                () -> assertThat(task8.subTask5("1001111111111")).isFalse(),
                () -> assertThat(task8.subTask5("0101010101")).isFalse(),
                () -> assertThat(task8.subTask5("111101")).isFalse()
            );
        }
    }

    @Nested
    class SubTask6Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task8.subTask6("001")).isTrue(),
                () -> assertThat(task8.subTask6("0000100")).isTrue(),
                () -> assertThat(task8.subTask6("10000")).isTrue(),
                () -> assertThat(task8.subTask6("010")).isTrue(),
                () -> assertThat(task8.subTask6("000001")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task8.subTask6("")).isFalse(),
                () -> assertThat(task8.subTask6("")).isFalse(),
                () -> assertThat(task8.subTask6("")).isFalse(),
                () -> assertThat(task8.subTask6("")).isFalse(),
                () -> assertThat(task8.subTask6("")).isFalse(),
                () -> assertThat(task8.subTask6("")).isFalse(),
                () -> assertThat(task8.subTask6("")).isFalse()
            );
        }
    }

    @Nested
    class SubTask7Test {
        @Test
        void testValidStrings() {
            assertAll(
                () -> assertThat(task8.subTask7("1")).isTrue(),
                () -> assertThat(task8.subTask7("10101010")).isTrue(),
                () -> assertThat(task8.subTask7("01010101")).isTrue(),
                () -> assertThat(task8.subTask7("101")).isTrue(),
                () -> assertThat(task8.subTask7("001")).isTrue()
            );
        }

        @Test
        void testInvalidStrings() {
            assertAll(
                () -> assertThat(task8.subTask7("777")).isFalse(),
                () -> assertThat(task8.subTask7("012")).isFalse(),
                () -> assertThat(task8.subTask7("11")).isFalse(),
                () -> assertThat(task8.subTask7("0010110")).isFalse(),
                () -> assertThat(task8.subTask7("111111")).isFalse(),
                () -> assertThat(task8.subTask7("011101010101100")).isFalse(),
                () -> assertThat(task8.subTask7("01011101")).isFalse()
            );
        }
    }
}
