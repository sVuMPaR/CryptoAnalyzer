package Exceptions;

public class FileProcessingException extends RuntimeException {
    private final String defaultFilePath;

    // Constructor for message and default file path
    public FileProcessingException(String message, String defaultFilePath) {
        super(message);
        this.defaultFilePath = defaultFilePath;
    }

    // Constructor for just a message
    public FileProcessingException(String message) {
        super(message);
        this.defaultFilePath = null;
    }

    // Constructor for message and cause
    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.defaultFilePath = null;
    }

    public String getDefaultFilePath() {
        return defaultFilePath;
    }
}
