package Commands;

public class Decrypt {
    // Decrypts a string
    // Расшифровка строки
    public static String decryptText(String text, int shift) {
        return Encrypt.encryptText(text, -shift);
    }

    // Decrypts a file (reads, decrypts, returns decrypted string)
    // Расшифровавыет файл (считываает, расшифровывает, возвращает расшифрованную строку)
    public static String decryptFile(String filename, int shift) {
        // shift is positive, we negate it in the logic
        return Encrypt.encryptFile(filename, -shift);
    }
}

