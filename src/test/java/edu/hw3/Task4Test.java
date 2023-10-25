package edu.hw3;

import edu.hw3.Task4.Task4;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task4Test {
    public final static Task4 task4 = new Task4();

    @Test
    void convertToRomanValidInputTest() {
        assertAll(
            () -> assertThat(task4.convertToRoman(2)).isEqualTo("II"),
            () -> assertThat(task4.convertToRoman(12)).isEqualTo("XII"),
            () -> assertThat(task4.convertToRoman(16)).isEqualTo("XVI"),
            () -> assertThat(task4.convertToRoman(999)).isEqualTo("CMXCIX"),
            () -> assertThat(task4.convertToRoman(2023)).isEqualTo("MMXXIII")
        );
    }

    @Test
    void convertToRomanInvalidInputTest() {
        assertAll(
            () -> assertThat(task4.convertToRoman(0)).isEqualTo(""),
            () -> assertThat(task4.convertToRoman(4000)).isEqualTo(""),
            () -> assertThat(task4.convertToRoman(-5)).isEqualTo(""),
            () -> assertThat(task4.convertToRoman(9999)).isEqualTo("")
        );
    }
}
