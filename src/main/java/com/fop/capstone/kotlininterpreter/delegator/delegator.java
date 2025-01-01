package com.fop.capstone.kotlininterpreter.delegator;

import com.fop.capstone.kotlininterpreter.evaluator.ExpressionEvaluator;
import com.fop.capstone.kotlininterpreter.model.exception.BreakInvokedException;
import com.fop.capstone.kotlininterpreter.model.scope.ScopeManager;
import com.fop.capstone.kotlininterpreter.model.value.Value;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delegator {

    private final ScopeManager scopeManager;
    private final ExpressionEvaluator expressionEvaluator;

    public Delegator() {
        this.scopeManager = new ScopeManager();
        this.expressionEvaluator = new ExpressionEvaluator(scopeManager);
    }

    public void interpret(String code, Scanner scanner) {
        String[] lines = handleEntry(code.trim());
        interpretBlock(scanner, lines, 0, lines.length);
    }

    private String[] handleEntry(String code) {
        Pattern pattern = Pattern.compile("fun\\s+main\\s*\\(\\s*\\)\\s*\\{");
        Matcher matcher = pattern.matcher(code);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Entry point 'main' function not found.");
        }

        code = code.substring(matcher.end());

        String[] lines = code.split("\n");

        getLastBracket(lines, 0, lines.length - 1);

        String lastLine = lines[lines.length - 1];
        lines[lines.length - 1] = lastLine.substring(0, lastLine.length() - 1);

        return lines;
    }

    private void interpretBlock(Scanner scanner, String[] lines, int start, int end) {
        scopeManager.pushScope();

        for (int i = start; i < end; i++) {
            String line = lines[i].trim();

            if (line.isEmpty()) continue;

            line = handleUserInput(line, scanner);

            if (line.startsWith("while")) {
                i = handleWhile(scanner, lines, i, end);
            } else if (line.startsWith("if")) {
                i = handleIfElse(scanner, lines, i, end);
            } else if (line.startsWith("var") || line.startsWith("val")) {
                handleDeclaration(line);
            } else if (isAssignment(line)) {
                handleAssignmentOrCompound(line);
            } else if (line.startsWith("print")) {
                handlePrint(line);
            } else if (line.contains("break")) {
                throw new BreakInvokedException();
            } else {
                throw new IllegalArgumentException("Unknown statement: " + line);
            }
        }

        scopeManager.popScope();
    }

    public static String handleUserInput(String code, Scanner scanner) {
        Pattern pattern = Pattern.compile("readln\\s*\\(\\s*\\)\\s*\\.\\s*toInt\\s*\\(\\s*\\)");
        Matcher matcher = pattern.matcher(code);

        return matcher.find() ? matcher.replaceFirst("" + scanner.nextInt()) : code;
    }

    private int handleWhile(Scanner scanner, String[] lines, int startIndex, int end) {
        String line = lines[startIndex].trim();
        validateWhileSyntax(line);

        int blockStart = startIndex + 1;

        int lastClosingBracket = getLastBracket(lines, blockStart, end);

        int blockEnd = findNearestClosingBracket(lines, blockStart, lastClosingBracket);

        while (evaluateCondition(line)) {
            try {
                interpretBlock(scanner, lines, blockStart, blockEnd);
            } catch (BreakInvokedException _unused) {
                return blockEnd;
            }
        }

        return lastClosingBracket;
    }

    private void validateWhileSyntax(String line) {
        Pattern pattern = Pattern.compile("(}\\s*)?while\\s*\\((.+)\\)\\s*\\{");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid syntax in while statement: " + line);
        }
    }

    private int handleIfElse(Scanner scanner, String[] lines, int startIndex, int end) {
        int lastClosingBracket = getLastBracket(lines, startIndex + 1, end);

        while (startIndex < lastClosingBracket) {
            String line = lines[startIndex].trim();

            int blockStart = startIndex + 1;

            if (line.startsWith("if") || line.contains("else if")) {
                validateIfElseSyntax(line);
                boolean condition = evaluateCondition(line);
                int blockEnd = findNearestClosingBracket(lines, blockStart, lastClosingBracket);

                if (condition) {
                    interpretBlock(scanner, lines, blockStart, blockEnd);
                    break;
                }

                startIndex = blockEnd;
            } else if (line.contains("else")) {
                validateIfElseSyntax(line);
                int blockEnd = findNearestClosingBracket(lines, blockStart, lastClosingBracket);
                interpretBlock(scanner, lines, blockStart, blockEnd);
                break;
            }
        }

        return lastClosingBracket;
    }

    private int getLastBracket(String[] lines, int startIndex, int end) {
        int openBrackets = 1;
        int lastClosingBracket = -1;

        for (int i = startIndex; i <= end; i++) {
            String line = lines[i].trim();

            if (line.contains("{")) openBrackets++;

            if (line.contains("}")) {
                openBrackets--;
                lastClosingBracket = i;
            }

            if (openBrackets < 0) {
                throw new IllegalArgumentException("Unexpected closing '}' at line " + (i + 1));
            }

            if (openBrackets == 0) break;
        }

        if (openBrackets != 0) {
            throw new IllegalArgumentException("Unmatched '{'");
        }

        return lastClosingBracket;
    }

    private void validateIfElseSyntax(String line) {
        Pattern pattern = Pattern.compile("(}\\s*)?(if|else if)\\s*\\((.+)\\)\\s*\\{|(}\\s*)?else\\s*\\{");

        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid syntax in if-else chain: " + line);
        }
    }

    private int findNearestClosingBracket(String[] lines, int startIndex, int end) {
        int lastClosingBracket = 0;
        int openBrackets = 1;
        int closeBrackets = 0;

        endFor:
        for (int i = startIndex; i <= end; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '{') openBrackets++;
                if (lines[i].charAt(j) == '}') closeBrackets++;

                if (openBrackets == closeBrackets) {
                    lastClosingBracket = i;
                    break endFor;
                }
            }
        }

        return lastClosingBracket;
    }

    private boolean evaluateCondition(String line) {
        int start = line.indexOf('(');
        int end = line.lastIndexOf(')');

        if (start == -1 || end == -1 || start >= end) {
            throw new IllegalArgumentException("Condition missing or malformed in line: " + line);
        }

        String condition = line.substring(start + 1, end).trim();
        Value condVal = expressionEvaluator.evaluateExpression(condition);

        return condVal.asBoolean();
    }

    private void handleDeclaration(String line) {
        Pattern pattern = Pattern.compile("(var|val)\\s+(\\w+)\\s*=\\s*(.+)|\\w+\\+\\+");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid declaration syntax: " + line);
        }

        String declarationType = matcher.group(1);
        String varName = matcher.group(2);
        String expression = matcher.group(3);

        if (scopeManager.peekScope().containsKey(varName)) {
            throw new IllegalArgumentException("Variable '" + varName + "' is already defined.");
        }

        Value value = expressionEvaluator.evaluateExpression(expression);

        if (declarationType.equals("val")) {
            scopeManager.setImmutable(varName);
        }

        scopeManager.setVariable(varName, value);
    }

    private void handleAssignmentOrCompound(String line) {
        Pattern compoundPattern = Pattern.compile("(\\w+)\\s*(\\+=|-=|\\*=|/=|%=|\\+\\+)\\s*(.*)");
        Matcher compoundMatcher = compoundPattern.matcher(line);

        if (compoundMatcher.matches()) {
            String varName = compoundMatcher.group(1);
            String op = compoundMatcher.group(2);
            String rightExpr = compoundMatcher.group(3);

            String expandedLine = getExpandedLine(varName, op, rightExpr);

            handleAssignment(expandedLine);
        } else {
            handleAssignment(line);
        }
    }

    private String getExpandedLine(String varName, String op, String rightExpr) {
        if (scopeManager.isImmutable(varName)) {
            throw new IllegalArgumentException("Cannot reassign immutable variable: " + varName);
        }

        String expandedExpr = switch (op) {
            case "+=" -> varName + " + " + rightExpr;
            case "-=" -> varName + " - " + rightExpr;
            case "*=" -> varName + " * " + rightExpr;
            case "/=" -> varName + " / " + rightExpr;
            case "%=" -> varName + " % " + rightExpr;
            case "++" -> varName + " + " + 1;
            default -> throw new IllegalArgumentException("Unsupported compound operator: " + op);
        };

        return varName + " = " + expandedExpr;
    }

    private void handleAssignment(String line) {
        Pattern pattern = Pattern.compile("(\\w+)\\s*=\\s*(.+)");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid assignment syntax: " + line);
        }

        String varName = matcher.group(1);
        String expression = matcher.group(2);

        if (!scopeManager.hasVariable(varName)) {
            throw new IllegalArgumentException("Variable '" + varName + "' is not defined.");
        }
        if (scopeManager.isImmutable(varName)) {
            throw new IllegalArgumentException("Cannot reassign immutable variable: " + varName);
        }

        Value value = expressionEvaluator.evaluateExpression(expression);
        scopeManager.setVariable(varName, value);
    }

    private void handlePrint(String line) {
        boolean isPrintln = line.startsWith("println");

        Pattern pattern = Pattern.compile("(?:print|println)\\s*\\((.*)\\)");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid print syntax: " + line);
        }

        String rawContent = matcher.group(1).trim();
        String evaluated = evaluateStringInterpolation(rawContent);

        if (isPrintln) {
            System.out.println(evaluated);
        } else {
            System.out.print(evaluated);
        }
    }

    private String evaluateStringInterpolation(String content) {
        Pattern topLevelPattern = Pattern.compile("\"([^\"]*)\"|([^\"]+)");
        Matcher matcher = topLevelPattern.matcher(content);

        StringBuilder result = new StringBuilder();
        int lastMatchEnd = 0;

        while (matcher.find()) {
            if (matcher.start() > lastMatchEnd) {
                String skipped = content.substring(lastMatchEnd, matcher.start());
                result.append(skipped);
            }

            lastMatchEnd = matcher.end();

            String quotedPart = matcher.group(1);
            String unquotedPart = matcher.group(2);

            if (quotedPart != null) {
                result.append(processQuotedString(quotedPart));
            } else if (unquotedPart != null) {
                if (unquotedPart.contains("$")) {
                    throw new IllegalArgumentException(
                            "Invalid usage of '$' outside of quotes: " + unquotedPart
                    );
                }

                result.append(processUnquotedExpression(unquotedPart));
            }
        }

        if (lastMatchEnd < content.length()) {
            String tail = content.substring(lastMatchEnd);
            result.append(tail);
        }

        return result.toString();
    }

    private String processQuotedString(String quotedContent) {
        Pattern interpolationPattern = Pattern.compile("\\$\\{([^}]+)}|\\$(\\w+)|([^$]+)");
        Matcher matcher = interpolationPattern.matcher(quotedContent);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                String expression = matcher.group(1).trim();
                Value value = expressionEvaluator.evaluateExpression(expression);
                sb.append(value);
            } else if (matcher.group(2) != null) {
                String variableName = matcher.group(2).trim();

                if (!scopeManager.hasVariable(variableName)) {
                    throw new IllegalArgumentException("Undefined variable: " + variableName);
                }

                sb.append(scopeManager.getVariable(variableName));
            } else if (matcher.group(3) != null) {
                sb.append(matcher.group(3));
            } else {
                throw new IllegalArgumentException(
                        "Unexpected interpolation syntax in: " + quotedContent
                );
            }
        }

        return sb.toString();
    }

    private String processUnquotedExpression(String text) {
        text = text.trim();

        if (scopeManager.hasVariable(text)) {
            return scopeManager.getVariable(text).toString();
        }

        try {
            Value val = expressionEvaluator.evaluateExpression(text);
            return val.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid syntax outside quotes: " + text, e);
        }
    }

    private boolean isAssignment(String line) {
        return line.matches("\\w+\\s*(=|\\+=|-=|\\*=|/=|%=|\\+\\+)\\s*.*");
    }

    public static void main(String[] args) throws IOException {
        String code = """
                fun main() {
                     print("Enter the first number: ")
                         var num1 = readln().toInt()
                
                         print("Enter the second number: ")
                         var num2 = readln().toInt()
                
                         if (num1 <= 0 || num2 <= 0) {
                             println("Please enter positive numbers only.")
                         } else {
                
                             while (num1 != num2) {
                                 if (num1 > num2) {
                                     num1 -= num2
                                 } else {
                                     num2 -= num1
                                 }
                             }
                
                             println("The GCD is: $num1")
                         }
                    }
                """;

        Delegator interpreter = new Delegator();
        try (Scanner scanner = new Scanner(System.in)) {
            interpreter.interpret(code, scanner);
        }
    }
}