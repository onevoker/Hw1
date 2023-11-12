package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {
    @Test
    void backwardIteratorTest() {
        List<Integer> list = List.of(1, 2, 3);
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(list);

        for (int i = list.size() - 1; i >= 0; i--) {
            int currentElement = backwardIterator.next();
            int element = list.get(i);

            assertThat(currentElement).isEqualTo(element);
        }
    }

    @Test
    void backwardIteratorThrowsTest() {
        List<Integer> list = List.of(1, 2, 3);
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(list);

        while (backwardIterator.hasNext()) {
            backwardIterator.next();
        }

        var exception = assertThrows(IndexOutOfBoundsException.class, backwardIterator::next);

        assertThat(exception.getMessage()).isEqualTo("There is no next element");
    }
}
