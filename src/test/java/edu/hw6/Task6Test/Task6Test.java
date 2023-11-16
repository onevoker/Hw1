package edu.hw6.Task6Test;

import edu.hw6.Task6.Task6;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Task6Test {
    @Test
    void testDoesNotThrowScanAllPorts() {
        assertDoesNotThrow(Task6::scanAllPorts);
    }

    @Test
    void testDoesNotThrowScanTCP() {
        assertDoesNotThrow(() -> Task6.scanTCP(17500));
    }

    @Test
    void testDoesNotThrowScanUDP() {
        assertDoesNotThrow(() -> Task6.scanUDP(1900));
    }
}
