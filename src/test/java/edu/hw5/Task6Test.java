package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task6Test {
    private static final Task6 task6 = new Task6();

    @Test
    void testSubstring() {
        assertAll(
            () -> assertThat(task6.isSubstring("achfdbaabgabcaabg", "abc")).isTrue(),
            () -> assertThat(task6.isSubstring("my name is Alexandr", "is Alexandr")).isTrue(),
            () -> assertThat(task6.isSubstring("abcdefg", "cde")).isTrue()
        );
    }

    @Test
    void testNotSubstring() {
        assertAll(
            () -> assertThat(task6.isSubstring("achfdbaabgabcaabg", "Alexandr")).isFalse(),
            () -> assertThat(task6.isSubstring("zxcqwewasdabc", "zxqwe")).isFalse(),
            () -> assertThat(task6.isSubstring("bart simpson", "jimpson")).isFalse()
        );
    }
}
