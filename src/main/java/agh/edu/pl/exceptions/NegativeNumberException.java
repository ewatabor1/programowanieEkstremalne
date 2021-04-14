package agh.edu.pl.exceptions;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(int[] nums) {
        super("Encountered negative numbers: " + nums);
    }
}
