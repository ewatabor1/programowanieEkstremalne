package agh.edu.pl;

import agh.edu.pl.exceptions.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    void add_AddsUpToTwoNumbers_WhenStringIsValid() {
        assertEquals(0, calculator.add(""));
        assertEquals(1, calculator.add("1"));
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void add_AddsUpToAnyNumbers_WhenStringIsValid() {
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(130, calculator.add("10,90,10,20"));
    }

    @Test
    void add_AddsNumbersUsingNewlineDelimiter_WhenStringIsValid() {
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(130, calculator.add("10\n90\n10\n20"));
    }

    @Test
    void add_AddsNegativeNumbers_StringInvalid() {
        assertThrows(NegativeNumberException.class, () -> calculator.add("1,2,-1"), "Encountered negative numbers: -1");
        assertThrows(NegativeNumberException.class, () -> calculator.add("1,-2,-4"), "Encountered negative numbers: -2,-4");
    }
}
