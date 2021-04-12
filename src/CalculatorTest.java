import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void noOfBits1_SingleNumber() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 1);
            put("3", 2);
            put("7", 3);
            put("127", 7);
            put("255", 8);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }

    @Test
    void noOfBits1_Invalid_out_of_bounds() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("-1", 0);
            put("256", 0);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Calculator().noOfBits1(entry.getKey());
            });
        }
    }

    @Test
    void noOfBits1_CommaDelimiter() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("2,2", 2);
            put("7,7", 6);
            put("255,255", 16);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }

    @Test
    void noOfBits1_OtherDelimiters() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1,1", 2);
            put("1 1", 2);
            put("1;1", 2);
            put("1\n1", 2);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }

    @Test
    void noOfBits1_InvalidDelimiters() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1a1", 0);
            put("1|1", 0);
            put("1[1", 0);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Calculator().noOfBits1(entry.getKey());
            });
        }
    }

    @Test
    void noOfBits1_MultipleNumbers_w_Hexadecimal() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1,$1", 2);
            put("2,$1", 2);
            put("$ff 255", 16);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }
}