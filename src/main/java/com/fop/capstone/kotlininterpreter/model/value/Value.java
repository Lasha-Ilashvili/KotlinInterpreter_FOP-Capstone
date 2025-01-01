package com.fop.capstone.kotlininterpreter.model.value;

public sealed interface Value permits IntValue, BoolValue {

    default Value plus(Value other) {
        throw new UnsupportedOperationException("plus not supported by " + getClass().getSimpleName());
    }

    default Value minus(Value other) {
        throw new UnsupportedOperationException("minus not supported by " + getClass().getSimpleName());
    }

    default Value times(Value other) {
        throw new UnsupportedOperationException("times not supported by " + getClass().getSimpleName());
    }

    default Value div(Value other) {
        throw new UnsupportedOperationException("div not supported by " + getClass().getSimpleName());
    }

    default Value mod(Value other) {
        throw new UnsupportedOperationException("mod not supported by " + getClass().getSimpleName());
    }

    default Value greaterThan(Value other) {
        throw new UnsupportedOperationException("gt not supported by " + getClass().getSimpleName());
    }

    default Value lessThan(Value other) {
        throw new UnsupportedOperationException("lt not supported by " + getClass().getSimpleName());
    }

    default Value greaterOrEqual(Value other) {
        throw new UnsupportedOperationException("ge not supported by " + getClass().getSimpleName());
    }

    default Value lessOrEqual(Value other) {
        throw new UnsupportedOperationException("le not supported by " + getClass().getSimpleName());
    }

    default Value equal(Value other) {
        throw new UnsupportedOperationException("eq not supported by " + getClass().getSimpleName());
    }

    default Value notEqual(Value other) {
        throw new UnsupportedOperationException("ne not supported by " + getClass().getSimpleName());
    }

    default Value and(Value other) {
        throw new UnsupportedOperationException("and not supported by " + getClass().getSimpleName());
    }

    default Value or(Value other) {
        throw new UnsupportedOperationException("or not supported by " + getClass().getSimpleName());
    }

    default Value not() {
        throw new UnsupportedOperationException("not not supported by " + getClass().getSimpleName());
    }

    default Value unaryMinus() {
        throw new UnsupportedOperationException("unaryMinus not supported by " + getClass().getSimpleName());
    }

    default Value increment() {
        throw new UnsupportedOperationException("increment not supported by " + getClass().getSimpleName());
    }

    default int asInt() {
        throw new UnsupportedOperationException("Cannot interpret this " + this.getClass().getSimpleName() + " as int: " + this);
    }

    default boolean asBoolean() {
        throw new UnsupportedOperationException("Cannot interpret this " + this.getClass().getSimpleName() + " as boolean: " + this);
    }
}