package exceptions;

import java.util.ArrayList;

public class NegativeException extends Exception
{
    private String negativeNumbers ="";

    public NegativeException(String negativeNumbers)
    {
        this.negativeNumbers = negativeNumbers;
    }

    public NegativeException(ArrayList<String> negativeNumbers)
    {
        for(String i : negativeNumbers)
            if(Integer.parseInt(i)<0)
                this.negativeNumbers+= i + " ";
    }

    public String getNegativeNumbers() {
        return negativeNumbers;
    }

    @Override
    public String getMessage() {
        return "negatives not allowed: "+ getNegativeNumbers();
    }
}
