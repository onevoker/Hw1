package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    private Task2() {
    }

    public static Path cloneFile(Path path) {
        var partsOfFileName = path.getFileName().toString().split("\\.");
        String fileName = partsOfFileName[0];
        String fileExtension = "." + partsOfFileName[1];

        Path newPath = path.resolveSibling(fileName + " — копия" + fileExtension);
        int copyNumber = 2;

        while (Files.exists(newPath)) {
            newPath = newPath.resolveSibling((fileName + " — копия (%s)" + fileExtension).formatted(copyNumber));
            ++copyNumber;
        }

        try {
            return Files.copy(path, newPath);
        } catch (IOException e) {
            throw new RuntimeException("Can't copy file, please check, is path correct");
        }
    }
}
