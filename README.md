# KIUPRoject

# Kotlin-Like Simple Interpreter

## Project Overview

This project implements a simple interpreter for a minimal subset of Kotlin-like syntax. It provides basic programming constructs such as variable declaration, arithmetic operations, and print functionality with Kotlin-style string interpolation. The interpreter is written in **Java** and designed to showcase the fundamental principles of interpreter design.

The interpreter can parse and execute simple code snippets, enabling users to understand how interpreters evaluate expressions, process variables, and manage program execution.

---

## Key Features

### 1. **Variable Declaration**
- **Mutable Variables (`var`)**:
    - Variables declared with `var` can be reassigned after initialization.
    - Example:
      ```kotlin
      var x = 10
      x = 20 // Allowed
      ```

- **Immutable Variables (`val`)**:
    - Variables declared with `val` cannot be reassigned after initialization.
    - Example:
      ```kotlin
      val y = 15
      y = 25 // Error: Cannot reassign immutable variable 'y'
      ```

### 2. **Arithmetic Operations**
The interpreter supports the following arithmetic operations:
- Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`), Modulus (`%`).

#### Usage Example:
```kotlin
var result = (10 + 5) * 2
print("Result: $result") // Output: Result: 30
```

### 3. **Compound Assignment**
- Supported compound operators: `+=`, `-=`, `*=`, `/=`, `%=`.
- Example:
    ```kotlin
    var x = 10
    x += 5 // x is now 15
    x *= 2 // x is now 30
    ```

### 4. **Print Statements**
- Print values using Kotlin-style interpolation:
    - Single-variable interpolation: `$variable`.
    - Expression interpolation: `${expression}`.
- Example:
    ```kotlin
    var x = 10
    print("Value of x: $x") // Output: Value of x: 10
    val y = x * 2
    print("Double of x: ${x * 2}") // Output: Double of x: 20
    ```

### 5. **Error Handling**
- The interpreter provides meaningful error messages for:
    - Using undeclared variables.
    - Reassigning immutable (`val`) variables.
    - Invalid syntax (e.g., mismatched parentheses, unknown operations).

#### Error Examples:
```kotlin
val x = 10
   // x = 20 // Error: Cannot reassign immutable variable 'x'

var y = (10 + 5 )// Error: Mismatched parentheses
```

---

## Limitations

- **Conditionals**: `if` statements are not supported.
- **Loops**: Iterative constructs like `while` are not yet implemented.
- **Advanced Constructs**: Recursion, functions, and arrays are not within the scope of this project.

---

## How It Works

### Code Execution Workflow:
1. **Parsing**: The interpreter reads the input code line by line and identifies commands such as variable declarations, reassignments, and print statements.
2. **Evaluation**:
    - Arithmetic expressions are processed using an `ArithmeticEvaluator` class, which:
        - Converts infix expressions to postfix (Reverse Polish Notation).
        - Evaluates the postfix expression using a stack.
        - Resolves variable values dynamically during evaluation.
3. **Execution**:
    - Executes parsed commands.
    - Maintains a map of variable names and their values.
    - Enforces rules for immutable (`val`) and mutable (`var`) variables.

---

## Supported Syntax

The following constructs are supported:
1. Variable Declarations:
    ```kotlin
    var x = 10
    val y = x + 5
    ```
2. Arithmetic Operations:
    ```kotlin
    var result = (10 + 5) * 2
    ```
3. Reassignment:
    ```kotlin
    var x = 10
    x += 5
    x *= 2
    ```
4. Print Statements:
    ```kotlin
    print("Value of x: $x")
    ```
5. Interpolation:
    ```kotlin
    print("Double of x: ${x * 2}")
    ```

---

## Example Code

Here’s an example program that can be executed with this interpreter:

```kotlin
var x = 10
print("Initial value of x: $x") // Output: Initial value of x: 10
x += 5
print("Updated value of x: $x") // Output: Updated value of x: 15
val y = x * 2
print("Value of y: $y") // Output: Value of y: 30
```

---

## How to Run

### How to Write Your Kotlin-Like Code?
In this project, users can:
- Modify the `code` variable in the `main` method to include their Kotlin-like code.
- Write the code manually in the terminal.
- Provide the path to a file containing the code.
- Choose from some pre-written algorithms included in the project.

### Steps to Execute:
1. Clone the repository to your local machine.
2. Open the project in IntelliJ or your preferred Java IDE.
3. Select one of the options to provide your Kotlin-like code (modify `code` variable, use terminal input, file path, or pre-written algorithm).
4. Run the `SimpleKotlinInterpreter` class to execute the code.


---

## Project Structure

- **ArithmeticEvaluator**:
    - Handles arithmetic expression evaluation.
    - Converts infix to postfix notation.
    - Evaluates postfix expressions using a stack.

- **SimpleKotlinInterpreter**:
    - Manages code parsing, execution, and variable storage.
    - Handles commands like `var`, `val`, and `print`.

---

## Learning Outcomes

By working on this project, you will:
- Understand how interpreters work, from parsing to execution.
- Learn to process arithmetic expressions using postfix evaluation.
- Gain insights into error handling for programming constructs.
- Appreciate the simplicity and power of designing a custom interpreter.

---



## Authors

This project was collaboratively developed to understand and implement the fundamental concepts of interpreter design.
