# Kotlin Interpreter

This project provides a simple interpreter for a subset of the Kotlin programming language, designed to process and execute Kotlin code interactively. It supports a range of programming constructs and provides a mechanism to handle both user-defined scripts and predefined algorithms.

## Features

- **Data Types and Variables**:
    - Integer and Boolean variables.
    - Support for both mutable (`var`) and immutable (`val`) variables.
    - Variable lifecycle management across nested blocks and scopes.

- **Arithmetic and Boolean Expressions**:
    - Complex boolean and arithmetic expressions are supported.
    - Operators include `+`, `-`, `*`, `/`, `%`, `&&`, `||`, `!`, `==`, `!=`, `<`, `>`, `<=`, `>=`.

- **Control Flow**:
    - Supports `if`, `else if`, and `else` blocks for conditional logic.
    - Includes `while` loops with support for nested loops and blocks.
    - Supports the `break` keyword to exit loops.

- **I/O**:
    - Provides simple input and output operations using `readln()` for user input and `print`/`println` for output.
    - Supports Kotlin-style string interpolation in print statements.

- **Expressions**:
    - Evaluates arithmetic and boolean expressions dynamically.
    - Handles post-increment (`++`) and unary minus (`-`) operators.

- **Error Handling**:
    - Centralized error handling for syntax errors, runtime exceptions, and input mismatches.
    - Provides meaningful error messages for debugging.

- **Code Execution**:
    - Executes user-provided code in one of the following ways:
        1. From a pre-written algorithm in the `resources` directory.
        2. From a custom file path specified by the user.
        3. By directly entering code in the console.

## Usage

1. **Launch the Interpreter**:
   Run the `main` method in the `KotlinInterpreter` class.

2. **Choose Input Mode**:
   Select one of the following options from the menu:
    - Execute a predefined algorithm.
    - Read a Kotlin script from a file.
    - Enter Kotlin code directly into the console.
    - Exit the program.

3. **Supported Kotlin Code**:
   Provide valid Kotlin code using the supported constructs. For example:
   ```kotlin
   fun main() {
       print("Enter a number: ")
       var num = readln().toInt()
       if (num > 0) {
           println("Positive number!")
       } else {
           println("Non-positive number.")
       }
   }
