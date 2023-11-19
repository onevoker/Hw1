package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Task4 {
    private Task4() {
    }

    public static void writeTextInFile(String text, Path path) {
        try (
            OutputStream os = Files.newOutputStream(path);
            CheckedOutputStream cos = new CheckedOutputStream(os, new CRC32());
            BufferedOutputStream bos = new BufferedOutputStream(cos);
            OutputStreamWriter osw = new OutputStreamWriter(bos, UTF_8);
            PrintWriter pw = new PrintWriter(osw)
        ) {
            pw.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
