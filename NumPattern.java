public class NumPattern {
    public static void main(String[] args) {
        int n = 7;
        // int num = 1;
        for (int line = 1; line <= n; line++) {
            for (int j = n - line; j >= 1; j--) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 2*line-1; j++) {
                // num++;
                if (line==1||line==n||j==1||j==(2*line)-1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

  
}
