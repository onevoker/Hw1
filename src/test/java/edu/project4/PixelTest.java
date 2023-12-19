package edu.project4;

import edu.project4.BaseClasses.Pixel;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PixelTest {
    @Test
    void testGetNormal() {
        Pixel pixel1 = new Pixel(0, 0, 0, 100);
        Pixel pixel2 = new Pixel(0, 0, 0, 1);

        double normal1 = pixel1.getNormal();
        double normal2 = pixel2.getNormal();

        assertAll(
            () -> assertThat(normal1).isEqualTo(2),
            () -> assertThat(normal2).isEqualTo(0)
        );
    }
}
