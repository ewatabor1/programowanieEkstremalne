import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    public void addUpToTwoNumbersWhenNumbersEmptyGiven(){
        String numbers="";
        Calculator calculator = new Calculator();

        assertEquals(calculator.add(numbers), 0);
    }

    @Test
    public void addUpToTwoNumbersWhenOneNumberGiven(){
        String numbers="1";
        Calculator calculator = new Calculator();

        assertEquals(calculator.add(numbers), 1);
    }

    @Test
    public void addUpToTwoNumbersWhenTwoNumbersGiven(){
        String numbers="1,2";
        Calculator calculator = new Calculator();

        assertEquals(calculator.add(numbers), 3);
    }

    @Test
    public void addAnyAmountOfNumbersMoreThanTwo(){
        String numbers="10,25,37";
        Calculator calculator = new Calculator();

        assertEquals(calculator.add(numbers), 72);
    }

    @Test
    public void addAnyAmountOfNumbersWithNewLine(){
        String numbers="10,25\n37";
        Calculator calculator = new Calculator();

        assertEquals(calculator.add(numbers), 72);
    }

    @Test
    public void addAnyAmountOfNumbersWithDifferentDelimiter(){
        String numbers = "//;\n1;2";
        Calculator calculator = new Calculator();

        assertEquals(calculator.add(numbers), 3);
    }

    @Test
    public void addAnyAmountOfNumbersThrowsExceptionWhenGivenNegativeNumber(){
        String numbers="-1,2,3";
        Calculator calculator = new Calculator();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add(numbers);
        });
        assertEquals(exception.getMessage(), "negatives not allowed " + numbers);
    }
}
