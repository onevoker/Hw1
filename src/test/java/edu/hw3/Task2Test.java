package edu.hw3;

import edu.hw3.Task2.Task2;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task2Test {
    private final static Task2 task2 = new Task2();

    @Test
    void clusterizeCorrectInputTest() {
        assertAll(
            () -> assertThat(task2.clusterize("()()()")).isEqualTo(new ArrayList<String>() {{
                add("()");
                add("()");
                add("()");
            }}),
            () -> assertThat(task2.clusterize("((()))")).isEqualTo(new ArrayList<String>() {{
                add("((()))");
            }}),
            () -> assertThat(task2.clusterize("((()))(())()()(()())")).isEqualTo(new ArrayList<String>() {{
                add("((()))");
                add("(())");
                add("()");
                add("()");
                add("(()())");
            }}),
            () -> assertThat(task2.clusterize("((())())(()(()()))")).isEqualTo(new ArrayList<String>() {{
                add("((())())");
                add("(()(()()))");
            }})
        );
    }

    @Test
    void clusterizeIncorrectInputTest() {
        assertAll(
            () -> assertThat(task2.clusterize(null)).isEqualTo(null),
            () -> assertThat(task2.clusterize("")).isEqualTo(new ArrayList<String>()),
            () -> assertThat(task2.clusterize("(((((")).isEqualTo(new ArrayList<String>()),
            () -> assertThat(task2.clusterize("(")).isEqualTo(new ArrayList<String>()),
            () -> assertThat(task2.clusterize(")")).isEqualTo(new ArrayList<String>())
        );
    }
}
