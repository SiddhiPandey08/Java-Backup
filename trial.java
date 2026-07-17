import java.util.*;

public class trial {
    public static String longestCommonPrefix(String[] v) {
        if (v == null || v.length == 0)
            return "";

        StringBuilder ans = new StringBuilder();
        Arrays.sort(v);
        String first = v[0];
        String last = v[v.length - 1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }

    public static void RevArr(int arr[]) {
        int n = arr.length % 2 == 0 ? arr.length / 2 : (arr.length / 2) + 1;
        int temp = 0;
        for (int i = 0; i <= n; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

    }

    public static void main(String[] args) {
        String[] words = { "apple", "app", "apes", };
        System.out.println(longestCommonPrefix(words));
        int arr[] = { 1, 2, 3, 4 };
        RevArr(arr);
        for (int num : arr) {
            System.out.print(num);
        }
    }
}
