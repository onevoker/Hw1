package edu.hw8.Task3Test;

import edu.hw8.Task3.Hasher;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class HasherTest {
    @ParameterizedTest
    @MethodSource("getPasswordAndHash")
    void testHashPassword(String password, String hash) {
        String actualHash = Hasher.hashPassword(password);

        assertThat(actualHash).isEqualTo(hash);
    }

    public static Stream<Arguments> getPasswordAndHash() {
        return Stream.of(
            Arguments.of("123456", "e10adc3949ba59abbe56e057f20f883e"),
            Arguments.of("qwerty", "d8578edf8458ce06fbc5bb76a58c5ca4"),
            Arguments.of("zxc", "5fa72358f0b4fb4f2c5d7de8c9a41846")
        );
    }
}
