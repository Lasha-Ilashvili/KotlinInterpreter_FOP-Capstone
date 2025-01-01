package com.fop.capstone.kotlininterpreter.model.value;

public final class IntValue implements Value {

    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    public static Value of(int value) {
        return new IntValue(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int asInt() {
        return value;
    }

    @Override
    public boolean asBoolean() {
        return value != 0;
    }

    @Override
    public Value plus(Value other) {
        return IntValue.of(value + other.asInt());
    }

    @Override
    public Value minus(Value other) {
        return IntValue.of(value - other.asInt());
    }

    @Override
    public Value unaryMinus() {
        return IntValue.of(-value);
    }

    @Override
    public Value increment() {
        return IntValue.of(value++);
    }

    @Override
    public Value times(Value other) {
        return IntValue.of(value * other.asInt());
    }

    @Override
    public Value div(Value other) {
        return IntValue.of(value / other.asInt());
    }

    @Override
    public Value mod(Value other) {
        return IntValue.of(value % other.asInt());
    }

    @Override
    public Value greaterThan(Value other) {
        return BoolValue.of(value > other.asInt());
    }

    @Override
    public Value lessThan(Value other) {
        return BoolValue.of(value < other.asInt());
    }

    @Override
    public Value greaterOrEqual(Value other) {
        return BoolValue.of(value >= other.asInt());
    }

    @Override
    public Value lessOrEqual(Value other) {
        return BoolValue.of(value <= other.asInt());
    }

    @Override
    public Value equal(Value other) {
        return BoolValue.of(value == other.asInt());
    }

    @Override
    public Value notEqual(Value other) {
        return BoolValue.of(value != other.asInt());
    }
}
