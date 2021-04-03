import exceptions.NegativeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {
    @Test
    public void TestAdd2Numbers()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add("2,3"),5);
            assertEquals(calculator.add("3,4"),7);
        } catch (NegativeException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestAdd1Numbers()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add("2"),2);
        } catch (NegativeException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestAdd0Numbers()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add(""),0);
        } catch (NegativeException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestAddNNumbers()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add("3,4,5"),12);
            assertEquals(calculator.add("3,4,5,1,1"),14);
            assertEquals(calculator.add("1,1,1,2,2,2,3,3,3"),18);
        } catch (NegativeException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestAddNNumbersWithNewLines()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add("3\n4,5"),12);
            assertEquals(calculator.add("3,4\n5,1\n1"),14);
            assertEquals(calculator.add("1,1,1\n2,2,2\n3\n3,3"),18);
        } catch (NegativeException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestAddNNumbersWithNewLinesAndCustom()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add("//as\n1as2"),3);
        } catch (NegativeException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestAddNNumbersWithNewLinesAndCustom1()
    {
        Calculator calculator= new Calculator();
        try {
            assertEquals(calculator.add("//as\n1as2"),3);
        } catch (NegativeException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestNegativeException()
    {
        Calculator calculator = new Calculator();
        Throwable ex = assertThrows(NegativeException.class,()->{
            calculator.add("-1,4,7,-3,-7,6,4");
        });
        assertEquals("negatives not allowed: -1 -3 -7 ",ex.getMessage());
    }
}
