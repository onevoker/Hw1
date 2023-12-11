package edu.hw8.Task3Test;

import edu.hw8.Task3.PasswordCracker;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordCrackerTest {
    private static final Map<String, String> DATA_BASE = Map.of(
        "a.v.petrov", "e10adc3949ba59abbe56e057f20f883e",
        "v.v.belov", "d8578edf8458ce06fbc5bb76a58c5ca4"
    );

    private static final Map<String, String> expected = Map.of(
        "a.v.petrov", "123456",
        "v.v.belov", "qwerty"
    );

    @Test
    void testCrackByOneThread() {
        PasswordCracker cracker = new PasswordCracker(DATA_BASE);
        Map<String, String> result = cracker.crackByOneThread();

        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
    }

    @Test
    void testCrackByMultiThreads() {
        PasswordCracker cracker = new PasswordCracker(DATA_BASE);
        Map<String, String> result = cracker.crackByMultiThreads(6);

        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
    }
}
