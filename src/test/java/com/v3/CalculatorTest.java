package com.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    @Test
    void add() {
        var calculator = new Calculator();
        assertEquals(3, calculator.add(1, 2));
    }

    @Test
    void addWithNull() {
        var calculator = new Calculator();
        // test fail
        assertEquals(2, calculator.add(null, 2));
    }

    @Test
    void minus() {
        var calculator = new Calculator();
        assertEquals(2, calculator.minus(2, null));
    }

}