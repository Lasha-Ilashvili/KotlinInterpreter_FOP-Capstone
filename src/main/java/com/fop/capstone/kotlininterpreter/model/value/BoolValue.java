package com.fop.capstone.kotlininterpreter.model.value;

public final class BoolValue implements Value {

    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public static Value of(boolean value) {
        return new BoolValue(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int asInt() {
        return value ? 1 : 0;
    }

    @Override
    public boolean asBoolean() {
        return value;
    }

    @Override
    public Value and(Value other) {
        return BoolValue.of(value && other.asBoolean());
    }

    @Override
    public Value or(Value other) {
        return BoolValue.of(value || other.asBoolean());
    }

    @Override
    public Value equal(Value other) {
        return BoolValue.of(value == other.asBoolean());
    }

    @Override
    public Value notEqual(Value other) {
        return BoolValue.of(value != other.asBoolean());
    }

    @Override
    public Value not() {
        return BoolValue.of(!value);
    }
}