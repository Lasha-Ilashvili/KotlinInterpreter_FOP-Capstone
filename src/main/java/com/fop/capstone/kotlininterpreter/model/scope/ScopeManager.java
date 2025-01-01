package com.fop.capstone.kotlininterpreter.model.scope;

import com.fop.capstone.kotlininterpreter.model.value.Value;

import java.util.*;

public class ScopeManager {

    private final Deque<Map<String, Value>> scopeStack = new ArrayDeque<>();
    private final Deque<Set<String>> immutablesStack = new ArrayDeque<>();

    public void pushScope() {
        scopeStack.offerLast(new HashMap<>());
        immutablesStack.offerLast(new HashSet<>());
    }

    public void popScope() {
        scopeStack.pollLast();
        immutablesStack.pollLast();
    }

    public Value getVariable(String name) {
        for (Map<String, Value> scope : scopeStack) {
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        throw new IllegalArgumentException("Variable '" + name + "' not defined in any scope.");
    }

    public void setVariable(String name, Value value) {
        for (Map<String, Value> scope : scopeStack) {
            if (scope.containsKey(name)) {
                scope.put(name, value);
                return;
            }
        }

        peekScope().put(name, value);
    }

    public boolean hasVariable(String name) {
        for (Map<String, Value> scope : scopeStack) {
            if (scope.containsKey(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isImmutable(String name) {
        return immutablesStack.peekLast().contains(name);
    }

    public void setImmutable(String name) {
        immutablesStack.peekLast().add(name);
    }

    public Map<String, Value> peekScope() {
        return scopeStack.peekLast();
    }
}
