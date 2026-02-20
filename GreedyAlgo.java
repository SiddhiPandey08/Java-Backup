import java.util.*;

public class GreedyAlgo {
    static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void JobSequence() {
        int jobsInfo[][] = { { 4, 20 }, { 1, 10 }, { 3, 40 }, { 3, 30 } }; // {deadline,profit}

        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < jobsInfo.length; i++) {
            jobs.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1]));
        }
        Collections.sort(jobs, (a, b) -> b.profit - a.profit); // sorting in descending order of profit
        ArrayList<Integer> result = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < jobs.size(); i++) {
            if (time < jobs.get(i).deadline) {
                result.add(jobs.get(i).id);
                time++;
            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println("Number of jobs done: " + result.size());
    }

    public static void IndianCoinChange() { // O(n)
        Integer coins[] = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 };
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.sort(coins, Comparator.reverseOrder());
        int amount = 590;
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (amount == 0) {
                break;
            }
            while (coins[i] <= amount) {
                amount -= coins[i];
                ans.add(coins[i]);
                count++;
            }
        }
        System.out.println("Minimum number of coins: " + count);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
    }

    public static void MinimumSumAbsoluteDiff() { // Leetcode 1878 O(nlogn)
        int A[] = { 1, 2, 3 };
        int B[] = { 2, 1, 3 };
        Arrays.sort(A);
        Arrays.sort(B);
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += Math.abs(A[i] - B[i]);
        }
        System.out.println("Minimum sum of absolute difference: " + sum);
    }

    public static void FractionalKnapsack(int values[], int weights[], int w) {
        double ratio[][] = new double[values.length][2]; // 0-> index , 1-> value/weight
        for (int i = 0; i < values.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = values[i] / (double) weights[i];
        }
        // sorting based on value/weight ratio in ascending order
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));
        int capacity = w;
        double finalValue = 0.0;
        for (int i = ratio.length - 1; i >= 0; i--) {
            int idx = (int) ratio[i][0];
            if (weights[idx] <= capacity) {
                finalValue += values[idx];
                capacity -= weights[idx];
            } else {
                finalValue += ratio[i][1] * capacity;
                capacity = 0;
                break;
            }
        }
        System.out.println("Maximum value in Knapsack = " + finalValue);
    }

    public static void ActivitySelection() {
        int start[] = { 1, 3, 0, 5, 8, 5 };
        int end[] = { 2, 4, 6, 7, 9, 9 }; // end time here is already sorted

        // if start and end time are not sorted we have to sort them based on end time
        int activities[][] = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            activities[i][0] = i; // activity number
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }
        // lambda function to sort based on end time
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));// sorting based on end time(col 2)
        int maxAct = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        maxAct = 1;
        ans.add(activities[0][0]); // first activity always selected
        int lastEnd = activities[0][2];
        for (int i = 1; i < end.length; i++) {
            if (activities[i][1] >= lastEnd) {
                maxAct++;
                ans.add(activities[i][0]);
                lastEnd = activities[i][2];
            }
        }
        System.out.println("Maximum number of activities: " + maxAct);
        System.out.println("Activities selected at indices: " + ans);

    }

    public static void main(String[] args) {
        int values[] = { 60, 100, 120 };
        int weights[] = { 10, 20, 30 };
        int w = 50;
        // IndianCoinChange();
        // MinimumSumAbsoluteDiff();
        FractionalKnapsack(values, weights, w);
        // ActivitySelection();
        // JobSequence();
    }
}
