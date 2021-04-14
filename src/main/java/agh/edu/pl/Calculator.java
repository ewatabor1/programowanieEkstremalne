package agh.edu.pl;

import agh.edu.pl.exceptions.NegativeNumberException;

import java.util.Arrays;

public class Calculator {
    int add(String numbers) {
        String[] strNums = numbers.split("\n|,");
        if(strNums.length == 0) return 0;

        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; ++i) {
            try {
                nums[i] = Integer.parseInt(strNums[i]);
            } catch (NumberFormatException e) {
                nums[i] = 0;
            }
        }

        int[] negative = Arrays.stream(nums).filter( x -> x < 0).toArray();
        if(negative.length != 0) throw new NegativeNumberException(negative);

        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        return sum;
    }
}
