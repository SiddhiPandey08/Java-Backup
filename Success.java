// import java.util.*;

public class Success {
    public static void insertsort(int arr[]) {
        for (int index = 1; index < arr.length ; index++) {
            int curr = arr[index];
            int pre = index - 1;
            while (pre >= 0 && arr[pre] > curr) {
                arr[pre + 1] = arr[pre];
                pre--;
            }
            arr[pre + 1] = curr;
        }
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index]);
        }
    }

    public static void main(String[] args) {// diamond pattern
        // System.out.println("Enter n:");
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // for (int line = 1; line <= n; line++) {
        //     for (int i = 1; i <= n - line; i++) {
        //         System.out.print(" ");
        //     }
        //     for (int j = 1; j <= 2 * line - 1; j++) {
        //         System.out.print("*");
        //     }
        //     System.out.println();
        // }
        // for (int line = n; line >= 1; line--) {
        //     for (int i = 1; i <= n - line; i++) {
        //         System.out.print(" ");
        //     }
        //     for (int j = 1; j <= 2 * line - 1; j++) {
        //         System.out.print("*");
        //     }
        //     System.out.println();   }
        // sc.close();
        int arr[] = {5,4,1,3,2,};
       insertsort(arr);
    }

}
