package File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class FileProcessor {
    private static final StandardOpenOption[] FILE_WRITE_OPTIONS = {StandardOpenOption.CREATE, StandardOpenOption.APPEND};

    // Private constructor to prevent instantiation
    private FileProcessor() {}

    // Reads all lines from a file and returns them as a List<String>
    public static List<String> readFile(String filename) {
        try {
            Path filePath = Path.of(filename);
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filename, e);
        }
    }

    // Appends content to a file, creating it if it doesn't exist
    public static void appendToFile(String filename, String content) {
        try {
            Path filePath = Path.of(filename);
            Files.writeString(filePath, content, StandardCharsets.UTF_8, FILE_WRITE_OPTIONS);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + filename, e);
        }
    }
}
