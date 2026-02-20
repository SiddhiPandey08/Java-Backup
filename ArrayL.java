import java.util.*;

public class ArrayL {
    public static int WaterContainer(ArrayList<Integer> height) { // brute force --> O(n^2)
        int maxWater = 0;
        for (int i = 0; i <= height.size() - 1; i++) {
            for (int j = i + 1; j <= height.size() - 1; j++) {
                int h = Math.min(height.get(i), height.get(j));
                int w = j - i;
                int currWater = h * w;
                maxWater = Math.max(maxWater, currWater);
            }
        }
        return maxWater;
    }

    public static int optWaterContainer(ArrayList<Integer> height) { // optimised -->O(n)
        int lp = 0;
        int rp = height.size() - 1;
        int maxWater = 0;
        while (lp < rp) {
            int h = Math.min(height.get(lp), height.get(rp));
            int w = rp - lp;
            int currWater = h * w;
            maxWater = Math.max(maxWater, currWater);
            if (lp < rp) {
                lp++;
            }
            if (rp < lp) {
                rp--;
            }
        }
        return maxWater;
    }

    public static void pairSum(int arr[], int target) {
        int lp = 0;
        int rp = arr.length - 1;
        while (lp < rp) {
            if (arr[lp] + arr[rp] == target) {
                System.out.println("target found at " + "(" + lp + "," + rp + ")");
                return;
            }
            if (arr[lp] + arr[rp] < target) {
                lp++;
            }
            if (arr[lp] + arr[rp] > target) {
                rp--;
            }
        }
        System.out.println("not found");
        return;

    }

    public static void pairSum2(int arr[], int target) { // Rotated and sorted
        int bp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) { // breaking point
                bp = i;
                break;
            }
        }
        int lp = bp + 1; // smallest
        int rp = bp; // largest
        while (lp != rp) { // yaha lp<rp nhi kar skte rotated me aise nhi ghumega pointer
            if (arr[lp] + arr[rp] == target) {
                System.out.println("target found at" + "(" + lp + "," + rp + ")");
                return;
            }
            if (arr[lp] + arr[rp] < target) {
                lp = (lp + 1) % (arr.length);

            }
            if (arr[lp] + arr[rp] > target) {
                rp = (arr.length + rp - 1) % arr.length;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int arr[] = { 11, 12, 13, 7, 8, 9 };

        pairSum2(arr, 20);
        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(7);
        // list.add(5);
        // list.add(4);
        // System.out.println(list);
        // Collections.sort(list);
        // System.out.println(list);
        // ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
        // ArrayList<Integer> list =new ArrayList<>();
        // list.add(1);list.add(2);
        // mainList.add(list);
        // ArrayList<Integer> list2 = new ArrayList<>();
        // list2.add(3);list2.add(4);
        // mainList.add(list2);
        // System.out.println(mainList);
        // ArrayList<Integer> height = new ArrayList<>();
        // height.add(5);
        // height.add(3);
        // height.add(5);
        // height.add(4);
        // height.add(6);
        // height.add(8);
        // height.add(9);
        // System.out.println(optWaterContainer(height));
    }
}
