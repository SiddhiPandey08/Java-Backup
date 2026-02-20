public class MyArrays {
    /*
     * 1]java ka inbuilt sorting pack aata hai Arrays.sort(arr) karke array sort ho
     * jayega
     * 2] bubble , insertion, selection sortings ki time complexity O(n^2) hoti hai
     * 3]f collections.reverseorder()
     */
    public static int lastOccurence(int arr[], int key, int i) {
        if (i == arr.length) {
            return -1;
        }
        int result = lastOccurence(arr, key, i + 1);
        if (result != -1) {
            return result;
        }
        if (arr[i] == key) {
            return i;
        }
        return -1;
    }

    public static void CountingSort(int arr[]) {
        int largest = Integer.MIN_VALUE;
        for (int index = 0; index < arr.length; index++) {
            largest = Math.max(largest, arr[index]);
        }
        int count[] = new int[largest + 1];
        for (int index = 0; index < arr.length; index++) {
            count[arr[index]]++;
        } // { 5, 1 ,3 ,4, 1, 3, 2 };
          // System.out.println(Arrays.toString(count)); --> to see how this loop works
          // sorting
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[j] = i;
                j++;
                count[i]--;
            }

        }
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index]);
        }
    }

    public static void SelectionSort(int arr[]) {
        for (int index = 0; index < arr.length - 1; index++) {
            int min = index;
            for (int j = index + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) { // agar > ki jagah < laga denge toh decreasing order me aa jayega
                    min = j;
                }
            }
            int tem = arr[min];
            arr[min] = arr[index];
            arr[index] = tem;
        }
        for (int index = 0; index < arr.length; index++) {
            System.out.println(arr[index]);
        }
    }

    public static void insertionSort(int arr[]) {
        for (int index = 0; index < arr.length; index++) {
            int curr = arr[index];
            int pre = index - 1;
            // finding correct pos for insertion
            while (pre >= 0 && curr > arr[pre]) { // iss condition ko reverse to increasing order me print hoga
                arr[pre + 1] = arr[pre];
                pre--;
            }
            // insertion
            arr[pre + 1] = curr;
        }
        // printing
        for (int index = 0; index < arr.length; index++) {
            System.out.println(arr[index]);
        }
    }

    public static void bubbleSort(int arr[]) {
        // optimised --> if given arr is already sorted(best case) then loops will not
        // run and time complexity can be reduced to O(n)
        for (int turn = 0; turn < arr.length - 1; turn++) {
            int swap = 0;
            for (int index = 0; index < arr.length - 1 - turn; index++) {
                if (arr[index] > arr[index + 1]) {

                    int temp = arr[index];
                    arr[index] = arr[index + 1];
                    arr[index + 1] = temp;
                    swap++;
                }
            }
            if (swap == 0) {
                System.out.println("already sorted");
                break;
            }
        }
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index] + " ");

        }

    }

    public static int TrappedRain(int height[]) {
        int n = height.length;
        int leftmax[] = new int[n];
        leftmax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftmax[i] = Math.max(height[i], leftmax[i - 1]);

        }
        int rightMax[] = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int trappwater = 0;
        for (int i = 0; i < n; i++) {
            int waterlevel = Math.min(rightMax[i], leftmax[i]);
            trappwater = waterlevel - height[i] + trappwater;

        }
        return trappwater;
    }
    /*
     * Suppose a '0' in the input means that there is a leak at that position and
     * the water can leak out. After the adjustment, that is, after the water levels
     * have stabilized due to leaking, what is the answer?
     * How do we change our approach/what would be out ideal answer for this
     * scenario?
     * i found this in discussions of that que
     * my approach is that maybe for the corresponding index we can store -1 and
     * then while adding trappedwater we will exclude that index and immediate
     * neighbours of that index
     * 
     * secondly i thought maybe just subtact water for that index after completing
     * the calculations normally but the neighbours trapped water will also drain
     * and this approach doesnt incorparate that case
     * 
     * guide me like my code educator
     */

    public static int trapWithLeaks(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int total = 0;
        int segmentStart = -1;

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == 0) { // Leak
                if (segmentStart != -1) {
                    // Process segment from segmentStart to i-1
                    total += trapSegment(heights, segmentStart, i - 1);
                    segmentStart = -1;
                }
            } else {
                if (segmentStart == -1) {
                    segmentStart = i; // Start of new segment
                }
            }
        }

        // Process last segment if exists
        if (segmentStart != -1) {
            total += trapSegment(heights, segmentStart, heights.length - 1);
        }

        return total;
    }

    // Trap water in segment from index start to end (inclusive)
    public static int trapSegment(int[] heights, int start, int end) {
        int len = end - start + 1;
        if (len < 3) {
            return 0;
        }

        int left = start, right = end;
        int maxLeft = 0, maxRight = 0;
        int water = 0;

        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] >= maxLeft) {
                    maxLeft = heights[left];
                } else {
                    water += maxLeft - heights[left];
                }
                left++;
            } else {
                if (heights[right] >= maxRight) {
                    maxRight = heights[right];
                } else {
                    water += maxRight - heights[right];
                }
                right--;
            }
        }

        return water;
    }

    public static void kadanes(int numbers[]) { // doesnt work for all negative numbers
        int cs = 0;
        int ms = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            cs = cs + numbers[i];
            if (cs < 0) {
                cs = 0;
            }
            ms = Math.max(cs, ms);
        }
        System.out.println("max sum :" + ms);
    }

    public int maxSubArray(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    public static int divideAndConquer(int[] nums, int left, int right) { // tc O(n log n) sc O(logn), alternative to
                                                                          // kadane's algo - works for all cases
        // Base case: only one element
        if (left == right) {
            return nums[left];
        }

        // Find middle point
        int mid = left + (right - left) / 2;

        // Find max in three possible cases:
        // 1. Left half
        int leftMax = divideAndConquer(nums, left, mid);
        // 2. Right half
        int rightMax = divideAndConquer(nums, mid + 1, right);
        // 3. Crossing midpoint
        int crossMax = maxCrossingSum(nums, left, mid, right);

        // Return maximum of three
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    public static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // Find max sum in left half ending at mid
        int leftSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = mid; i >= left; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }

        // Find max sum in right half starting from mid+1
        int rightSum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }

        // Return sum of both parts
        return leftSum + rightSum;
    }

    public static void reverse(int numbers[]) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int temp = numbers[end];
            numbers[end] = numbers[start];
            numbers[start] = temp;
            start++;
            end--;
        }
    }

    public static int SumSubArr(int numbers[]) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int prefix[] = new int[numbers.length];
        prefix[0] = numbers[0];
        for (int x = 1; x < prefix.length; x++) {
            prefix[x] = prefix[x - 1] + numbers[x];
        }
        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i + 1; j < numbers.length; j++) {
                int end = j;
                currSum = start == 0 ? prefix[end] : prefix[end] - prefix[start - 1];// prefix sum technique
                // for (int k = start; k <= end; k++) {
                // currSum += numbers[k];--------->> brute force
                // }
                // System.out.println(currSum);
                if (currSum > maxSum) {
                    maxSum = currSum;
                }
            }
        }
        System.out.println("maxSum" + maxSum);
        return maxSum;
    }

    int min = Integer.MAX_VALUE;

    public static void pairs(int numbers[]) {
        int tp = 0;
        for (int i = 0; i < numbers.length; i++) {
            int curr = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                System.out.println("(" + curr + "," + numbers[j] + ")");
                tp++;

            }
        }
        System.out.println(tp);
    }

    public static void printSubarrays(int numbers[]) {
        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i; j < numbers.length; j++) {
                int end = j;
                for (int k = start; k <= end; k++) {
                    System.out.print(numbers[k] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(divideAndConquer(new int[] { 4, -1, 2, 1 }, 0, 3));
        pairs(new int[] { 2, 4, 6, 8, 10 });
        // Example 1: No leaks (normal case)
        int[] heights1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println("Example 1: " + trapWithLeaks(heights1));

        // Example 2: With leaks
        int[] heights2 = { 3, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        // Segments: [3], [2,1], [1,3,2,1,2,1]
        System.out.println("Example 2: " + trapWithLeaks(heights2));

        // Example 3: Leak in middle of valley
        int[] heights3 = { 4, 2, 0, 3, 5 };
        // Water drains out completely
        System.out.println("Example 3: " + trapWithLeaks(heights3));
        int arr[] = { 1, 2, 3 };
        System.out.println(arr);
        // Arrays.sort(arr);
        // for (int index = 0; index < arr.length; index++) {
        // System.out.println(arr[index]);
        // }
        bubbleSort(arr);
    }
}