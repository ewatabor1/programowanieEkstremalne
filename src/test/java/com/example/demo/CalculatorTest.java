package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CalculatorTest {
    @Test
    void add_AddsUpToTwoNumbers_WhenStringIsValid() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("", 0);
            put("1", 1);
            put("1,2", 3);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().add(entry.getKey());
            assertEquals(entry.getValue(), result);
        }

    }

    @Test
    void add_AddsUpToAnyNumbers_WhenStringIsValid() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1,2,3", 6);
            put("10,90,10,20", 130);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().add(entry.getKey());
            assertEquals(entry.getValue(), result);
        }

    }

    @Test
    void add_AddsNumbersUsingNewlineDelimiter_WhenStringIsValid() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1\n2,3", 6);
            put("10\n90,10\n20", 130);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().add(entry.getKey());
            assertEquals(entry.getValue(), result);
        }

    }

    @Test
    void add_AddsNegativeNumbers_WhenStringIsValid() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1\n2,-3", 0);
            put("10\n-90,10\n20", -50);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().add(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }

    @Test
    void add_AddsNegativeNumbers_StringInvalid() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("--1\n2,-3", 0);
            put("10-\n-90,10\n20", -50);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                int result = new Calculator().add(entry.getKey());
            });
        }
    }

    @Test
    void noOfBits1_Valid() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1", 1);
            put("255", 8);
            put("127", 7);
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
            put("1255", 0);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Calculator().noOfBits1(entry.getKey());
            });
        }
    }
    @Test
    void noOfBits1_MultipleNumbers_CommaDelimiter() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1,1", 2);
            put("255,255", 16);
            put("127,127", 14);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }
    @Test
    void noOfBits1_MultipleNumbers_OtherDelimiters() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1,1", 2);
            put("255 255", 16);
            put("127;127", 14);
            put("127    \t127", 14);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }
    @Test
    void noOfBits1_MultipleNumbers_InvalidDelimiters() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1k1", 0);
            put("255|255", 0);
            put("127[127", 0);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Calculator().noOfBits1(entry.getKey());
            });
        }
    }
    @Test
    void noOfBits1_MultipleNumbers_Hexadecimal() {
        Map<String, Integer> tests = new HashMap<String, Integer>() {{
            put("1,$1", 2);
            put("$ff 255", 16);
        }};

        for (Map.Entry<String, Integer> entry : tests.entrySet()) {
            int result = new Calculator().noOfBits1(entry.getKey());
            assertEquals(entry.getValue(), result);
        }
    }
}
