package com.fop.capstone.kotlininterpreter;

import com.fop.capstone.kotlininterpreter.utils.ExceptionHandlerUtils;
import com.fop.capstone.kotlininterpreter.utils.UserInputHandlerUtils;

import java.util.Scanner;

/**
 * An interpreter designed to process and execute Kotlin code.
 *
 * <p>
 * The interpreter supports fundamental programming constructs:
 * <ul>
 *     <li>Integer and boolean type variables</li>
 *     <li>Complex arithmetic and boolean operations</li>
 *     <li>I/O</li>
 *     <li>Control flow and while loop</li>
 * </ul>
 * </p>
 */
public class KotlinInterpreter {

    /**
     * Entry point for the interpretation process.
     *
     * <p>
     * This method ensures the interpretation logic is executed safely by wrapping it with
     * centralized exception handling.
     * </p>
     */
    public static void interpret() {
        ExceptionHandlerUtils.handle(KotlinInterpreter::execute, KotlinInterpreter::replay);
    }

    /* IMPLEMENTATION DETAILS */

    /**
     * Core execution method.
     *
     * <p>
     * Retrieves user input through a {@link Scanner}, processes the provided code, and
     * displays the output or relevant error messages.
     * </p>
     *
     * @param scanner a {@link Scanner} object for reading user input.
     * @throws Exception if any errors occur during the interpretation process.
     */
    private static void execute(Scanner scanner) throws Exception {
        String userInputCode = UserInputHandlerUtils.getUserInputCode(scanner);

        if (userInputCode == null) {
            return;
        }
    }

    /**
     * Determines if the user wants to replay the execution.
     *
     * <p>
     * This method prompts the user with a yes/no question and returns the result.
     * </p>
     *
     * @return {@code true} if the user wants to replay, {@code false} otherwise.
     */
    private static boolean replay() {
        return UserInputHandlerUtils.getUserAnswer();
    }

    /**
     * Main method for testing purposes.
     *
     * <p>
     * This method allows the user to test the code before using it in actual scenarios.
     * The actual usage of the interpreter should be done through the {@link #interpret()} method.
     * </p>
     */
    public static void main(String[] args) {
        interpret();
    }
}