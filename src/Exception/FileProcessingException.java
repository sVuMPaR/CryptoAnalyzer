package Exceptions;

public class FileProcessingException extends RuntimeException {
    private final String defaultFilePath;

    // Constructor for message and defaultFilePath
    // Конструктор для message и defaultFilePath
    public FileProcessingException(String message, String defaultFilePath) {
        super(message);
        this.defaultFilePath = defaultFilePath;
    }

    // Constructor for just a message
    // Конструктор для message
    public FileProcessingException(String message) {
        super(message);
        this.defaultFilePath = null;
    }

    // Constructor for message and cause
    // Конструктор для message и cause
    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.defaultFilePath = null;
    }

    public String getDefaultFilePath() {
        return defaultFilePath;
    }
}
