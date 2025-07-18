package PACKAGE_NAME;

import File.FileProcessor;
import Commands.Encrypt;
import Commands.Decrypt;
import java.util.List;

public class Application {
    public static void encryptFile(String inputPath, String outputPath, int shift) {
        String encrypted = Encrypt.encrypt(inputPath, shift);
        FileProcessor.appendToFile(outputPath, encrypted);
    }

    public static void decryptFile(String inputPath, String outputPath, int shift) {
        try {
            List<String> lines = FileProcessor.readFile(inputPath);
            String encryptedContent = String.join(System.lineSeparator(), lines);
            String decrypted = Decrypt.decryptText(encryptedContent, shift);
            FileProcessor.writeToFile(outputPath, decrypted);
        } catch (FileProcessingException e) {
            if (e.getDefaultFilePath() != null) {
                // Write decrypted data to the default file
                String decrypted = Decrypt.decryptText("", shift); // or whatever logic you want for empty/default
                FileProcessor.writeToFile(e.getDefaultFilePath(), decrypted);
                System.out.println("Decrypted data written to default file: " + e.getDefaultFilePath());
            } else {
                throw e; // rethrow if not a missing file case
            }
        }
    }
}