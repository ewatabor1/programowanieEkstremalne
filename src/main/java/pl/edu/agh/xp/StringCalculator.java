package pl.edu.agh.xp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String numbersString) {
        String delimitersRegex = ",|\n";

        if(numbersString.startsWith("//")) {
            String[] splitted = numbersString.split("\n", 2);
            String delimiter = splitted[0].replace("//", "");
            delimitersRegex += "|" + delimiter;
            numbersString = splitted[1];
        }

        List<Integer> numbers = Arrays
                .stream(numbersString.split(delimitersRegex))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> negativeNumbers = numbers
                .stream()
                .filter(number -> number < 0)
                .collect(Collectors.toList());

        if(!negativeNumbers.isEmpty()) {
            String negativeNumbersString = negativeNumbers
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            throw new NegativeNumbersOnInputException("Negative numbers on input: " + negativeNumbersString);
        }

        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public int noOfBitsIn1(String numbersAsString) {
        String delimitersRegex = ";| |\n|\t";

        if(numbersAsString.isEmpty())
            return 0;
        List<String> stringNumbers;

        try {
            stringNumbers = Arrays
                    .stream(numbersAsString.split(delimitersRegex))
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            throw new IncorrectlySeparatedArgumentException();
        }

        List<Integer> numbers = stringNumbers
                .stream()
                .map(stringNumber -> stringNumber.startsWith("$") ? Integer.parseInt(stringNumber.replace("$", ""), 16) : Integer.parseInt(stringNumber))
                .collect(Collectors.toList());

        if (numbers.stream().anyMatch(number -> number < 0 || number > 256))
            throw new NumberOutOfBoundException("Provided number is out of expected bounds [0 - 256]");

        return numbers.stream().map(Integer::bitCount).mapToInt(Integer::intValue).sum();
    }
}
