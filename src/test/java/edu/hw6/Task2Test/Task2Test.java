package edu.hw6.Task2Test;

import edu.hw6.Task2.Task2;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task2Test {
    private static final String mainPath = "./src/test/java/edu/hw6/Task2Test/Tinkoff Bank Biggest Secret.txt";

    @Test
    void testCloneFile() throws IOException {
        Path copy = Task2.cloneFile(Path.of(mainPath));
        assertThat(copy.getFileName().toString()).isEqualTo("Tinkoff Bank Biggest Secret — копия.txt");

        Files.deleteIfExists(copy);
    }

    @Test
    void testMultiCloneFiles() throws IOException {
        Path copy1 = Task2.cloneFile(Path.of(mainPath));
        Path copy2 = Task2.cloneFile(Path.of(mainPath));
        Path copy3 = Task2.cloneFile(Path.of(mainPath));
        assertAll(
            () -> assertThat(copy1.getFileName().toString()).isEqualTo("Tinkoff Bank Biggest Secret — копия.txt"),
            () -> assertThat(copy2.getFileName().toString()).isEqualTo("Tinkoff Bank Biggest Secret — копия (2).txt"),
            () -> assertThat(copy3.getFileName().toString()).isEqualTo("Tinkoff Bank Biggest Secret — копия (3).txt")
        );

        Files.deleteIfExists(copy1);
        Files.deleteIfExists(copy2);
        Files.deleteIfExists(copy3);
    }
}
