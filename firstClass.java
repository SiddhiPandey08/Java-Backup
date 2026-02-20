import java.util.Scanner;

public class firstClass {
    public static int sum(int num) {
        int sum=0;
        while (num>0) {
          int  dig = num%10;
           sum = sum +dig;
            num = num/10;
           
        }
        return sum;
    }


    public static int decTobin(int decNum) {
        int pow = 0;
        int biNum = 0;
        while (decNum > 0) {
            int rem = decNum % 2;
            biNum = biNum + rem * (int) Math.pow(10, pow);
            pow++;
            decNum = decNum / 2;
        }
        return biNum;
        
    }

    public static void main(String[] args) {
        System.out.println("Enter your DecNum:");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println("num:" + sum(num));
        sc.close();
    }
}
