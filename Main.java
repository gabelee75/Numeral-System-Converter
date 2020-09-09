package converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner read = new Scanner(System.in);
            int sourceRadix = read.nextInt();
            read.nextLine();
            String num = read.nextLine();
            int tarRadix = read.nextInt();
            String nums[] = num.split("\\.");
            String intNum = nums[0];
            String fraction = "";
            if(sourceRadix <= 0 || tarRadix <= 0 || sourceRadix > 36 || tarRadix > 36) {
                throw new Exception("An error has occurred");
            }
            if (sourceRadix == 1) {
                intNum = Integer.toString(nums[0].length(), tarRadix);
            }
            if (tarRadix == 1) {
                intNum = "";
                for (int i = 0; i < Integer.parseInt(nums[0], sourceRadix); i++) {
                    intNum += "1";
                }
            }
            if (sourceRadix != 1 && tarRadix != 1) {
                intNum = Integer.toString(Integer.parseInt(nums[0], sourceRadix), tarRadix);
            }
            if (sourceRadix != 10 && nums.length > 1) {
                nums[1] = convertFracToBase10(nums[1], sourceRadix);
                fraction = convertFrac10ToBase(nums[1], tarRadix);
            } else if (nums.length > 1) {
                nums[1] = "0." + nums[1];
                fraction = convertFrac10ToBase(nums[1], tarRadix);
            }
            System.out.println(intNum + "." + fraction);
        }
        catch(Exception e){
            System.out.println("An error has occurred");
        }
    }
    public static String convertFracToBase10(String num, int sourceRadix){
       double result = 0;
        for(int i = 0;i < num.length();i++){
            int j = Integer.parseInt(Character.toString(num.charAt(i)), sourceRadix);
            result += (j/Math.pow(sourceRadix,i+1));
        }
        return Double.toString(result);
    }


    public static String convertFrac10ToBase(String num, int tarRadix){
        String result = "";
        double d = Double.parseDouble(num.substring(0,Math.min(num.length(), 10)));
        for(int i = 0; i < 5; i++) {
            d = d * tarRadix;
            String remainder[] = Double.toString(d).split("\\.");
            result += Integer.toString(Integer.parseInt(remainder[0]),tarRadix);
            d = d - Integer.parseInt(remainder[0]);

        }
        return result;
    }
}
