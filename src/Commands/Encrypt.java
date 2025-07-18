package Commands;

import File.FileProcessor;
import java.util.List;

public class Encrypt {
    private static final int EN_UPPER_A = 65, EN_LOWER_A = 97, EN_ALPHABET = 26;
    private static final int RU_UPPER_A = 1040, RU_LOWER_A = 1072, RU_ALPHABET = 32;
    private static final int ASCII_RANGE = 128;

    // Encrypts a string
    public static String encryptText(String text, int shift) {
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (Character.isLetter(ch) && ch < 128) { // English letters
                boolean isUpperCase = Character.isUpperCase(ch);
                int base = isUpperCase ? EN_UPPER_A : EN_LOWER_A;
                ch = (char) (((((ch - base) + shift) % EN_ALPHABET + EN_ALPHABET) % EN_ALPHABET) + base);
            } else if ((ch >= 'А' && ch <= 'Я') || (ch >= 'а' && ch <= 'я')) { // Cyrillic
                boolean isUpperCase = (ch >= 'А' && ch <= 'Я');
                int base = isUpperCase ? RU_UPPER_A : RU_LOWER_A;
                ch = (char) (((((ch - base) + shift) % RU_ALPHABET + RU_ALPHABET) % RU_ALPHABET) + base);
            } else {
                ch = (char) (((ch + shift) % ASCII_RANGE + ASCII_RANGE) % ASCII_RANGE);
            }
            chars[i] = ch;
        }
        return new String(chars);
    }

    // Encrypts a file (reads, encrypts, returns encrypted string)
    public static String encryptFile(String filename, int shift) {
        List<String> lines = FileProcessor.readFile(filename);
        String content = String.join(System.lineSeparator(), lines);
        return encryptText(content, shift);
    }
}
