package test;

import kalkulator.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void noOfBits1_AcceptSingleNumber() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1", 1);
            put("127", 7);
            put("255", 8);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }

    @Test
    void noOfBits1_OutOfBounds() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("-1", 0);
            put("260", 0);
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
            put("3,3", 4);
            put("8,8", 6);
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
            put("2 2", 4);
            put("2;2", 4);
            put("2,2", 4);
            put("2\n2", 4);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }

    @Test
    void noOfBits1_InvalidDelimiters() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("2a2", 0);
            put("2|2", 0);
            put("2[2", 0);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Calculator().noOfBits1(entry.getKey());
            });
        }
    }

    @Test
    void noOfBits1_MultipleNumbers() {
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
