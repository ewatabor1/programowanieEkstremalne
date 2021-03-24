package agh.edu.pl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoOfBits1Test {
    private NoOfBits1 noOfBits1;

    @BeforeEach
    public void setUp() {
        noOfBits1 = new NoOfBits1();
    }

    @Test
    public void testMultiply() throws Exception {

        assertEquals(0, noOfBits1.noOfBits1(""));
        assertEquals(2, noOfBits1.noOfBits1("192"));
        assertEquals(1, noOfBits1.noOfBits1("1"));
        assertEquals(4, noOfBits1.noOfBits1("195"));

        assertEquals(5, noOfBits1.noOfBits1("195;1"));
        assertEquals(8, noOfBits1.noOfBits1("195 2  1;1\n1"));
        assertEquals(11, noOfBits1.noOfBits1("195 2  1;1\n1;$a4"));

        Assertions.assertThrows(Exception.class, () -> {
            noOfBits1.noOfBits1("300");
        });

        Assertions.assertThrows(Exception.class, () -> {
            noOfBits1.noOfBits1("100,200");
        });
    }

}
