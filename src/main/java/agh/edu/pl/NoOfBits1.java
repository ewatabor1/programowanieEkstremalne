package agh.edu.pl;

public class NoOfBits1 {
    public static void main(String[] args) {

    }
    int noOfBits1Single(String number) throws Exception {
        if (number.isEmpty()) {
            return 0;
        } else {
            int n;
            if(number.startsWith("$")) {
                String hex = number.replace("$", "0x");
                n = Integer.decode(hex);
            } else {
                n = Integer.parseInt(number);
            }
            if (n < 0 || n > 255)
                throw new Exception("Please provide number in range 0-255");
            int count = 0;
            while (n > 0) {
                count += n & 1;
                n >>= 1;
            }
            return count;
        }
    }

    int noOfBits1(String numbers) throws Exception {
        String[] nums = numbers.split("\\s+|;");
        int sum = 0;
//        return Arrays.stream(nums).map( x -> {
//            try {
//                return noOfBits1(x);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return 0;
//        }).reduce(0, Integer::sum);
        for (String num: nums) {
            int noOfBits1InSingle = noOfBits1Single(num);
            sum += noOfBits1InSingle;
        }
        return sum;
    }

}
