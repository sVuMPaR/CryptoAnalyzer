package PACKAGE_NAME;

import java.util.Scanner;
import File.Validation;
import Exceptions.FileProcessingException;

public class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Выберите опцию / Choose an option:");
            System.out.println("1. Зашифровать файл / Encrypt a file");
            System.out.println("2. Расшифровать файл / Decrypt a file");
            System.out.println("3. Расшифровать файл методом перебора / Brute force decrypt a file");
            System.out.print("Введите 1, 2 или 3 / Enter 1, 2, or 3: ");
            String optionInput = scanner.nextLine();
            int option;
            // Check for right Option
            //Проверка на правильность Option
            try {
                option = Integer.parseInt(optionInput);
            } catch (NumberFormatException e) {
                System.out.println("Не правильное меню опции! Выходим. / Invalid menu option! Exiting.");
                return;
            }
            
            // Check for inputPath
            // Провека на правильность inputPath
            String inputPath;
            do {
                System.out.print("Enter the path to the source file: ");
                inputPath = scanner.nextLine();
                if (!Validation.isValidPath(inputPath)) {
                    System.out.println("Не правильный путь к файлу! Пожалуйста введите правильный / Invalid file path! Please enter a valid path.");
                }
            } while (!Validation.isValidPath(inputPath));

            // Check for outputPath
            // Провека на правильность outputPath
            String outputPath;
            do {
                System.out.print("Введите путь к файлу в который хотите сохранить / Enter the path to the destination file: ");
                outputPath = scanner.nextLine();
                if (!Validation.isValidPath(outputPath)) {
                    System.out.println("Не правильный путь к файлу! Пожалуйста введите правильный / Invalid file path! Please enter a valid path.");
                }
            } while (!Validation.isValidPath(outputPath));

            // Check Option for options with key(shift)
            // Проверка Option на опции с ключом
            int shift = 0;
            if (option == 1 || option == 2) {
                boolean validShift = false;
                while (!validShift) {
                    System.out.print("Введите ключ шифра(число) / Enter the shift value (integer): ");
                    String shiftInput = scanner.nextLine();
                    try {
                        shift = Integer.parseInt(shiftInput);
                        validShift = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Не верное число! Введите верное / Invalid number! Please enter a valid integer.");
                    }
                }
            }

            try {
                if (option == 1) {
                    MainApp.encryptFile(inputPath, outputPath, shift);
                    System.out.println("Шифрование файла успешно / File encrypted successfully.");
                } else if (option == 2) {
                    MainApp.decryptFile(inputPath, outputPath, shift);
                    System.out.println("Расшифровывание файла успешно / File decrypted successfully.");
                } else if (option == 3) {
                    MainApp.bruteForceFile(inputPath, outputPath);
                    System.out.println("Расшифровка методом перебора успешна. Проверьте все возможные варианты в файле / Brute force decryption complete. Check the output file for all possible results.");
                } else {
                    System.out.println("Выьрана не верная опция / Invalid option selected.");
                }
            } catch (FileProcessingException e) {
                System.err.println("Ошибка файла / File error: " + e.getMessage());
                if (e.getDefaultFilePath() != null) {
                    System.err.println("Дефолтный путь создан / Default file created at: " + e.getDefaultFilePath());
                }
            } catch (RuntimeException e) {
                System.err.println("Не предвиденная ошибка / Unexpected error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Возникла не предвиденная ошибка / An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
