package Commands;

public class Decrypt {
    // Decrypts a string
    public static String decryptText(String text, int shift) {
        return Encrypt.encryptText(text, -shift);
    }

    // Decrypts a file (reads, decrypts, returns decrypted string)
    public static String decryptFile(String filename, int shift) {
        // shift is positive, we negate it in the logic
        return Encrypt.encryptFile(filename, -shift);
    }
}

