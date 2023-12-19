package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static final Class<?> FIBONACCI = Task3.createFibonacciClass();

    public static Stream<Arguments> getArguments() {
        return Stream.of(
            Arguments.of(0, 0L),
            Arguments.of(2, 1L),
            Arguments.of(4, 3L),
            Arguments.of(6, 8L),
            Arguments.of(8, 21L),
            Arguments.of(10, 55L)
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void testFib(int num, long expected)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method fib = FIBONACCI.getMethod("fib", int.class);
        var result = fib.invoke(null, num);

        assertThat(result).isEqualTo(expected);
    }
}
