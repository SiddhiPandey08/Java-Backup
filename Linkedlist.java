public class Linkedlist {
    // Inner Node class
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    // we will build methods to add , remove , print and other operations on LL's
    public void addFirst(int data) { // O(k) constant time complexity
        // add new node
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        // new node ka pointer head ko point karega
        newNode.next = head;
        // newNode ko head bana denge
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public int removeFirst() {
        if (size == 0) {
            System.out.println("LL is empty");
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {
        if (size == 0) {
            System.out.println("LL is empty");
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }

        int val = tail.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;

    }

    public int helper(Node head, int target) { // tc and sc both O(n)
        if (head == null) {
            return -1;
        }
        if (head.data == target) {
            return 0;
        }
        int idx = helper(head.next, target);
        if (idx == -1) {
            return -1;
        }
        return idx + 1; // cannot use idx++ as it increases the value before returning so the actual
                        // value is never return

    }

    public int recSearch(int target) {
        return helper(head, target);
    }

    public void reverseLL() { // O(n)
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        head = prev;
    }

    public void removeNthFromEnd(int n) {
        int sz = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            sz++;
        }
        if (n == sz) {
            head = head.next; // if we are asked to remove nth from last ans n = size -->head
        }
        int i = 1;
        int iTofind = sz - n;
        Node prev = head;
        while (i < iTofind) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
    }

    public Node findMid(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean checkPalindromeLL() {
        if (head == null || head.next == null) {
            return true;
        }
        Node midNode = findMid(head);

        Node prev = null;
        Node curr = midNode;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public void print() { // O(n)
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void addMiddle(int idx, int data) {
        Node newNode = new Node(data);
        Node temp = head;
        int i = 0;
        if (idx == 0) {
            addFirst(data);
            return;
        }
        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public boolean isCycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public void removeCycle() {
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }
        slow = head;
        Node prev = null;
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = null;

    }

    public Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node merge(Node head1, Node head2) {
        Node mergedLL = new Node(-1); // dummy node
        Node temp = mergedLL;

        // Merge until one list ends
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        // Attach remaining nodes (if any)
        if (head1 != null) {
            temp.next = head1;
        } else {
            temp.next = head2;
        }

        return mergedLL.next;
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);
        return merge(newLeft, newRight);
    }

    public void ZigZagLL() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;
        // reverse 2nd half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left = head;
        Node right = prev;
        Node nextL, nextR;
        // zig zag merge
        while (left != null && right != null) {
            nextL = left.next;
            left.next = right;
            nextR = right.next;
            right.next = nextL;

            left = nextL;
            right = nextR;

        }

    }

    public static void main(String[] args) {
        Linkedlist LL = new Linkedlist();
        // LL.addFirst(10);
        // LL.addFirst(20);
        // LL.addFirst(30);
        // LL.addLast(35);
        // LL.addLast(40);
        // LL.addMiddle(2, 60);
        // // LL.removeFirst();
        // // LL.removeLast();
        // LL.print();
        // // System.out.println(LL.recSearch(35));
        // // System.out.println("size " + size);
        // LL.reverseLL();
        // LL.print();
        // LL.removeNthFromEnd(3);
        LL.head = LL.new Node(1);
        LL.head.next = LL.new Node(2);
        LL.head.next.next = LL.new Node(3);
        LL.head.next.next.next = LL.new Node(4);
        LL.head.next.next.next.next = LL.new Node(5);
        // LL.removeCycle();
        LL.print();
        // LL.mergeSort(LL.head);
        LL.ZigZagLL();
        LL.print();
        // System.out.println(LL.isCycle());

    }
}