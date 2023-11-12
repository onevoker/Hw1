package edu.hw3;

import edu.hw3.Task1.Task1;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task1Test {
    private final static Task1 task1 = new Task1();

    @Test
    void encodeUpperLetterTest() {
        assertAll(
            () -> assertThat(task1.encodeUpperLetter('A')).isEqualTo('Z'),
            () -> assertThat(task1.encodeUpperLetter('B')).isEqualTo('Y'),
            () -> assertThat(task1.encodeUpperLetter('Z')).isEqualTo('A'),
            () -> assertThat(task1.encodeUpperLetter('C')).isEqualTo('X')
        );
    }

    @Test
    void encodeLowerLetterTest() {
        assertAll(
            () -> assertThat(task1.encodeLowerLetter('a')).isEqualTo('z'),
            () -> assertThat(task1.encodeLowerLetter('b')).isEqualTo('y'),
            () -> assertThat(task1.encodeLowerLetter('z')).isEqualTo('a'),
            () -> assertThat(task1.encodeLowerLetter('c')).isEqualTo('x')
        );
    }

    @Test
    void atbashTest() {
        assertAll(
            () -> assertThat(task1.atbash("Hello world!")).isEqualTo("Svool dliow!"),
            () -> assertThat(task1.atbash(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler")).isEqualTo(
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"),
            () -> assertThat(task1.atbash("java 21")).isEqualTo("qzez 21"),
            () -> assertThat(task1.atbash("123 zxc qwe")).isEqualTo("123 acx jdv"),
            () -> assertThat(task1.atbash(null)).isEqualTo(null),
            () -> assertThat(task1.atbash("")).isEqualTo("")
        );
    }
}
