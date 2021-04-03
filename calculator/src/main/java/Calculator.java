import exceptions.NegativeException;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator{

    public Calculator(){}
    public int add(String numbers) throws NegativeException {
        String delimiter = "[,\\n]";

        ArrayList<String> list = new ArrayList<String>();
        String[] nums = numbers.split("\n");

        if(nums[0].startsWith("//"))
        {
            Collections.addAll(list,nums);
            delimiter = nums[0].substring(2);
            list.remove(0);
            String[] numbersFromArray = list.toString().substring(1,list.toString().length()-1).split(delimiter);
            list.clear();
            Collections.addAll(list,numbersFromArray);
        }
        else
        {
            nums = numbers.split(delimiter);
            Collections.addAll(list,nums);
        }

        int sum =0;
        if(numbers.length()>0) {
            for (String i : list) {
                if(Integer.parseInt(i)>1000)
                    continue;
                if(Integer.parseInt(i)<0)
                    throw new NegativeException(list);
                sum += Integer.parseInt(i);
            }
        }
        return sum;
    }

}
