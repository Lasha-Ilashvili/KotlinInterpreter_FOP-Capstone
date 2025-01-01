package com.fop.capstone.kotlininterpreter.interfaces;

import java.util.Scanner;

/**
 * Functional interface enclosing .
 * <p>
 * This interface is used to encapsulate whole logic of the application,
 * which requires {@link Scanner} for user input.
 * </p>
 */
@FunctionalInterface
public interface Executor {
    /**
     * Executes the task using the provided {@link Scanner}.
     *
     * @param scanner a {@link Scanner} object for reading user input.
     * @throws Exception if any error occurs during the execution of the task.
     */
    void execute(Scanner scanner) throws Exception;
}