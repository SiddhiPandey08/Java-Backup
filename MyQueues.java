import java.util.*;
//deque=double ended queue
//operations: addFirst(), addLast(), removeFirst(), removeLast(), peekFirst(), peekLast()
//deque can be used to implement both stack and queue
//stack using deque: push()=addFirst(), pop()=removeFirst() --> time complexity O(1)
//queue using deque: add()=addLast(), remove()=removeFirst()--> time complexity O(1)

public class MyQueues {
    public static void reverseQueue(Queue<Integer> q) { // O(n) time and O(n) space complexcity
        //using a stack
        Stack<Integer> s = new Stack<>();
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }
    public static void firstNonRepeating(String str){       //O(n)
        int freq[] = new int[26];                //stream of characters likha tha question me..stream likha hota toh queue use karte hai 
        Queue<Character> q = new LinkedList<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            q.add(ch);
            freq[ch-'a']++;
            while(!q.isEmpty() && freq[q.peek()-'a']>1){
                q.remove();
            }
            if(q.isEmpty()){
                System.out.print(-1+" ");
            }else{
                System.out.print(q.peek()+" ");
            }
        }
    }
    public static void interleaveQueue(Queue<Integer> q){  //O(n) time and O(n) space
        Queue<Integer> firstHalf = new LinkedList<>();
        int size = q.size();
        for(int i=0;i<size/2;i++){
            firstHalf.add(q.remove());
        }
        while(!firstHalf.isEmpty()){
            q.add(firstHalf.remove());
            q.add(q.remove());
        }
    }

    
    // ----------------------------implementation of stack using 2 queues-----------------------------------
    static class TwoQueueStack {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public static boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public static void push(int data) {
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        public static int pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            int top = -1;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }

            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }

            }
            return top;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            int top = -1;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }

            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }

            }
            return top;

        }

    }

    // ----------------------------implementation of queue using 2 stacks-----------------------------------
    static class TwoStackQueue {
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        public static boolean isEmpty() {
            return s1.isEmpty();
        }

        public static void add(int data) { // O(n)
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public static int remove() { // O(1)
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return (int) s1.pop();
        }

        public static int peek() { // O(1)
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return (int) s1.peek();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d= new LinkedList<>();
        d.addFirst(1);
        d.addLast(2);
        System.out.println(d);
        d.removeFirst();
        System.out.println(d);  
        d.addLast(10);
        d.addLast(20);
        System.out.println(d);
        d.removeLast();
        System.out.println(d);

        // Queue<Integer> q = new LinkedList<>();
        // q.add(1);
        // q.add(2);   
        // q.add(3);
        // q.add(4);
        // q.add(5);
        // q.add(6);
        // q.add(7);
        // System.out.println("Original queue: " + q);
        // interleaveQueue(q);
        // System.out.println("Interleaved queue: " + q);
       /*  firstNonRepeating("aabccxb");
        // Queue q
        // Testing TwoStackQueue

        System.out.println("Testing Queue implementation using two stacks:");
        TwoStackQueue.add(10);
        TwoStackQueue.add(20);
        TwoStackQueue.add(30);
        System.out.println("Front element: " + TwoStackQueue.peek());

        // Dequeue elements
        System.out.println("Removed Element: " + TwoStackQueue.remove());
        System.out.println("Front element after removal: " + TwoStackQueue.peek());

        // Remove and print remaining elements
        while (!TwoStackQueue.isEmpty()) {
            System.out.println("Removed: " + TwoStackQueue.remove());
        }

        // Testing TwoQueueStack
        System.out.println("\nTesting Stack implementation using two queues:");
        TwoQueueStack.push(10);
        TwoQueueStack.push(20);
        TwoQueueStack.push(30);

        System.out.println("Top element: " + TwoQueueStack.peek());
        System.out.println("Popped: " + TwoQueueStack.pop());
        System.out.println("Top after pop: " + TwoQueueStack.peek());

        while (!TwoQueueStack.isEmpty()) {
            System.out.println("Popped: " + TwoQueueStack.pop());
        }*/
    }
}
