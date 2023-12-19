package edu.hw11;

import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodCall;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    public static Stream<Arguments> getArguments() {
        return Stream.of(
            Arguments.of(2, 3, 6),
            Arguments.of(4, 10, 40),
            Arguments.of(5, 5, 25),
            Arguments.of(12, 12, 144),
            Arguments.of(4, 8, 32)
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void testMultiply(int a, int b, int expected)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException {

        var clazz = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodCall.invoke(ArithmeticUtils.class.getMethod("multiply", int.class, int.class))
                .withAllArguments())
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        int result = clazz.newInstance().sum(a, b);

        assertThat(result).isEqualTo(expected);
    }
}
