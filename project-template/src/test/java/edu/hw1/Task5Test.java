package edu.hw1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task5Test {

    @Nested
    class isPalindromeTest {
        @Test
        void isPalindrome() {
            assertAll(
                () -> assertThat(Task5.isPalindrome(121)).isTrue(),
                () -> assertThat(Task5.isPalindrome(0)).isTrue(),
                () -> assertThat(Task5.isPalindrome(363)).isTrue(),
                () -> assertThat(Task5.isPalindrome(5665)).isTrue(),
                () -> assertThat(Task5.isPalindrome(44)).isTrue(),
                () -> assertThat(Task5.isPalindrome(11)).isTrue()
            );
        }

        @Test
        void isNotPalindrome() {
            assertAll(
                () -> assertThat(Task5.isPalindrome(11211230)).isFalse(),
                () -> assertThat(Task5.isPalindrome(4022)).isFalse(),
                () -> assertThat(Task5.isPalindrome(9929)).isFalse(),
                () -> assertThat(Task5.isPalindrome(23336014)).isFalse(),
                () -> assertThat(Task5.isPalindrome(-121)).isFalse()
            );
        }
    }

    @Nested
    class isPalindromeDescendantTest {
        @Test
        void isPalindromeDescendant() {
            assertAll(
                () -> assertThat(Task5.isPalindromeDescendant(11211230)).isTrue(),
                () -> assertThat(Task5.isPalindromeDescendant(13001120)).isTrue(),
                () -> assertThat(Task5.isPalindromeDescendant(23336014)).isTrue(),
                () -> assertThat(Task5.isPalindromeDescendant(11)).isTrue(),
                () -> assertThat(Task5.isPalindromeDescendant(1450)).isTrue(),
                () -> assertThat(Task5.isPalindromeDescendant(9)).isTrue()
            );
        }

        @Test
        void isNotPalindromeDescendant() {
            assertAll(
                () -> assertThat(Task5.isPalindromeDescendant(-11)).isFalse(),
                () -> assertThat(Task5.isPalindromeDescendant(133)).isFalse(),
                () -> assertThat(Task5.isPalindromeDescendant(23336015)).isFalse(),
                () -> assertThat(Task5.isPalindromeDescendant(130011201)).isFalse(),
                () -> assertThat(Task5.isPalindromeDescendant(333336)).isFalse(),
                () -> assertThat(Task5.isPalindromeDescendant(45)).isFalse()
            );
        }
    }
}
