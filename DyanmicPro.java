import java.util.Arrays;

public class DyanmicPro {
    public static int longestCommonSubstringRecursion(String s1, String s2, int m, int n, int count) { // O(2^n)
        if (m == 0 || n == 0) {
            return count;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            count = longestCommonSubstringRecursion(s1, s2, m - 1, n - 1, count + 1);
        }
        int count1 = longestCommonSubstringRecursion(s1, s2, m - 1, n, 0);
        int count2 = longestCommonSubstringRecursion(s1, s2, m, n - 1, 0);
        return Math.max(count, Math.max(count1, count2));
    }

    public static int longestCommonSubstringMemoization(String s1, String s2, int m, int n, int[][] dp, int count) { // O(m*n)
        int[][] dp3 = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp3[i], -1);
        }
        if (m == 0 || n == 0) {
            return count;
        }
        if (dp3[m][n] != -1) {
            return dp3[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            count = longestCommonSubstringMemoization(s1, s2, m - 1, n - 1, dp3, count + 1);
        }
        int count1 = longestCommonSubstringMemoization(s1, s2, m - 1, n, dp3, 0);
        int count2 = longestCommonSubstringMemoization(s1, s2, m, n - 1, dp3, 0);
        dp3[m][n] = Math.max(count, Math.max(count1, count2));
        return dp3[m][n];
    }

    public static int longestCommonSubstringTab(String s1, String s2) { // longest common substring tabulation O(m*n)
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0; // reset to 0 if characters do not match
                }
            }
        }
        printDP(dp); // print the dp array for visualization
        return maxLength;
    }

    public static int lcsRecursion(String s1, String s2, int m, int n) { // recursive O(2^n)
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcsRecursion(s1, s2, m - 1, n - 1);
        } else {
            return Math.max(lcsRecursion(s1, s2, m - 1, n), lcsRecursion(s1, s2, m, n - 1));
        }
    }

    public static int lcsMemoization(String s1, String s2, int m, int n, int[][] dp) { // memoization O(m*n)
        int[][] dp2 = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp2[i], -1);
        }

        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp2[m][n] != -1) {
            return dp2[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            dp2[m][n] = 1 + lcsMemoization(s1, s2, m - 1, n - 1, dp2);
        } else {
            dp2[m][n] = Math.max(lcsMemoization(s1, s2, m - 1, n, dp2), lcsMemoization(s1, s2, m, n - 1, dp2));
        }
        return dp2[m][n];
    }

    public static int lcsTabulation(String s1, String s2) { // tabulation O(m*n)
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // base case
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        printDP(dp); // print the dp array for visualization
        return dp[m][n];
    }

    public static int rodCutting(int[] prices, int len, int[] lengths) { // exactly same to unbounded knapsack O(n*len)
        int n = prices.length;
        int[][] dp = new int[n + 1][len + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= len; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // base case
                } else if (lengths[i - 1] <= j) {
                    dp[i][j] = Math.max(prices[i - 1] + dp[i][j - lengths[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printDP(dp); // print the dp array for visualization
        return dp[n][len];
    }

    public static int coinsChange(int[] coins, int amount) { // coin change problem (number of ways)
                                                             // O(n*amount)
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0) {
                    dp[i][j] = 1; // base case: one way to make amount 0
                } else if (i == 0) {
                    dp[i][j] = 0; // no way to make positive amount with 0 coins
                } else if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]; // include or exclude coin
                } else {
                    dp[i][j] = dp[i - 1][j]; // exclude coin
                }
            }
        }
        printDP(dp); // print the dp array for visualization
        return dp[coins.length][amount];
    }

    public static int unboundedKnapsack(int[] weights, int[] values, int capacity, int n) { // unbounded knapsack
        // O(n*capacity)
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // base case
                } else if (weights[i - 1] <= j) {
                    // note dp[i][j - weights[i - 1]] instead of dp[i-1][j - weights[i - 1]],rest
                    // all same as 0-1 knapsack
                    dp[i][j] = Math.max(values[i - 1] + dp[i][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printDP(dp); // print the dp array for visualization
        return dp[n][capacity];
    }

    public static boolean targetSum(int target, int[] arr) {// tabulation O(n*target)
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        // i=0 and j= 1 to n ke liye false default set hoga java me 2d boolean array bante waqt
        for (int i = 1; i <= n; i++) {
            for (int sum = 1; sum <= target; sum++) {
                if (arr[i - 1] <= sum && dp[i - 1][sum - arr[i - 1]] == true)
                    dp[i][sum] = true;
                else if (dp[i - 1][sum] == true)
                    dp[i][sum] = true;
                else
                    dp[i][sum] = dp[i - 1][sum];
            }
        }
        printDP(dp);
        return dp[n][target];
    }

    public static int Knapsack01Tab(int[] weights, int[] values, int capacity, int n) { // 0-1 knapsack problem
                                                                                        // (tabulation)
                                                                                        // O(n*capacity)
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // base case
                } else if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printDP(dp); // print the dp array for visualization
        return dp[n][capacity];
    }

    public static void printDP(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printDP(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int Knapsack01Memo(int[] weights, int[] values, int capacity, int n) {// 0-1 knapsack problem
                                                                                        // (memoization) O(n*capacity)
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = -1;
            }
        }
        if (n == 0 || capacity == 0) {
            return 0;
        }
        if (dp[n][capacity] != -1) {
            return dp[n][capacity];
        }
        if (weights[n - 1] <= capacity) {
            // two choices: include or exclude
            int ans1 = values[n - 1] + Knapsack01Memo(weights, values, capacity - weights[n - 1], n - 1);
            int ans2 = Knapsack01Memo(weights, values, capacity, n - 1);
            dp[n][capacity] = Math.max(ans1, ans2);// memoization step(updating dp array)
            return dp[n][capacity];
        } else {
            dp[n][capacity] = Knapsack01Memo(weights, values, capacity, n - 1);// memoization step(updating dp array)
            return dp[n][capacity];
        }
    }

    public static int Knapsack01(int[] weights, int[] values, int capacity, int n) { // 0-1 knapsack problem (recursive)
                                                                                     // O(2^n)
        if (n == 0 || capacity == 0) {
            return 0;
        }
        if (weights[n - 1] <= capacity) {
            // two choices: include or exclude
            int ans1 = values[n - 1] + Knapsack01(weights, values, capacity - weights[n - 1], n - 1);
            int ans2 = Knapsack01(weights, values, capacity, n - 1);
            return Math.max(ans1, ans2);
        } else {
            return Knapsack01(weights, values, capacity, n - 1);
        }
    }

    public static int countWays(int n, int[] ways) { // dp ref: fibonacci series(memoization)O(n) (you can take only 1 or 2 steps at a time)
        // if 1 or 2 or 3 steps then ways[n] = countWays(n - 1, ways) + countWays(n - 2, ways) + countWays(n - 3, ways)
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (ways[n] != 0) {
            return ways[n];
        }
        ways[n] = countWays(n - 1, ways) + countWays(n - 2, ways);
        return ways[n];
    }

    public static void countWaysTab(int n) { // tabulation method O(n)
        int[] ways = new int[n + 1];
        ways[0] = 1; // base case
        for (int i = 1; i <= n; i++) {
            ways[i] = ways[i - 1];
            if (i >= 2) {
                ways[i] += ways[i - 2];
            }
        }
        System.out.println("Number of ways to reach " + n + ": " + ways[n]);
    }

    public static void main(String[] args) {
        System.out.println("Length of Longest Common Substring (Recursion): "
                + longestCommonSubstringRecursion("aabb", "acbe", 4, 4, 0));
        System.out.println("Length of Longest Common Substring (Memoization): "
                + longestCommonSubstringMemoization("aabb", "acbe", 4, 4, new int[5][5], 0));
        System.out.println(
                "Length of Longest Common Substring (Tabulation): " + longestCommonSubstringTab("aabb", "acbe"));
        System.out.println("Length of LCS (Recursion): " + lcsRecursion("aabb", "acbe", 4, 4));
        System.out.println("Length of LCS (Memoization): " + lcsMemoization("aabb", "acbe", 4, 4, new int[5][5]));
        System.out.println("Length of LCS (Tabulation): " + lcsTabulation("aabb", "acbe"));
        int lengths[] = { 1, 2, 3, 4 };
        int prices[] = { 2, 5, 7, 8 };
        System.out.println("Maximum profit for rod cutting: " + rodCutting(prices, 4, lengths));
        int arr[] = { 2, 3, 7, 8, 10 };
        int[] coins = { 1, 2, 3 };
        int amount = 4;
        System.out.println("Number of ways to make amount " + amount + ": " + coinsChange(coins, amount));
        System.out.println("Is target sum possible: " + targetSum(11, arr));
        int[] weights = { 1, 2, 3 };
        int[] values = { 10, 15, 40 };
        int capacity = 6;
        int n = weights.length;
        int maxValue = Knapsack01(weights, values, capacity, n);
        System.out.println("Maximum value in Knapsack = " + maxValue);
        System.out.println("Maximum value in Knapsack (Memoization) = " + Knapsack01Memo(weights, values, capacity, n));
        System.out.println("Maximum value in Knapsack (Tabulation) = " + Knapsack01Tab(weights, values, capacity, n));
        // int n1 = 5; // Example input
        // int[] ways = new int[n1 + 1];
        // int result = countWays(n1, ways);
        // System.out.println("Number of ways to reach " + n1 + ": " + result);
    }
}
