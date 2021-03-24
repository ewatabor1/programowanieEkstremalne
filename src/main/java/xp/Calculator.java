package xp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public int add(String text){
        if(text == null ||text.isEmpty())
        return 0;
        String[] split = text.split("\n");
        List<String> items = new ArrayList<>();
        for (String s : split) {
            String[] split2 = s.split(",");
            items.addAll(Arrays.asList(split2));
        }
        return items.stream().map(Integer::parseInt).reduce(0, Integer::sum);
    }
}
