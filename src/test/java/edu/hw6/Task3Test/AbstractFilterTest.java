package edu.hw6.Task3Test;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.regexContains;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.REGULAR_FILE;
import static edu.hw6.Task3.AbstractFilter.READABLE_FILE;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFilterTest {
    private static final Path dir = Path.of("./src/test/java/edu/hw6/Task3Test/FilesForTest");

    @Test
    void testLargerThan() throws IOException {
        List<String> expectedNamesOfFiles = List.of("angryMorty.jpg", "randomCat.png", "text.txt");
        List<String> fitNamesFiles = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, largerThan(10))) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testMagicNumber() throws IOException {
        List<String> expectedNamesOfFiles = List.of("angryMorty.jpg");
        List<String> fitNamesFiles = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            dir,
            magicNumber(new byte[] {(byte) 255, (byte) 216})
        )) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testGlobMatches() throws IOException {
        List<String> expectedNamesOfFiles = List.of("randomCat.png");
        List<String> fitNamesFiles = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, globMatches("*.png"))) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testRegexContains() throws IOException {
        List<String> expectedNamesOfFiles = List.of("angryMorty.jpg", "randomCat.png");
        List<String> fitNamesFiles = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, regexContains("[A-z]{9}"))) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testFindRandomCat() throws IOException {
        List<String> expectedNamesOfFiles = List.of("randomCat.png");
        List<String> fitNamesFiles = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE_FILE)
            .and(largerThan(200))
            .and(globMatches("*.png"))
            .and(regexContains("(Cat)"))
            .and(magicNumber(new byte[] {(byte) 0x89, 'P', 'N', 'G'}));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testFindAngryMorty() throws IOException {
        List<String> expectedNamesOfFiles = List.of("angryMorty.jpg");
        List<String> fitNamesFiles = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE_FILE)
            .and(largerThan(200))
            .and(globMatches("*.jpg"))
            .and(regexContains("(angry)"))
            .and(magicNumber(new byte[] {(byte) 255, (byte) 216}));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testFindAllCats() throws IOException {
        List<String> expectedNamesOfFiles = List.of("angryMorty.jpg", "randomCat.png");
        List<String> fitNamesFiles = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE_FILE)
            .and(largerThan(200))
            .and(regexContains("(a)"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedNamesOfFiles);
    }

    @Test
    void testFindText() throws IOException {
        List<String> expectedNamesOfFiles = List.of("text.txt");
        List<String> fitNamesFiles = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE_FILE)
            .and(regexContains("(text)"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> fitNamesFiles.add(path.getFileName().toString()));
        }

        assertThat(fitNamesFiles).isEqualTo(expectedNamesOfFiles);
    }
}
