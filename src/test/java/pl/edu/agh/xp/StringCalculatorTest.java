package pl.edu.agh.xp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    @Test
    public void Add_AddsUpToTwoNumbers() {
        final String toAdd = "1,2";
        final int expected = 3;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add(toAdd);

        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsAnyCountOfNumbers() {
        final String toAdd = "10,20,30";
        final int expected = 60;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add(toAdd);

        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsAnyCountOfNumbersSeparatedByNewLine() {
        final String toAdd = "10,20\n30,40\n50";
        final int expected = 150;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add(toAdd);

        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsAnyCountOfNumbersSeparatedByCustomDelimiter() {
        final String toAdd = "//;\n1;2";
        final int expected = 3;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add(toAdd);

        assertEquals(expected, result);
    }

    @Test
    public void Add_ThrowsExceptionOnNegativeNumbers() {
        final String toAdd = "1,2,-3";
        final int expected = 0;
        StringCalculator stringCalculator = new StringCalculator();

        NegativeNumbersOnInputException exception = assertThrows(NegativeNumbersOnInputException.class, () -> stringCalculator.add(toAdd));

        assertEquals(exception.getMessage(), "Negative numbers on input: -3");
    }

    @Test
    public void NoOfBitsIn1_ReturnsZeroForEmptyString() {
        final String inputNumber = "";
        final int expected = 0;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.noOfBitsIn1(inputNumber);

        assertEquals(expected, result);
    }


    @Test
    public void NoOfBitsIn1_ReturnsCorrectNumberOfBits() {
        final String inputNumber = "7";
        final int expected = 3;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.noOfBitsIn1(inputNumber);

        assertEquals(expected, result);
    }

    @Test
    public void NoOfBitsIn1_ThrowsExceptionOnNumberOutOfBounds() {
        final String inputNumber = "512";
        final int expected = 1;

        StringCalculator stringCalculator = new StringCalculator();

        NumberOutOfBoundException exception = assertThrows(NumberOutOfBoundException.class, () -> stringCalculator.noOfBitsIn1(inputNumber));

        assertEquals(exception.getMessage(), "Provided number is out of expected bounds [0 - 256]");
    }

    @Test
    public void NoOfBitsIn1_ReturnsCorrectNumberOfBitsForListOfStringsSeparatedBySemicolon() {
        final String inputNumber = "3;7";
        final int expected = 5;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.noOfBitsIn1(inputNumber);

        assertEquals(expected, result);
    }

    @Test
    public void NoOfBitsIn1_ReturnsCorrectNumberOfBitsForListOfStringsSeparatedBySemicolonOrSpace() {
        final String inputNumber = "3;7 1";
        final int expected = 6;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.noOfBitsIn1(inputNumber);

        assertEquals(expected, result);
    }

    @Test
    public void NoOfBitsIn1_ReturnsCorrectNumberOfBitsForListOfStringsSeparatedBySemicolonOrAnyWhitespace() {
        final String inputNumber = "3;7 1\n3\t3";
        final int expected = 10;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.noOfBitsIn1(inputNumber);

        assertEquals(expected, result);
    }

    @Test
    public void NoOfBitsIn1_ThrowsExceptionForIncorrectlySeparatedListOfNumbers() {
        final String inputNumber = "3;7 1\n3\t3,1";

        StringCalculator stringCalculator = new StringCalculator();

        assertThrows(IncorrectlySeparatedArgumentException.class, () -> stringCalculator.noOfBitsIn1(inputNumber));
    }

    @Test
    public void NoOfBitsIn1_AcceptsDecimalAndHexadecimalListOfNumbers() {
        final String inputNumber = "10;$a4";
        final int expected = 5;

        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.noOfBitsIn1(inputNumber);

        assertEquals(expected, result);
    }
}
