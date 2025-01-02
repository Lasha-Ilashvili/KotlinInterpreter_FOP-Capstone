
# Kotlin Interpreter

## Overview
The Kotlin Interpreter is an efficient tool for interpreting Kotlin code. It is designed to provide a streamlined environment for experimenting with fundamental Kotlin programming constructs.
It supports various user-friendly features with intuitive design.

---

## Features

### Supported Constructs
- **Data Types**: Supports integer and boolean types.
- **Variable Declaration**: Supports `var` (mutable) and `val` (immutable).
- **Arithmetic and Boolean Operations**:
    - Operators: `+`, `-`, `*`, `/`, `%`, `>`, `<`, `>=`, `<=`, `==`, `!=`, `&&`, `||`, `!`.
    - Short-hand operators: `+=`, `-=`, `*=`, `/=`, `%=`
    - Post-increment
    - Complex logical and arithemtic expressions, with or without parentheses ensuring correct precedence.
- **Control Flow**:
    - `if`, `else if`, `else` blocks.
    - `while` loops with `break` keyword support.
    - Nesting of any levels of above blocks.
- **Input/Output (I/O)**:
    - Read input using `readln().toInt()` and display results using `print` or `println`.
- **String Interpolation**:
    - Supports `$variable` and `${expression}` for dynamic content in strings.
- **Scope Management**:
    - Variables have a well-defined scope and lifetime.
    - Nested blocks ensure proper scoping rules.

### Efficiency
- Handles deeply nested control structures and complex expressions efficiently using recursion and stack.
- Centralized exception handling ensures robustness and ability to control the flow.

---

## User Flow

1. **Menu Options**:
    - At the start user is provided with 4 options.
      1. Run a predifined algorithm.
         - 10 numbered algorithms are presented for user to choose from 
      2. Load a kotlin file from a custom path.
         - User is asked to type a path.
      4. Enter kotlin code directly via the console.
         - User is asked to type in a kotlin code. 
      6. Quit
    - In any case, after application is terminated (Whether successfully or by inputing incorrect code or user chose Quit option) <br> user is asked to start over

3. **Execution**:
    - Code is executed in real-time, with outputs and errors displayed to the console.

4. **Error Handling**:
    - Handles all incorrect user choices, asks again for correct format, identifies common pitfalls when communicating with user.

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

## General Error Handling

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
4. Make sure that your code is compiling, <br> or else CI will fail and you will be unable to merge.
5. Add at least one reviewer, this is a must and you will fail to merge otherwise.
6. All discussions or required changes must be resolved before being able to merge.
7. Good luck!
