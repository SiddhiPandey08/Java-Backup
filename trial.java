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

    public static void main(String[] args) {
        String[] words = { "apple", "app", "apes", };
        System.out.println(longestCommonPrefix(words));

    }
}
