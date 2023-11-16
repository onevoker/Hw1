package edu.hw6.Task4Test;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task4Test {
    private static final Path path = Path.of("./src/test/java/edu/hw6/Task4Test/test.txt");

    @Test
    void testWriteTextInFile() {
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        Task4.writeTextInFile(text, path);

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line = br.readLine();

            assertAll(
                () -> assertThat(line).isEqualTo(text),
                () -> assertThat(br.readLine()).isEqualTo(null)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
