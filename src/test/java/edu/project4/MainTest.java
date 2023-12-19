package edu.project4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static edu.project4.Main.main;


public class MainTest {
    @Test
    void testMainDoesNotThrow() {
        assertDoesNotThrow(() -> main(new String[]{}));
    }
}
