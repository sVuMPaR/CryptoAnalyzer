package PACKAGE_NAME;

import java.util.Scanner;
import File.Validation;
import Exceptions.FileProcessingException;

public class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Choose an option:");
            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            System.out.println("3. Brute force decrypt a file");
            System.out.print("Enter 1, 2, or 3: ");
            String optionInput = scanner.nextLine();
            int option;
            try {
                option = Integer.parseInt(optionInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid menu option! Exiting.");
                return;
            }

            String inputPath;
            do {
                System.out.print("Enter the path to the source file: ");
                inputPath = scanner.nextLine();
                if (!Validation.isValidPath(inputPath)) {
                    System.out.println("Invalid file path! Please enter a valid path.");
                }
            } while (!Validation.isValidPath(inputPath));

            String outputPath;
            do {
                System.out.print("Enter the path to the destination file: ");
                outputPath = scanner.nextLine();
                if (!Validation.isValidPath(outputPath)) {
                    System.out.println("Invalid file path! Please enter a valid path.");
                }
            } while (!Validation.isValidPath(outputPath));

            int shift = 0;
            if (option == 1 || option == 2) {
                boolean validShift = false;
                while (!validShift) {
                    System.out.print("Enter the shift value (integer): ");
                    String shiftInput = scanner.nextLine();
                    try {
                        shift = Integer.parseInt(shiftInput);
                        validShift = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number! Please enter a valid integer.");
                    }
                }
            }

            try {
                if (option == 1) {
                    Application.encryptFile(inputPath, outputPath, shift);
                    System.out.println("File encrypted successfully.");
                } else if (option == 2) {
                    Application.decryptFile(inputPath, outputPath, shift);
                    System.out.println("File decrypted successfully.");
                } else if (option == 3) {
                    Application.bruteForceFile(inputPath, outputPath);
                    System.out.println("Brute force decryption complete. Check the output file for all possible results.");
                } else {
                    System.out.println("Invalid option selected.");
                }
            } catch (FileProcessingException e) {
                System.err.println("File error: " + e.getMessage());
                if (e.getDefaultFilePath() != null) {
                    System.err.println("Default file created at: " + e.getDefaultFilePath());
                }
            } catch (RuntimeException e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
