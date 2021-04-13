public class NumberOfBits {

    public int noOfBits1(String numbers){
        if (numbers.isEmpty()) return 0;

        String[] numbersArray = numbers.split("\\s+|;");
        int count=0;
        for (String num : numbersArray){
            int nDec=0;
            if (num.startsWith("$")) {
                num=num.substring(1);
                if (!num.matches("-?[0-9a-fA-F]+"))
                    throw new IllegalArgumentException("Illegal character given!");
                nDec = Integer.parseInt(num, 16);
            }
            else{
                if (!num.matches("[0-9]+"))
                    throw new IllegalArgumentException("Illegal character given!");
                nDec = Integer.parseInt(num);
            }
            if (nDec<0 || nDec>255)
                throw new IllegalArgumentException("Number out of range!");
            count += (int) Integer.toBinaryString(nDec).chars().filter(ch -> ch == '1').count();
        }

        return count;
    }

}
