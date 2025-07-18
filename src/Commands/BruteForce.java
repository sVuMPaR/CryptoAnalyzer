package Commands;

import File.FileProcessor;
import java.util.List;

public class BruteForce {
    public static void bruteForceFile(String inputPath, String outputPath) {
        List<String> lines = FileProcessor.readFile(inputPath);
        String encrypted = String.join(System.lineSeparator(), lines);

        long englishCount = encrypted.codePoints().filter(
            c -> (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
        ).count();
        long cyrillicCount = encrypted.codePoints().filter(
            c -> (c >= 'А' && c <= 'я') || c == 'Ё' || c == 'ё'
        ).count();

        StringBuilder results = new StringBuilder();

        if (englishCount > cyrillicCount) {
            results.append("=== English Alphabet (26 shifts) ===\n");
            for (int shift = 1; shift < 26; shift++) {
                String decrypted = Decrypt.decryptText(encrypted, shift);
                results.append("Shift ").append(shift).append(":\n").append(decrypted).append("\n\n");
            }
        } else if (cyrillicCount > englishCount) {
            results.append("=== Cyrillic Alphabet (32 shifts) ===\n");
            for (int shift = 1; shift < 32; shift++) {
                String decrypted = Decrypt.decryptText(encrypted, shift);
                results.append("Shift ").append(shift).append(":\n").append(decrypted).append("\n\n");
            }
        } else if (englishCount > 0 && cyrillicCount > 0) {
            results.append("Both English and Cyrillic detected. Showing both results.\n");
            // Optionally, show both as before
        } else {
            results.append("No English or Cyrillic letters detected in the text.\n");
        }

        FileProcessor.writeToFile(outputPath, results.toString());
    }

    // BruteForce decryption for a string (returns all results)
    // Расшифровка строки методом полного перебора (возвращает все варианты)
    public static String bruteForceText(String encrypted) {
        StringBuilder results = new StringBuilder();

        results.append("=== English Alphabet (26 shifts) ===\n");
        for (int shift = 1; shift < 26; shift++) {
            String decrypted = Decrypt.decryptText(encrypted, shift);
            results.append("Shift ").append(shift).append(":\n").append(decrypted).append("\n\n");
        }

        results.append("=== Cyrillic Alphabet (32 shifts) ===\n");
        for (int shift = 1; shift < 32; shift++) {
            String decrypted = Decrypt.decryptText(encrypted, shift);
            results.append("Shift ").append(shift).append(":\n").append(decrypted).append("\n\n");
        }

        return results.toString();
    }
}

