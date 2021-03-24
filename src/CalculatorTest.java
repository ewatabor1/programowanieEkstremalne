import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void Add_AddsUpToTwoNumber_WhenStringIsValid() throws Exception {
        Calculator calc = new Calculator();
        assertEquals(calc.Add(""), 0);
        assertEquals(calc.Add("1"), 1);
        assertEquals(calc.Add("1,2"), 3);
    }

    @Test
    void Add_AddsUpToAnyNumber_WhenStringIsValid() throws Exception {
        Calculator calc = new Calculator();
        assertEquals(calc.Add("1,2,3"), 6);
        assertEquals(calc.Add("10,90,10,20"), 130);
    }

    @Test
    void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid() throws Exception {
        Calculator calc = new Calculator();
        assertEquals(calc.Add("1\n2,3"), 6);
        assertEquals(calc.Add("10\n90,10\n20"), 130);
    }

    @Test
    void Add_AddsNumbersUsingCustomDelimiter_WhenStringIsValid() throws Exception {
        Calculator calc = new Calculator();
        assertEquals(calc.Add("//;\n1;2"), 3);
        assertEquals(calc.Add("//;\n1;2;4"), 7);
    }

    @Test
    void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsed() throws Exception {
        Calculator calc = new Calculator();
        try {
            calc.Add("1,2,-1");
            fail("No exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Negatives not allowed: -1");
        }
        try {
            calc.Add("//;\n1;-2;-4");
            fail("No exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Negatives not allowed: -2,-4");
        }
    }
}