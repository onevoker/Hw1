package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void rectangleAreaTest() {
        Rectangle rect = new Rectangle(0, 0);
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    void squareAreaTest() {
        Square square = new Square(0);
        square = (Square) square.setHeight(5);

        assertThat(square.area()).isEqualTo(25.0);
    }
}
