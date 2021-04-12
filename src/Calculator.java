import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public int Add(String numbers) throws Exception {
        if (numbers.equals("")) {
            return 0;
        }
        char[] delimiters = new char[]{'\n', ','};
        StringBuilder regexPattern = new StringBuilder("[");
        for (char delimiter : delimiters) {
            regexPattern.append(delimiter);
        }

        if (numbers.charAt(0) == '/') {
            regexPattern.append(numbers.split("[\n]", 2)[0].replace('/', '\u0000'));
            numbers = numbers.split("[\n]", 2)[1];
        }
        regexPattern.append("]");

        int[] splitNumbers = Arrays.stream(numbers.split(regexPattern.toString())).mapToInt(Integer::parseInt).toArray();
        List<String> negativeNumbers = new ArrayList<>();
        for (int num : splitNumbers) {
            if (num < 0) {
                negativeNumbers.add(Integer.toString(num));
            }
        }
        if (negativeNumbers.size() > 0) {
            throw new Exception("Negatives not allowed: " + String.join(",", negativeNumbers));
        }
        return Arrays.stream(splitNumbers).sum();
    }

    public int noOfBits1(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        String[] items = numbers.split(";|,|\\s+");
        int parsedNumber;
        int sum = 0;
        for (String number : items) {
            if (number.startsWith("$")) {
                parsedNumber = Integer.parseInt(number.substring(1), 16);
            } else {
                parsedNumber = Integer.parseInt(number);
            }
            if (parsedNumber < 0 || parsedNumber > 255) {
                throw new IllegalArgumentException();
            } else {
                sum += Integer.bitCount(parsedNumber);
            }
        }
        return sum;
    }
}
