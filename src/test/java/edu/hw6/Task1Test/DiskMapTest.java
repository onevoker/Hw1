package edu.hw6.Task1Test;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DiskMapTest {
    private static final DiskMap diskMap = new DiskMap("./src/test/java/edu/hw6/Task1Test/test.txt");

    @Test
    void testIsCorrectWorkingOfFunctions1() {
        diskMap.put("Lol", "hah");
        assertAll(
            () -> assertThat(diskMap.containsKey("Lol")).isTrue(),
            () -> assertThat(diskMap.containsValue("hah")).isTrue(),
            () -> assertThat(diskMap.get("Lol")).isEqualTo("hah")
        );
    }

    @Test
    void testIsCorrectWorkingOfFunctions2() {
        diskMap.put("Lol", "hah");
        assertAll(
            () -> assertThat(diskMap.isEmpty()).isFalse(),
            () -> assertThat(diskMap.remove("Lol")).isEqualTo("hah")
        );
    }

    @Test
    void testIsCorrectWorkingOfFunctions3() {
        diskMap.clear();
        diskMap.putAll(Map.of(
                "1", "one",
                "2", "two",
                "3", "three",
                "4", "four"
            )
        );
        assertAll(
            () -> {
                Set<String> expectedKeySet = Set.of("1", "2", "3", "4");
                assertThat(diskMap.keySet()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedKeySet);
            },
            () -> {
                Map<String, String> expected = Map.of(
                    "1", "one",
                    "2", "two",
                    "3", "three",
                    "4", "four"
                );

                assertThat(diskMap.entrySet()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected.entrySet());
                assertThat(diskMap.values()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected.values());
            }
        );
    }

    @Test
    void testIsEmptyAndSize() {
        diskMap.putAll(Map.of(
                "1", "one",
                "2", "two",
                "3", "three"
            )
        );
        diskMap.clear();

        assertAll(
            () -> assertThat(diskMap.isEmpty()).isTrue(),
            () -> assertThat(diskMap.size()).isEqualTo(0)
        );
    }
}
