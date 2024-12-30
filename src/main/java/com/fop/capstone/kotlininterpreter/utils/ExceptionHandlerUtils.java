package com.fop.capstone.kotlininterpreter.utils;

import com.fop.capstone.kotlininterpreter.interfaces.Executor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for centralized exception handling.
 * Provides a mechanism to handle and process exceptions caught during an execution of program.
 */
public class ExceptionHandlerUtils {

    /**
     * Handles exceptions thrown during the execution of the given {@link Executor}.
     *
     * <p>
     * Provides a {@link Scanner} for input handling.
     * </p>
     *
     * @param executor the task to execute, implemented as an {@link Executor} functional interface.
     */
    public static void handle(Executor executor) {
        try (Scanner scanner = new Scanner(System.in)) {
            executor.execute(scanner);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Input mismatch: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error occurred: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("A null reference was encountered: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}