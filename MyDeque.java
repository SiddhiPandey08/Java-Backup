import java.util.Deque;
import java.util.LinkedList;

public class MyDeque {
    static class StackUsingDeque {
        Deque<Integer> d = new LinkedList<>();

        public void push(int data) {
            d.addFirst(data);
        }

        public int pop() {

            return d.removeFirst();
        }

        public int peek() {

            return d.peekFirst();
        }
    }

    static class QueueUsingDeque {
        Deque<Integer> d = new LinkedList<>();

        public void add(int data) {
            d.addLast(data);
        }

        public int remove() {

            return d.removeFirst();
        }

        public int peek() {

            return d.peekFirst();
        }
    }
    
}
