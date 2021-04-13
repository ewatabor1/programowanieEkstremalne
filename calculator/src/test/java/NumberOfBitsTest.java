import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberOfBitsTest {

    @Test
    public void returnCorrectNumberWhenOneNumberGiven(){
        String number="222";
        NumberOfBits numberOfBits = new NumberOfBits();

        assertEquals(numberOfBits.noOfBits1(number), 6);
    }

    @Test
    public void throwExceptionForNumberOutOfRange(){
        String number="300";
        NumberOfBits numberOfBits = new NumberOfBits();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            numberOfBits.noOfBits1(number);
        });
        assertEquals(exception.getMessage(), "Number out of range!");
    }

    @Test
    public void returnCorrectNumberWhenThreeNumbersGivenDividedBySemicolon(){
        String number="222;10;35";
        NumberOfBits numberOfBits = new NumberOfBits();

        assertEquals(numberOfBits.noOfBits1(number), 11);
    }

    @Test
    public void returnCorrectNumberWhenThreeNumbersGivenDividedBySpace(){
        String number="222 10 35";
        NumberOfBits numberOfBits = new NumberOfBits();

        assertEquals(numberOfBits.noOfBits1(number), 11);
    }

    @Test
    public void returnCorrectNumberWhenThreeNumbersGivenDivided(){
        String number="222\t10 35\n1;1";
        NumberOfBits numberOfBits = new NumberOfBits();

        assertEquals(numberOfBits.noOfBits1(number), 13);
    }

    @Test
    public void throwExceptionForNumberWithIllegalCharacter(){
        String number="222\t10 35\n1a\\bb\\c";
        NumberOfBits numberOfBits = new NumberOfBits();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            numberOfBits.noOfBits1(number);
        });
        assertEquals(exception.getMessage(), "Illegal character given!");
    }

    @Test
    public void returnCorrectNumberWhenHexNumbersGiven(){
        String number="1;$ff\n$a1";
        NumberOfBits numberOfBits = new NumberOfBits();

        assertEquals(numberOfBits.noOfBits1(number), 12);
    }

}
