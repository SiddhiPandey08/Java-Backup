import java.util.*;

public class MyStack {
    // static class Node {
    // int data;
    // Node next;

    // Node(int data) {
    // this.data = data;
    // this.next = null;
    // }
    // }

    // static class Stack {
    // static Node head = null;

    // public static boolean isLLEmpty() {
    // return head == null;
    // }

    // public static void pushLL(int data) {
    // Node newNode = new Node(data);
    // if (isLLEmpty()) {
    // head = newNode;
    // return;
    // }
    // newNode.next = head;
    // head = newNode;
    // }

    // public static int popLL() {
    // if (isLLEmpty()) {
    // return -1;
    // }
    // int top = head.data;
    // head = head.next;
    // return top;
    // }

    // public static int peekLL() {
    // if (isLLEmpty()) {
    // return -1;
    // }
    // return head.data;
    // }

    // yaha se arraylist me stack hai line 72 tak
    // static ArrayList<Integer> list = new ArrayList<>();

    // public static boolean isEmpty() {
    // return list.size() == 0;
    // }

    // public static void push(int data) {
    // list.add(data);
    // }

    // public static int pop() {
    // if (isEmpty()) {
    // return -1;
    // }
    // int top = list.get(list.size() - 1);
    // list.remove(list.size() - 1);
    // return top;
    // }

    // public static int peek() {
    // if (isEmpty()) {
    // return -1;
    // }
    // return list.get(list.size() - 1);
    // }
    // }
    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }

    public static String reverseString(String str) {
        Stack<Character> s = new Stack<>();
        int idx = 0;
        while (idx < str.length()) {
            s.push(str.charAt(idx));
            idx++;
        }

        StringBuilder result = new StringBuilder("");
        while (!s.isEmpty()) {
            char curr = s.pop();
            result.append(curr);
        }

        return result.toString();
    }

    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int top = s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }

    public static void printStack(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    public static void stockSpan(int stocks[], int span[]) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);
        for (int i = 1; i < stocks.length; i++) {
            int currPrice = stocks[i];
            while (!s.isEmpty() && currPrice > stocks[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }
            s.push(i);
        }
    }

    public static void nextGreaterElement(int arr[], int nxtGreater[]) { // next smallest nikalna hai to bas while wale
                                                                         // greater than ki jagah smaller than ka sign
                                                                         // and equal to ka sign use kar dena hai
                                                                         // aur curr>=s.peek() kar dena hai
                                                                         // aur curr<s.peek() kar dena hai
                                                                         // for loop me i ko 0 se and arr.length-1 tak
                                                                         // kar dena hai
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            int curr = arr[i];
            while (!s.isEmpty() && curr >= s.peek()) {
                s.pop();
            }
            if (s.isEmpty()) {
                nxtGreater[i] = -1;
            } else {
                nxtGreater[i] = s.peek();
            }
            s.push(curr);
        }

    }

    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) { // time complexity O(n) and space complexity O(n)
            char ch = str.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                s.push(ch);
            } else {
                if (s.isEmpty()) { // example str = ")))"
                    return false;
                }
                if ((s.peek() == '(' && ch == ')')
                        || (s.peek() == '{' && ch == '}')
                        || (s.peek() == '[' && ch == ']')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }

    public static boolean isDuplicate(String str) { // time complexity O(n) and space complexity O(n)
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // closing
            if (ch == ')') {
                int count = 0;
                while (s.peek() != '(') {
                    s.pop();
                    count++;
                }
                if (count < 1) {
                    return true;
                } else {
                    s.pop();
                }
            } else {
                // opening
                s.push(ch);

            }
        }
        return false;
    }

    public static void MaxAreaHistogram(int heights[]) {  // time complexity O(n) and space complexity O(n)
        int n = heights.length;
        int leftSmaller[] = new int[n];
        int rightSmaller[] = new int[n];

        // left smaller
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && heights[s.peek()] >= heights[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                leftSmaller[i] = -1;
            } else {
                leftSmaller[i] = s.peek();
            }
            s.push(i);
        }

        // right smaller
        s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && heights[s.peek()] >= heights[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                rightSmaller[i] = n;
            } else {
                rightSmaller[i] = s.peek();
            }
            s.push(i);
        }

        // max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int width = rightSmaller[i] - leftSmaller[i] - 1;
            int area = height * width;
            maxArea = Math.max(maxArea, area);
        }
        System.out.println("Max Area in Histogram: " + maxArea);
    }

    public static void main(String[] args) {
        // // Stack s = new Stack();
        // Stack<Integer> s = new Stack<>(); // ---> java collection frameworks se bas
        // ek line likhna hai then pus pop peek
        // // ke alag se function banane ki need nhi

        // s.push(0);
        // s.push(1);
        // s.push(2);

        // reverseStack(s);
        // printStack(s);

        // // String str = "Siddhi";
        // // System.out.println(reverseString(str));
        int arr[] = { 9, 2, 6, 4, 5 };
        int nxtGreater[] = new int[arr.length];
        int stocks[] = { 100, 80, 60, 70, 60, 85, 100 };
        int span[] = new int[stocks.length];
        stockSpan(stocks, span);
        for (int i = 0; i < span.length; i++) {
        System.out.print(span[i] + " ");
        }
        nextGreaterElement(arr, nxtGreater);
        for (int i = 0; i < nxtGreater.length; i++) {
            System.out.print(nxtGreater[i] + " ");
        }
        String str = "{[[()]]}";
        String str1 = "((a+b))";
        int heights[] = { 2, 1, 5, 6, 2, 3 };
        MaxAreaHistogram(heights);
        System.out.println(isDuplicate(str1));
        System.out.println(isValid(str));

    }
}
