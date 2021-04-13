package kalkulator;

public class Calculator {
    public int noOfBits1(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] ItemsArray = numbers.split("\\s+;|,");
        int count = 0;

        for (String number : ItemsArray) {
            int pNumber;
            if (number.startsWith("$")) {
                pNumber = Integer.parseInt(number.substring(1), 16);
            } else {
                pNumber = Integer.parseInt(number);
            }
            if (pNumber < 0 || pNumber > 255) {
                throw new IllegalArgumentException();
            } else {
                count += Integer.bitCount(pNumber);
            }
        }
        return count;
    }
}
