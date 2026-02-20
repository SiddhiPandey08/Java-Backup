import java.util.*;

public class Strings {
    public static String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        boolean[] used = new boolean[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        StringBuilder res = new StringBuilder();

        for (char c : s.toCharArray()) {
            freq[c - 'a']--;

            if (used[c - 'a'])
                continue;

            while (res.length() > 0 &&
                    res.charAt(res.length() - 1) > c &&
                    freq[res.charAt(res.length() - 1) - 'a'] > 0) {
                used[res.charAt(res.length() - 1) - 'a'] = false;
                res.deleteCharAt(res.length() - 1);
            }

            res.append(c);
            used[c - 'a'] = true;
        }

        return res.toString();
    }

    public static String compress(String str) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < str.length(); index++) {
            Integer count = 1;
            while (index < str.length() - 1 && str.charAt(index) == str.charAt(index + 1)) {
                count++;
                index++;
            }
            sb.append(str.charAt(index));
            if (count > 1) {
                sb.append(count.toString());
            }
        }
        return sb.toString();
    }

    public static String toUpperCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < str.length(); index++) {
            char currentChar = str.charAt(index);
            if (currentChar >= 'a' && currentChar <= 'z') {
                char upperChar = (char) (currentChar - ('a' - 'A'));
                sb.append(upperChar);
            } else {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }

    public static void removeDuplicates(String str) { // time complexity O(n^2) space O(n)
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < str.length(); index++) {
            char currentChar = str.charAt(index);
            if (sb.indexOf(String.valueOf(currentChar)) == -1) {
                sb.append(currentChar);
            }
        }
        System.out.println(sb.toString());
    }

    public static void removeDuplicates2(String str) { // time complexity O(n) space O(1)
        StringBuilder sb = new StringBuilder();
        boolean[] seen = new boolean[256]; // assuming ASCII character set
        for (int index = 0; index < str.length(); index++) {
            char currentChar = str.charAt(index);
            if (!seen[currentChar]) {
                seen[currentChar] = true;
                sb.append(currentChar);
            }
        }
        System.out.println(sb.toString());
    }

    public static void printLetters(String str) {
        for (int index = 0; index < str.length(); index++) {
            System.out.print(str.charAt(index) + " ");
        }
    }

    public static boolean palindrome(String str) {
        for (int index = 0; index < str.length() / 2; index++) {
            if (str.charAt(index) != str.charAt(str.length() - 1 - index)) {
                return false; // mismatch found
            }
        }
        return true; // no mismatches, it's a palindrome
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
        // // char arr and string are diff
        // char arr[] = { 'a', 'b', 'c' };
        // // types of declaring strings
        // String str = "a b c b";
        // String str2 = new String("abcd");
        // // ***STRINGS ARE IMMUTABLE***

        // // concatenation--> +
        // String name = "Siddhi";
        // removeDuplicates(name);
        // System.out.println(palindrome(name));
    }
}
