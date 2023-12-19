package edu.hw9.Task2Test;

import edu.hw9.Task2.Task2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static edu.hw9.Task2.Task2.getDirectoriesWithMoreThan1000Files;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Nested
    class DirectorySearcherTest {
        private static final String DIRECTORY_PATH = "./src/test/java/edu/hw9/Task2Test/Directory";

        @BeforeEach void generateDirectoryWith1000Files() throws IOException {
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            for (int i = 0; i < 1001; i++) {
                String fileName = "file" + i + ".txt";
                File file = new File(directory, fileName);
                file.createNewFile();
            }
        }

        @Test
        void getDirectoriesWithMoreThan1000FilesTest() {
            File directory = new File(DIRECTORY_PATH);
            List<File> result = getDirectoriesWithMoreThan1000Files(directory);
            assertThat(result.size()).isEqualTo(1);
        }

        @AfterEach void deleteDirectoryWith1000Files() {

            File directory = new File(DIRECTORY_PATH);
            deleteFiles(directory);
            directory.delete();
        }

    }

    private static void deleteFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFiles(file);
                }
                file.delete();
            }
        }
    }

    @Nested
    class FileSearcherTest {
        private static final String DIRECTORY_PATH = "./src/test/java/edu/hw9/Task2Test/testDirectoryForFileSearcher";
        private static final File DIRECTORY = new File(DIRECTORY_PATH);
        private static final Predicate<File> PREDICATE_FOR_TXT = file -> file.getName().endsWith(".txt");
        private static final Predicate<File> PREDICATE_FOR_PNG = file -> file.getName().endsWith(".png");
        private static final Predicate<File> PREDICATE_FOR_TXT_AND_SIZE_MORE_THAN_ZERO =
            file -> file.length() > 0 && file.getName().endsWith(".txt");

        private List<String> getFileNames(List<File> files) {
            List<String> fileNames = new ArrayList<>();

            for (var file : files) {
                String fileName = file.getName();
                fileNames.add(fileName);
            }

            return fileNames;
        }

        @Test
        void getFilesByTxtPredicateTest() {
            List<File> txtFiles = Task2.getFilesByPredicate(DIRECTORY, PREDICATE_FOR_TXT);
            List<String> expectedFileNames = List.of(
                "first.txt",
                "second.txt",
                "third.txt"
            );

            List<String> actualFileNames = getFileNames(txtFiles);

            assertThat(txtFiles.size()).isEqualTo(3);
            assertThat(actualFileNames).usingRecursiveComparison().ignoringCollectionOrder()
                .isEqualTo(expectedFileNames);

        }

        @Test
        void getFilesByPngPredicateTest() {
            List<File> pngFiles = Task2.getFilesByPredicate(DIRECTORY, PREDICATE_FOR_PNG);
            List<String> expectedFileNames = List.of(
                "photo.png"
            );

            List<String> actualFileNames = getFileNames(pngFiles);

            assertThat(pngFiles.size()).isEqualTo(1);
            assertThat(actualFileNames).usingRecursiveComparison().ignoringCollectionOrder()
                .isEqualTo(expectedFileNames);
        }

        @Test
        void getFilesByTxtAndSizePredicateTest() {
            List<File> txtFilesWithSizeMoreThanZero =
                Task2.getFilesByPredicate(DIRECTORY, PREDICATE_FOR_TXT_AND_SIZE_MORE_THAN_ZERO);
            List<String> expectedFileNames = List.of(
                "third.txt"
            );

            List<String> actualFileNames = getFileNames(txtFilesWithSizeMoreThanZero);

            assertThat(txtFilesWithSizeMoreThanZero.size()).isEqualTo(1);
            assertThat(actualFileNames).usingRecursiveComparison().ignoringCollectionOrder()
                .isEqualTo(expectedFileNames);
        }
    }
}
