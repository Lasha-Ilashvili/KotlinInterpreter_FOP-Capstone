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
 *     <li>Variable declarations</li>
 *     <li>Arithmetic and boolean operations</li>
 *     <li>Simple I/O</li>
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
        ExceptionHandlerUtils.handle(KotlinInterpreter::execute);
    }

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
        System.out.println("User input code:\n" + userInputCode);
    }
}