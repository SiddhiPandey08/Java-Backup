import java.util.*;

// Heaps: complete binary tree that satisfies heap property.
// visualize-array and implement by binary tree.
// heaps are not implemented by class because it requires O(n) time and it is
// discussed in priority queue thena tc should be O(log n).
// cbt complete binary tree is a tree in which every level, except possibly the
// last, is completely filled except the last one, and all nodes are as far left
// as possible.
//for min heap change greater than to less than in add and heapify methods.
public class Heaps {

    // --------------------- CUSTOM MAX HEAP (ArrayList) ---------------------
    static class MaxHeap {
        private ArrayList<Integer> list = new ArrayList<>();

        // Add element to heap (O(log n))
        public void add(int val) {
            list.add(val);
            int child = list.size() - 1;
            int parent = (child - 1) / 2;

            // Heapify up
            while (child > 0 && list.get(child) > list.get(parent)) {
                swap(child, parent);
                child = parent;
                parent = (child - 1) / 2;
            }
        }

        // Return root (max element)
        public int peek() {
            return list.size() == 0 ? -1 : list.get(0);
        }

        // Remove max (root) from heap (O(log n))
        public int remove() {
            if (list.isEmpty())
                return -1;

            int removedValue = list.get(0);

            // Move last element to root and remove last
            int lastIndex = list.size() - 1;
            list.set(0, list.get(lastIndex));
            list.remove(lastIndex);

            heapifyDown(0);

            return removedValue;
        }

        // Heapify down (O(log n))
        private void heapifyDown(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int max = i;

            if (left < list.size() && list.get(left) > list.get(max)) {
                max = left;
            }
            if (right < list.size() && list.get(right) > list.get(max)) {
                max = right;
            }

            if (max != i) {
                swap(i, max);
                heapifyDown(max);
            }
        }

        private void swap(int i, int j) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public String toString() {
            return list.toString();
        }
    }

    static class Row implements Comparable<Row> {
        int soldiers;
        int idx;

        public Row(int soldiers, int idx) {
            this.soldiers = soldiers;
            this.idx = idx;
        }

        @Override
        public int compareTo(Row r2) {
            if (this.soldiers == r2.soldiers) {
                return this.idx - r2.idx;
            } else {
                return this.soldiers - r2.soldiers;
            }
        }
    }
    /*
     * ------------------------------------------------------------
     * 2) Nearby Cars Problem (k nearest to origin)
     * ------------------------------------------------------------
     */

    // Car model
    static class Car {
        int id;
        int x;
        int y;

        Car(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        int distSq() {
            return x * x + y * y;
        }
    }

    // Method to get k nearest cars
    public static List<Car> getNearestCars(List<Car> cars, int k) {
        PriorityQueue<Car> maxHeap = new PriorityQueue<>(
                (a, b) -> b.distSq() - a.distSq() // max heap: farthest at top
        );

        for (Car car : cars) {
            maxHeap.add(car);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return new ArrayList<>(maxHeap);
    }

    // --------------------- HEAP UTILITIES ---------------------

    // Heapify for array-based heap (Used for HeapSort)
    public static void heapify(int arr[], int n, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        if (left < n && arr[left] > arr[max])
            max = left;
        if (right < n && arr[right] > arr[max])
            max = right;

        if (max != i) {
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;

            heapify(arr, n, max);
        }
    }

    // HeapSort using array heap (O(n log n))
    public static void heapSort(int arr[]) {
        int n = arr.length;

        // Step 1: Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: Extract elements
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    // Minimum cost to connect ropes (Min-Heap)
    public static int minCostToConnectRopes(int[] ropes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int r : ropes)
            pq.add(r);

        int cost = 0;

        while (pq.size() > 1) {
            int min1 = pq.remove();
            int min2 = pq.remove();
            int sum = min1 + min2;

            cost += sum;
            pq.add(sum);
        }

        return cost;
    }

    // ------------WEAKEST SOLIDER-------------
    public static void weakestSolider(int arr[][], int k) {
        PriorityQueue<Row> ws = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr[0].length; j++) {
                count += arr[i][j] == 1 ? 1 : 0;
            }
            ws.add(new Row(count, i));
        }
        for (int i = 0; i < k; i++) {
            System.out.println("R" + ws.remove().idx);
        }
    }

    // ----------------------------SLIDING WINDOW MAXIMUM-----------------------------
    static class SWMpairs implements Comparable<SWMpairs> {
        int idx;
        int val;

        public SWMpairs(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(SWMpairs p2) {
            return p2.val - this.val; // max heap
        }

        public static void slidingWindowMax(int arr[], int k) {
            int n = arr.length;
            int result[] = new int[n - k + 1];
            PriorityQueue<SWMpairs> swm = new PriorityQueue<>();
            //store first k elements(first window)
            for (int i = 0; i < k; i++) {
                swm.add(new SWMpairs(i, arr[i]));
            }
            result[0] = swm.peek().val;
            //process remaining windows
            for (int i = k; i < n; i++) {
                //remove elements not part of window
                while (!swm.isEmpty() && swm.peek().idx <= i - k) {
                    swm.remove();
                }
                //add current element
                swm.add(new SWMpairs(i, arr[i]));
                result[i - k + 1] = swm.peek().val;
            }
            //print result
            for (int x : result) {
                System.out.print(x + " ");
            }   
           
        }

        // --------------------- MAIN ---------------------
        public static void main(String[] args) {
            System.out.println("----- Testing Sliding Window Maximum -----");
            int arr1[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
            slidingWindowMax(arr1, 3);
            System.out.println();

            System.out.println("-------Testing weakestSoldiers---------");
            int army[][] = { { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } };
            weakestSolider(army, 2);

            System.out.println("----- Testing Custom MaxHeap -----");
            MaxHeap heap = new MaxHeap();
            heap.add(10);
            heap.add(20);
            heap.add(15);
            heap.add(30);
            System.out.println("Heap after insertions: " + heap);

            heap.remove();
            System.out.println("Heap after removal: " + heap);

            System.out.println("\n----- Testing HeapSort -----");
            int arr[] = { 5, 3, 8, 4, 2, 7, 1, 10 };
            heapSort(arr);
            for (int x : arr)
                System.out.print(x + " ");
            System.out.println();

            System.out.println("\n----- Testing Rope Cost -----");
            int[] ropes = { 2, 3, 3, 4, 6 };
            System.out.println("Minimum cost to connect ropes: " + minCostToConnectRopes(ropes));
            // ----- Test Nearby Cars -----
            List<Car> cars = Arrays.asList(
                    new Car(1, 3, 3),
                    new Car(2, 5, -1),
                    new Car(3, -2, 4),
                    new Car(4, 0, 2));

            int k = 2;
            List<Car> res = getNearestCars(cars, k);

            System.out.println("\nNearest " + k + " cars:");
            for (Car c : res) {
                System.out.println("Car " + c.id + " at (" + c.x + ", " + c.y + ")");
            }
        }
    }
}
