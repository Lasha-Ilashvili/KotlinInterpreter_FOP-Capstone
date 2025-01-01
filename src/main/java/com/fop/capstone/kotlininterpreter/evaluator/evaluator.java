package com.fop.capstone.kotlininterpreter.evaluator;

import com.fop.capstone.kotlininterpreter.model.scope.ScopeManager;
import com.fop.capstone.kotlininterpreter.model.value.BoolValue;
import com.fop.capstone.kotlininterpreter.model.value.IntValue;
import com.fop.capstone.kotlininterpreter.model.value.Value;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {

    private static final Map<String, BiFunction<Value, Value, Value>> BINARY_OPERATORS = Map.ofEntries(
            Map.entry("+", Value::plus),
            Map.entry("-", Value::minus),
            Map.entry("*", Value::times),
            Map.entry("/", Value::div),
            Map.entry("%", Value::mod),
            Map.entry(">", Value::greaterThan),
            Map.entry("<", Value::lessThan),
            Map.entry(">=", Value::greaterOrEqual),
            Map.entry("<=", Value::lessOrEqual),
            Map.entry("==", Value::equal),
            Map.entry("!=", Value::notEqual),
            Map.entry("&&", Value::and),
            Map.entry("||", Value::or)
    );

    private static final Map<String, Function<Value, Value>> UNARY_OPERATORS = Map.ofEntries(
            Map.entry("!", Value::not),
            Map.entry("-", Value::unaryMinus),
            Map.entry("++", Value::increment)
    );

    private static final Map<String, Integer> PRECEDENCE = new HashMap<>(Map.ofEntries(
            Map.entry("++", 5),
            Map.entry("!", 4),
            Map.entry("*", 3),
            Map.entry("/", 3),
            Map.entry("%", 3),
            Map.entry("+", 2),
            Map.entry("-", 2),
            Map.entry(">", 1),
            Map.entry("<", 1),
            Map.entry(">=", 1),
            Map.entry("<=", 1),
            Map.entry("==", 1),
            Map.entry("!=", 1),
            Map.entry("&&", 0),
            Map.entry("||", -1)
    ));

    private static final Map<String, Boolean> RIGHT_ASSOCIATIVE = Map.of(
            "!", true
    );

    private final ScopeManager scopeManager;

    public ExpressionEvaluator(ScopeManager scopeManager) {
        this.scopeManager = scopeManager;
    }

    public Value evaluateExpression(String expression) {
        List<String> tokens = tokenize(expression);
        List<String> postfix = infixToPostfix(tokens);
        return evaluatePostfix(postfix);
    }

    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();

        Matcher matcher = Pattern.compile(
                "\\s*(\\d+|true|false|\\w+|==|!=|>=|<=|&&|\\|\\||\\+\\+|[+\\-*/%()><=!])\\s*"
        ).matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group().trim());
        }

        return tokens;
    }

    private List<String> infixToPostfix(List<String> tokens) {
        List<String> postfix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token) || isBooleanLiteral(token) || scopeManager.hasVariable(token)) {
                postfix.add(token);
            } else if ("(".equals(token)) {
                operatorStack.push(token);
            } else if (")".equals(token)) {
                while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek())) {
                    postfix.add(operatorStack.pop());
                }
                if (operatorStack.isEmpty() || !"(".equals(operatorStack.pop())) {
                    throw new IllegalArgumentException("Mismatched parentheses in expression.");
                }
            } else if (PRECEDENCE.containsKey(token)) {
                while (!operatorStack.isEmpty()
                        && PRECEDENCE.containsKey(operatorStack.peek())
                        && ((RIGHT_ASSOCIATIVE.getOrDefault(token, false)
                        && PRECEDENCE.get(token) < PRECEDENCE.get(operatorStack.peek()))
                        || (!RIGHT_ASSOCIATIVE.getOrDefault(token, false)
                        && PRECEDENCE.get(token) <= PRECEDENCE.get(operatorStack.peek())))
                ) {
                    postfix.add(operatorStack.pop());
                }
                operatorStack.push(token);
            } else {
                throw new IllegalArgumentException("Unknown token: " + token);
            }
        }

        while (!operatorStack.isEmpty()) {
            String op = operatorStack.pop();
            if ("(".equals(op) || ")".equals(op)) {
                throw new IllegalArgumentException("Mismatched parentheses in expression.");
            }
            postfix.add(op);
        }

        return postfix;
    }

    private Value evaluatePostfix(List<String> postfix) {
        Stack<Value> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(IntValue.of(Integer.parseInt(token)));
            } else if (isBooleanLiteral(token)) {
                stack.push(BoolValue.of(token.equals("true")));
            } else if (scopeManager.hasVariable(token)) {
                stack.push(scopeManager.getVariable(token));
            } else {
                if (UNARY_OPERATORS.containsKey(token) && stack.size() < 2) {
                    Value operand = stack.pop();
                    stack.push(UNARY_OPERATORS.get(token).apply(operand));
                } else {
                    Value right = stack.pop();
                    Value left = stack.pop();
                    stack.push(applyOperator(token, left, right));
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression. Stack ended with size=" + stack.size());
        }

        return stack.pop();
    }

    private Value applyOperator(String op, Value left, Value right) {
        BiFunction<Value, Value, Value> func = BINARY_OPERATORS.get(op);

        if (func == null) {
            throw new IllegalArgumentException("Unknown operator: " + op);
        }

        return func.apply(left, right);
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBooleanLiteral(String token) {
        return "true".equals(token) || "false".equals(token);
    }
}