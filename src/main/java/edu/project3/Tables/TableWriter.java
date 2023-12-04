package edu.project3.Tables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

abstract class TableWriter {
    public static void writeToFile(String path, List<List<String>> lines) {
        File file = new File(path);

        try (FileWriter writer = new FileWriter(path)) {
            if (!file.exists()) {
                file.createNewFile();
            }

            for (List<String> report : lines) {
                for (String line : report) {
                    writer.write(line + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid path");
        }
    }
}
