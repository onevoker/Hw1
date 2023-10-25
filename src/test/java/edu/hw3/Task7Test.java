package edu.hw3;

import edu.hw3.Task7.TreeMapComparator;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void nullContainsTest() {
        TreeMap<String, String> tree = new TreeMap<>(new TreeMapComparator());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
