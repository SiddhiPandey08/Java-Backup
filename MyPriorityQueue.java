import java.util.Comparator;
import java.util.PriorityQueue;
public class MyPriorityQueue {
    static class Student implements Comparable<Student> {
        String name;
        int rank;

        public Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.rank, other.rank); //min-heap based on rank
        }
    
        
    }
    //priority queue using heap data structure.In jcf operations are logn time complexity.(insert,delete,getmin)
    //peek operation is o(1) time complexity.
    //fifo is not guaranteed in heaps.remove the min or max element based on the type of heap.
    public static void main(String[] args) {
        PriorityQueue<Student> minHeap = new PriorityQueue<>(); //max-heap if reverseOrder is used, min-heap otherwise
        minHeap.add(new Student("A", 0));
        minHeap.add(new Student("B", 2));
        minHeap.add(new Student("C", 1));
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.peek().name + " with rank " + minHeap.peek().rank); 
            minHeap.remove();
        }
    }
}
