package com.fop.capstone.kotlininterpreter.utils;

import com.fop.capstone.kotlininterpreter.interfaces.Executor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Utility class for centralized exception handling.
 * Provides a mechanism to handle and process exceptions caught during an execution of program.
 */
public final class ExceptionHandlerUtils {

    /**
     * Handles exceptions thrown during the execution of the given {@link Executor}.
     *
     * <p>
     * Provides a {@link Scanner} for input handling and allows replaying the execution based on user input.
     * </p>
     *
     * @param executor the task to execute, implemented as an {@link Executor} functional interface.
     * @param replay   a {@link Supplier} that determines if the execution should be replayed.
     */
    public static void handle(Executor executor, Supplier<Boolean> replay) {
        try {
            executor.execute(UserInputHandlerUtils.provideScanner());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Input mismatch: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("A null reference was encountered: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            boolean shouldReplay = replay.get();
            if (shouldReplay) {
                handle(executor, replay);
            } else {
                UserInputHandlerUtils.closeScanner();
            }
        }
    }
}