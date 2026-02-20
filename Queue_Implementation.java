public class Queue_Implementation {
    // queue using linked list
    //Queue<Integer> q = new LinkedList<>();
    //Queue <Integer> q = new Deque<>(); --> implentation through array deque
    //difference between linked list and array deque is that linked list uses more memory as it stores address of next node also
    // whereas array deque uses contiguous memory but linked list can grow dynamically
    // in array deque we have to define size initially but in linked list its dynamic
    // in linked list insertion and deletion is O(1) but access is O(n) whereas in array deque access is O(1) but insertion and deletion is O(n) in worst case when resizing is needed
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }   
   static class LinkedListQueue {
        static Node head = null;
        static Node tail = null;

        public static boolean isEmpty() {
            return (head == null && tail == null);
        }
//isFull is not required in linked list implementation of queue as it can grow dynamically until memory is exhausted
        public static void add(int data) { // O(1)
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }
//***since queue follows FIFO, we always remove from head and add to tail****
        public static int remove() { // O(1)
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            int result = head.data;
            if (head == tail) { // single element
                head = tail = null;
            } else {
                head = head.next;
            }
            return result;
        }

        public static int peek() { // O(1)
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return head.data;
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // queue using circular array
    static class InnerQueue {
        static int arr[];
        static int size;
        static int rear;
        static int front;

        InnerQueue(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        public static boolean isEmpty() {
            return rear == -1;
        }

        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        public static void add(int data) { // time complexity O(1)
            if (isFull()) {
                System.out.println("Queue is full ");
                return;
            }
            if (front == -1) { // first element added
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        } // add , remove , peek sabki time complexity ab O(1)

        public static int remove() { // time complexity O(n)
            if (isEmpty()) {
                System.out.println("Queue is empty ");
                return -1;
            }
            int result = arr[front];
            if (rear == front) { // single element condition
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty ");
                return -1;
            }
            return arr[front];
        }
    }

    public static void main(String args[]) {
        InnerQueue q = new InnerQueue(3);

        q.add(1);
        q.add(2);
        q.add(3);
       
        q.add(4);
       
        q.add(5);
        while (!q.isEmpty()) {
            System.out.println(q.peek());
            q.remove();
        }
    }
}
