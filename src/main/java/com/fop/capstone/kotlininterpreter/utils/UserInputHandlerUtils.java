package com.fop.capstone.kotlininterpreter.utils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public final class UserInputHandlerUtils {

    /**
     * Handles user input to determine the source of Kotlin code and retrieves the code accordingly.
     *
     * <p>The method provides a menu with options for the user to select the source of Kotlin code.
     * It supports the following options:
     * <ul>
     *     <li>Run a pre-written algorithm from the resources directory.</li>
     *     <li>Read code from a custom file path.</li>
     *     <li>Enter code directly via the console.</li>
     *     <li>Exit the program.</li>
     * </ul>
     *
     * <p>The method loops until a valid input is provided or the user chooses to exit.
     * It delegates specific tasks (e.g., reading files, validating input) to helper methods.
     *
     * @param scanner a {@link Scanner} object for reading user input.
     * @return a {@link String} containing the Kotlin code input by the user, or {@code null} if the user exits.
     * @throws IOException if an I/O error occurs while reading files.
     */
    public static String getUserInputCode(Scanner scanner) throws IOException {
        showMainMenu();

        int choice = getValidatedChoice(scanner, 4, "Enter your choice (1-4): ");

        return switch (choice) {
            case 1 -> processAlgorithmFiles(scanner);
            case 2 -> processCustomFilePath(scanner);
            case 3 -> processDirectInput(scanner);
            default -> null;
        };
    }

    /**
     * Prompts the user with a yes/no question to determine if they want to replay the execution.
     *
     * <p>
     * This method continuously prompts the user until a valid input ('Y' or 'N') is provided.
     * </p>
     *
     * @return {@code true} if the user wants to replay, {@code false} otherwise.
     */
    public static boolean getUserAnswer() {
        while (true) {
            System.out.print("\nDo you want to try again? (Y/N): ");

            String answer = scanner.next();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }

            if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("\nInvalid input. Please enter 'Y' or 'N'.");
        }
    }

    /**
     * Provides a {@link Scanner} object for reading user input.
     *
     * @return a {@link Scanner} object.
     */
    public static Scanner provideScanner() {
        return scanner;
    }

    /**
     * Closes the {@link Scanner} object and prints an exit message.
     */
    public static void closeScanner() {
        System.out.println("\nExiting the program. Goodbye!");
        scanner.close();
    }

    /* IMPLEMENTATION DETAILS */

    private static final String RESOURCES_DIRECTORY = "src/main/resources";
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Private constructor to prevent instantiation
     */
    private UserInputHandlerUtils() {
    }

    private static void showMainMenu() {
        System.out.println("\nWelcome to the Kotlin Interpreter!\n");
        System.out.println("Choose an option:");
        System.out.println("1. Run a pre-written algorithm");
        System.out.println("2. Enter a custom file path");
        System.out.println("3. Enter code directly");
        System.out.println("4. Quit");
    }

    private static String processAlgorithmFiles(Scanner scanner) throws IOException {
        File[] files = new File(RESOURCES_DIRECTORY).listFiles();

        displayFileList(Objects.requireNonNull(files));

        int fileChoice = getValidatedChoice(scanner, files.length, "\nEnter the number of the algorithm to execute: ");

        return FileUtils.readFile(files[fileChoice - 1].toPath());
    }

    private static void displayFileList(File[] files) {
        System.out.println("\nAvailable Kotlin algorithms:");

        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }
    }

    private static int getValidatedChoice(Scanner scanner, int maxOption, String message) {
        while (true) {
            System.out.print('\n' + message);

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= maxOption) return choice;

                System.out.println("\nInvalid choice. Please select a number between 1 and " + maxOption);
            } else {
                System.out.println("\nInvalid input. Please enter a valid number");
                scanner.nextLine();
            }
        }
    }

    private static String processCustomFilePath(Scanner scanner) throws IOException {
        while (true) {
            System.out.print("\nEnter the file path: ");

            scanner.nextLine();

            String filePath = scanner.nextLine().trim();

            if (FileUtils.fileExists(filePath)) return FileUtils.readFile(filePath);

            System.out.println("\nInvalid file path. Please try again.");
        }
    }

    private static String processDirectInput(Scanner scanner) {
        System.out.println("\nEnter your Kotlin code (type 'END' on a new line to finish):");

        StringBuilder code = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();
            if (line.contains("END")) break;

            code.append(line).append("\n");
        }

        return code.toString();
    }
}