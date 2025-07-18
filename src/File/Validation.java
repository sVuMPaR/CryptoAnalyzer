package File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class Validation {
    /**
     * Ensures the parent directory of the given file path exists. / Удостовериться, что директория файла существует.
     * If it does not exist, it will be created. / Если не существует, то будет создана.
     * @param filePath The file path whose parent directory should be checked/created. / @param filePath Путь к файлу, чья директория должная быть проверенна/создана.
     * @throws IOException if directory creation fails. / @throws IOException, если создать директорию не возможно.
     */
    public static void ensureParentDirectoryExists(Path filePath) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    public static Path getOrCreateDefaultEncryptFile() {
        Path defaultDir = Path.of(System.getProperty("user.home"), "CryptoAnalyzerDefault");
        try {
            if (notExists(defaultDir)) {
                Files.createDirectories(defaultDir);
            }
            Path defaultFile = defaultDir.resolve("encrypt.txt");
            if (notExists(defaultFile)) {
                Files.createFile(defaultFile);
            }
            return defaultFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create default encrypt file.", e);
        }
    }

    public static boolean exists(Path path) {
        return Files.exists(path);
    }

    public static boolean notExists(Path path) {
        return !Files.exists(path);
    }

    public static boolean isValidPath(String pathStr) {
        if (pathStr == null || pathStr.trim().isEmpty()) {
            return false;
        }
        try {
            Path path = Path.of(pathStr);
            // Windows forbidden characters
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                String forbidden = "<>:\"/\\|?*";
                for (char c : forbidden.toCharArray()) {
                    if (pathStr.indexOf(c) >= 0) {
                        return false;
                    }
                }
            }
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }
}
