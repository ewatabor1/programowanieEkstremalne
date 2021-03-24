package xp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public int add(String text) {
        if (text == null || text.isEmpty())
            return 0;
        String[] split = text.split("\n");
        List<String> items = new ArrayList<>();
        for (String s : split) {
            String[] split2 = s.split(",");
            items.addAll(Arrays.asList(split2));
        }
        return items.stream().map(Integer::parseInt).reduce(0, Integer::sum);
    }

    public int noOfBits1(String numbers) {
        if (numbers == null || numbers.isEmpty())
            return 0;

        String[] split = numbers.split(";");
        List<String> items = new ArrayList<>();
        for (String s : split) {
            String[] split2 = s.split(",");
            for (String s1 : split2) {
                String[] split3 = s1.split("\\s+");
                items.addAll(Arrays.asList(split3));
            }
        }
        return items.stream().map(i -> {
            int no;
            if (i.startsWith("$"))
                no = Integer.parseInt(i.substring(1), 16);
            else
                no = Integer.parseInt(i);

            if (no < 0 || no > 255)
                throw new IllegalArgumentException();
            int count;
            for (count = 0; no > 0; ++count) {
                no &= no - 1;
            }
            return count;
        }).reduce(0, Integer::sum);
    }
}