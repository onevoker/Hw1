package edu.hw10.Task2Test;

import edu.hw10.Task2.cache.CacheProxy;
import edu.hw10.Task2.fibonacci.Calculator;
import edu.hw10.Task2.fibonacci.FibCalculator;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CacheProxyTest {
    private static final String DIRECTORY = "./src/test/java/edu/hw10/Task2Test/test.txt";
    private static final File FILE = new File(DIRECTORY);
    private FibCalculator calculator;
    private FibCalculator proxy;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator();
        this.proxy = CacheProxy.create(calculator, DIRECTORY);
    }

    @Test
    void testCalculateFib() {
        long number = proxy.fib(10);

        assertAll(
            () -> assertThat(number).isEqualTo(55),
            () -> assertThat(FILE.exists()).isTrue(),
            () -> assertThat(FILE.length()).isGreaterThan(0)

        );
    }

    @Test
    void testCacheWorks() {
        long number1 = proxy.fib(45);

        long startTime = System.currentTimeMillis();
        long number2 = proxy.fib(45);
        long endTime = System.currentTimeMillis();
        long calculatingTime = endTime - startTime;

        assertAll(
            () -> assertThat(number1).isEqualTo(number2),
            () -> assertThat(calculatingTime).isEqualTo(0L)
        );
    }
    @BeforeEach
    void deleteDirectory() {
        FILE.deleteOnExit();
    }
}
