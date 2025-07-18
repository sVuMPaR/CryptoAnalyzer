package File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.List;
import java.nio.charset.StandardCharsets;
import Exceptions.FileProcessingException;
import java.nio.file.FileAlreadyExistsException;

public class FileProcessor {
    private static final StandardOpenOption[] FILE_WRITE_OPTIONS = {
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING
    };

    private FileProcessor() {}

    public static List<String> readFile(String filename) {
        try {
            Path filePath = Path.of(filename);

            // If file does not exist, create default directory and file
            // Если файл не существует, создаёт дефолтную директорию и файл
            if (Validation.notExists(filePath)) {
                Path defaultDir = Path.of(System.getProperty("user.home"), "CryptoAnalyzerDefault");
                if (Validation.notExists(defaultDir)) {
                    Files.createDirectories(defaultDir);
                }
                Path defaultFile = defaultDir.resolve("default.txt");
                if (Validation.notExists(defaultFile)) {
                    Files.writeString(defaultFile, "This is a default file.", StandardCharsets.UTF_8, StandardOpenOption.CREATE);
                }
                throw new FileProcessingException(
                    "File not found: " + filename + ". Default file created at: " + defaultFile.toAbsolutePath(),
                    defaultFile.toAbsolutePath().toString()
                );
            }

            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

            // Check for empty file
            // Проверка на пустой файл
            if (lines.isEmpty() || (lines.size() == 1 && lines.get(0).isEmpty())) {
                throw new FileProcessingException("File is empty: " + filename);
            }

            return lines;
        } catch (IOException e) {
            throw new FileProcessingException("Failed to read file: " + filename, e);
        }
    }

    public static void writeToFile(String filename, String content) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename must not be null or empty.");
        }
        try {
            Path path = Path.of(filename);
            if (Files.exists(path) && Files.isDirectory(path)) {
                throw new IllegalArgumentException("The specified path is a directory, not a file: " + filename);
            }
            writeToFile(path, content);
        } catch (java.nio.file.InvalidPathException e) {
            throw new IllegalArgumentException("Invalid file path: " + filename, e);
        }
    }

    private static void writeToFile(Path filePath, String content) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path must not be null.");
        }
        try {
            if (Files.exists(filePath) && Files.isDirectory(filePath)) {
                throw new IllegalArgumentException("The specified path is a directory, not a file: " + filePath);
            }
            Validation.ensureParentDirectoryExists(filePath);
            System.out.println("Attempting to write to: " + filePath.toAbsolutePath());
            Files.writeString(filePath, content, StandardCharsets.UTF_8, FILE_WRITE_OPTIONS);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + filePath, e);
        }
    }
}
