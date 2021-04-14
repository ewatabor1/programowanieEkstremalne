public class NumberOfBits {

    public int noOfBits1(String numbers){
        if (numbers.isEmpty()) return 0;

        String[] numbersArray = numbers.split("\\s+|;");
        int count=0;
         StringBuilder illegalCharacters= new StringBuilder();
        StringBuilder numbersOutOfRange= new StringBuilder();
        for (String num : numbersArray){
            int nDec=0;
            if (num.startsWith("$")) {
                num=num.substring(1);
                if (!num.matches("-?[0-9a-fA-F]+"))
                    illegalCharacters.append(num);
                else
                    nDec = Integer.parseInt(num, 16);
            }
            else{
                if (!num.matches("[0-9]+"))
                    illegalCharacters.append(num);
                else
                    nDec = Integer.parseInt(num);
            }
            if (nDec<0 || nDec>255)
                numbersOutOfRange.append(num);
            count += (int) Integer.toBinaryString(nDec).chars().filter(ch -> ch == '1').count();
        }

        if(!illegalCharacters.toString().isEmpty())
            throw new IllegalArgumentException("Illegal characters given! " + illegalCharacters.toString());
        if(!numbersOutOfRange.toString().isEmpty())
            throw new IllegalArgumentException("Numbers out of range! " + numbersOutOfRange.toString());
        return count;
    }

}
