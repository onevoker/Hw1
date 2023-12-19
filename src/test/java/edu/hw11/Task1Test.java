package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void testHelloByteBuddy() throws InstantiationException, IllegalAccessException {
        String hello = "Hello, ByteBuddy!";
        Class<?> clazz = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value(hello))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        String result = clazz.newInstance().toString();

        assertThat(result).isEqualTo(hello);
    }
}
