import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public int add(String numbers){

        String delimiters = ",|\n";

        if (numbers.startsWith("//")){
            numbers = numbers.replace("//", "");
            delimiters = delimiters + "|" + numbers.charAt(0);
        }

        List<Integer> ints = Arrays.stream(numbers.split(delimiters))
                .filter((s) -> s.matches("-?[1-9]\\d*|0"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (Integer integer : ints){
            if (integer<0)
                throw new IllegalArgumentException("negatives not allowed "+numbers);
        }

        return ints.stream().mapToInt(Integer::intValue).sum();
    }
}
