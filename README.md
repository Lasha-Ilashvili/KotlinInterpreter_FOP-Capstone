# KotlinInterpreter

## Overview
KotlinInterpreter is a simplified interpreter for a subset of Kotlin-like syntax. It supports fundamental programming constructs such as:

- **Variable Declarations** (`var` and `val`).
- **Arithmetic and Boolean Operations**.
- **Simple Input/Output** (including reading from the console).
- **Control Flow** (`if/else`, `while`).
- **Basic Scoping Rules for Variables**.

Its primary goal is to provide a streamlined environment where you can experiment with Kotlin-like code interactively or by reading from files.

---

## Key Features

### Variable Declarations
- Supports `var` (mutable) and `val` (immutable).
- Immutable variables (`val`) cannot be reassigned.

### Arithmetic and Boolean Operations
- **Arithmetic Operators**: `+`, `-`, `*`, `/`, `%`.
- **Comparison Operators**: `>`, `<`, `>=`, `<=`, `==`, `!=`.
- **Logical Operators**: `&&`, `||`, `!`.
- Supports integer and Boolean types (internally treats `true` as `1` and `false` as `0` when needed).

### Simple I/O
- `print(...)` and `println(...)` for output.
- `readln().toInt()` for reading an integer from the user.

### Control Flow
- **Conditional Statements**:
    - `if/else if/else` statements with condition expressions (e.g., `if (x > 5) { ... }`).
- **Loops**:
    - `while` loops with the ability to break out of the loop.

### String Interpolation
- Within quotes, you can interpolate variables or expressions using `$var` or `${expression}`.
- Example:
  ```kotlin
  println("Value of x is $x") 
  println("x > 3 is ${x > 3}")

### How to Use
Build or compile the code (if needed) as you would any standard Java application with Maven/Gradle, or simply run it from an IDE.
Run the main entry point: com.fop.capstone.kotlininterpreter.KotlinInterpreter.
Menu Options:

- Option 1: Run a pre-written algorithm from the resources directory.
- Option 2: Specify a custom file path on your local file system.
- Option 3: Enter Kotlin-like code directly into the console (type code line by line, ending with END).

 Follow the prompts, and the interpreter will execute your code, printing results or errors.


# Sample Session
### When you launch the program, the following menu will appear:
- Run a pre-written algorithm
- Enter a custom file path
- Enter code directly
- Quit



The interpreter will then:

1. Prompt you for input (because of `readln().toInt()`).
2. Execute your code and display the result or any errors.

---

# View Output

- The interpreter prints any `println(...)` or `print(...)` statements to the console.
- Error messages (e.g., syntax errors, unmatched braces, undefined variables) are displayed with helpful messages.

---

# Updated Architecture

## `KotlinInterpreter` (Entry Point)

- Wraps the core interpretation logic in exception handling and user prompts.
- Uses `ExceptionHandlerUtils` for centralized error handling.
- Uses `UserInputHandlerUtils` to gather user input or file paths.
- Instantiates the `Delegator` and calls its `interpret(...)` method.

## `Delegator`

- Orchestrates how lines of code are parsed and executed.
- Manages statements like `while`, `if/else`, declarations, assignments, and printing.
- Uses `ScopeManager` to handle variable scoping and immutability checks.

## `ExpressionEvaluator`

- Responsible for tokenizing and evaluating arithmetic/boolean expressions (infix -> postfix -> evaluation).
- Called by `Delegator` to handle expressions in conditions, assignments, etc.

## `ScopeManager`

- Maintains a stack of scopes to store variable values, allowing for nested blocks.

----
# Error Handling

## Syntax Errors
- Unmatched braces: `"Unmatched '{'"` or `"Unexpected closing '}'"`.

## Undefined Variables
- `"Variable 'x' is not defined."`

## Immutability Violations
- `"Cannot reassign immutable variable: varName"`

## Runtime Exceptions
- Issues like division by zero are caught and reported as runtime errors.

---

# Contributing

## New Features
- Extend support for additional data types.
- Add advanced Kotlin features.

## Debugging Tools
- Improve error messages.
- Add logging capabilities.

## Optimizations
- Enhance performance for expression evaluation and scope management.

---

# Contribution Steps
1. Fork the repository.
2. Create a new branch.
3. Submit a pull request.
