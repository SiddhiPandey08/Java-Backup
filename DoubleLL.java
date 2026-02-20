public class DoubleLL {
    public class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.prev = null;
            this.data = data;
            this.next = null;
        }
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public int removeFirst() {
        if (head == null) {
            System.out.println("DLL is empty");
            return Integer.MIN_VALUE;
        }

        if (size == 1) {
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }
        int val = head.data;
        head = head.next;
        head.prev = null;
        size--;
        return val;
    }

    public void reverse() {
        Node curr = head;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;

            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public static void main(String[] args) {
        DoubleLL Dll = new DoubleLL();
        Dll.addFirst(4);
        Dll.addFirst(3);
        Dll.addFirst(2);
        Dll.addFirst(1);
        Dll.print();
        Dll.removeFirst();
        Dll.print();

    }
}
