
# Kotlin Interpreter

## Overview
The Kotlin Interpreter is a lightweight and efficient tool for interpreting Kotlin-like code. It is designed to provide a streamlined environment for experimenting with fundamental Kotlin programming constructs. Users can run predefined algorithms, enter code directly, or load scripts from files.

---

## Features

### Supported Constructs
- **Data Types**: Supports integer and boolean variables.
- **Variable Declaration**: Supports `var` (mutable) and `val` (immutable).
- **Arithmetic and Boolean Operations**:
    - Operators: `+`, `-`, `*`, `/`, `%`, `>`, `<`, `>=`, `<=`, `==`, `!=`, `&&`, `||`, `!`.
    - Complex logical expressions with correct precedence.
- **Control Flow**:
    - `if`, `else if`, `else` blocks.
    - `while` loops with support for nesting and `break`.
- **Input/Output (I/O)**:
    - Read input using `readln()` and display results using `print` or `println`.
- **String Interpolation**:
    - Supports `$variable` and `${expression}` for dynamic content in strings.
- **Scope Management**:
    - Variables have a well-defined scope and lifetime.
    - Nested blocks ensure proper scoping rules.

### Efficiency
- Handles deeply nested control structures and complex expressions efficiently.
- Centralized exception handling ensures robustness.

---

## User Flow

1. **Menu Options**:
    - Run a predefined algorithm from the `resources` directory.
    - Load a Kotlin script from a custom file path.
    - Enter Kotlin-like code directly via the console.
    - Exit the interpreter.
    - Support for user input errors and ability to start over.

2. **Execution**:
    - Code is executed in real-time, with outputs and errors displayed to the console.

3. **Error Handling**:
    - Detailed error messages for syntax issues, unmatched braces, undefined variables, and runtime errors like division by zero.

---

## How to Use

### Cloning the Repository
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. Compile the code using your preferred Java IDE or tool.

### Running the Interpreter
1. Use `KotlinInterpreter` class static method `interpret()`.
2. Follow the interactive prompts in the menu:
    - Choose to run predefined algorithms, load files, or enter code manually.
3. You can run class directly and test before usage.
---

## Error Handling

### Types of Errors
- **Syntax Errors**:
    - Reports unmatched braces: `"Unmatched '{'"` or `"Unexpected closing '}'"`.
- **Undefined Variables**:
    - Reports undefined variables: `"Variable 'x' is not defined."`
- **Immutability Violations**:
    - Reports reassignment of immutable variables: `"Cannot reassign immutable variable: varName"`.
- **Runtime and IO Exceptions**:
    - Catches issues like division by zero.
- **Invalid Expressions**:
    - Reports invalid expressions: `"Invalid expression: 'x + y'"`.
- **Input/Output Errors**:
    - Reports issues with reading input or displaying output.
- **General Exceptions**:
    - Catches any other exceptions and provides a generic error message.
- **User Input Errors**:
    - Reports invalid menu choices or input errors.
- **File Loading Errors**:
    - Reports issues with loading files or reading file contents.
- **Scope Violations**:
    - Reports issues with variable scope and lifetime.
---

### Steps to Contribute
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Submit a pull request for review.
