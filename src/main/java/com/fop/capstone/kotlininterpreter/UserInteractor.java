package com.fop.capstone.kotlininterpreter;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class UserInteractor {

    public void readFromCustomPath(Scanner scanner) throws IOException {
        String filePath;

        while (true) {
            System.out.print("Enter the file path: ");
            filePath = scanner.nextLine();

            if (fileExists(filePath)) {
                break;
            }

            System.out.println("Invalid file path or the file is not accessible. Please try again.");
        }

        String content = readFile(filePath);

        System.out.println("\nProgram has started successfully!\n");
        System.out.println(content);
    }

    private void executeDirectInput(SimpleInterpreter interpreter, Scanner scanner) {
        System.out.println("Enter your code (type 'END' on a new line to finish):");
        StringBuilder code = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) break;
            code.append(line).append("\n");
        }

        System.out.println("\nCode executed successfully:\n");
        interpreter.eval(code.toString());
    }
    private void executePrewrittenAlgorithms(SimpleInterpreter interpreter, Scanner scanner) {
        File folder = new File("src/main/resources"); // Updated folder path for algorithms
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Algorithms folder not found. Please ensure 'src/main/resources' exists.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".kt")); // Filter only .kt files
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName());
            }

            System.out.println("Enter the number of the algorithm to execute:");
            int fileChoice = -1;
            while (fileChoice < 1 || fileChoice > files.length) {
                try {
                    fileChoice = Integer.parseInt(scanner.nextLine().trim());
                    if (fileChoice < 1 || fileChoice > files.length) {
                        System.err.println("Invalid choice. Please select a valid file number.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Please enter a number.");
                }
            }

            File selectedFile = files[fileChoice - 1];
            try {
                String code = Files.readString(selectedFile.toPath());
                System.out.println();
                System.out.println("Code from the selected file:");
                System.out.println(code);
                System.out.println("Code executed successfully:");
                System.out.println();
                interpreter.eval(code);
                System.out.println();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.err.println("No algorithm files found in the folder.");
        }
    }

    public void mainMenu(SimpleInterpreter interpreter, Scanner scanner) {
        boolean running = true;

        while (running) {
            int choice = -1;
            while (choice < 1 || choice > 4) {
                try {
                    System.out.println("Choose an option:");
                    System.out.println("1. Run a pre-written algorithm");
                    System.out.println("2. Enter a custom file path");
                    System.out.println("3. Enter code directly");
                    System.out.println("4. Quit");

                    choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice < 1 || choice > 4) {
                        System.err.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Please enter a number between 1 and 4.");
                }
            }

            switch (choice) {
                case 1 -> interpreter.executePrewrittenAlgorithms(interpreter, scanner);
                case 2 -> {
                    try {
                        interpreter.readFromCustomPath(scanner);
                    } catch (IOException e) {
                        System.err.println("An error occurred while reading the file: " + e.getMessage());
                    }
                }
                case 3 -> interpreter.executeDirectInput(interpreter, scanner);
                case 4 -> {
                    System.out.println("Exiting the interpreter. Goodbye!");
                    running = false;
                }
            }
        }
    }
}
