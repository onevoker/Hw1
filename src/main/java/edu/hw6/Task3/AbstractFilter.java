package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE_FILE = Files::isReadable;

    default AbstractFilter and(AbstractFilter o) {
        return path -> accept(path) && o.accept(path);
    }

    static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) > size;
    }

    static AbstractFilter magicNumber(byte[] bytes) {
        return path -> {
            byte[] fileBytes = Files.readAllBytes(path);

            if (fileBytes.length < bytes.length) {
                return false;
            }

            for (int i = 0; i < bytes.length; i++) {
                if (fileBytes[i] != bytes[i]) {
                    return false;
                }
            }

            return true;
        };
    }

    static AbstractFilter globMatches(String glob) {
        return path -> {
            String regex = "\\.";

            String extension = path.getFileName().toString().split(regex)[1];
            String expectedExtension = glob.split(regex)[1];

            return extension.equals(expectedExtension);
        };
    }

    static AbstractFilter regexContains(String regex) {
        return path -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(path.getFileName().toString());

            return matcher.find();
        };
    }
}
