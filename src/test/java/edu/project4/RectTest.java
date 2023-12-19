package edu.project4;

import edu.project4.BaseClasses.Point;
import edu.project4.BaseClasses.Rect;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RectTest {
    @Test
    void testContains() {
        Rect rect = new Rect(1, 1, 10, 10);
        Point point = new Point(10, 10);
        assertThat(rect.contains(point)).isTrue();
    }

    @Test
    void testNotContains() {
        Rect rect = new Rect(-3, -3, 5, 5);
        Point point = new Point(10, 10);
        assertThat(rect.contains(point)).isFalse();
    }

}
