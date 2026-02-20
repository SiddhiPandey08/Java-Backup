import java.util.*;
//LinkedHashMap<Key,Value> maintains insertion order(keys are in order of their insertion)->uses doubly linked list to maintain order->O(1) time complexity for put , get , remove operations
//TreeMap<Key,Value> maintains sorted order of keys(keys are in natural sorted order or custom comparator order)->uses Red-Black tree(Self Balancing bst) data structure->O(log n) time complexity for put , get , remove operations
//hashset is implemented using hashmap internally->stores only keys(no values)->no duplicate keys allowed->O(1) time complexity for add , remove , contains operations
public class HashmapImplement<K, V> {

    // Node stored in each bucket (LinkedList)
    private class Node {
        K key;
        V value;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    private LinkedList<Node>[] buckets; // array of buckets
    private int N = 4;                  // number of buckets (small as in video)
    private int size = 0;               // number of key-value pairs

    @SuppressWarnings("unchecked")
    public HashmapImplement() {
        buckets = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // hash function -> bucket index
    private int hashFunction(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % N;
    }

    // search key in bucket 'bi'. returns index inside linked list or -1 if not found
    // comparison style matches the screenshot: node.key.equals(key) while safe for nulls
    private int searchInLL(K key, int bi) {
        LinkedList<Node> ll = buckets[bi];

        for (int i = 0; i < ll.size(); i++) {
            Node node = ll.get(i);
            if (node.key == key || (node.key != null && node.key.equals(key))) {
                return i;
            }
        }
        return -1;
    }

    // put key -> value (if key exists, replace and return old value, else return null)
    public V put(K key, V value) {
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if (di != -1) {
            // key exists -> replace
            Node node = buckets[bi].get(di);
            V old = node.value;
            node.value = value;
            return old;
        } else {
            // new key -> add
            buckets[bi].add(new Node(key, value));
            size++;
            return null;
        }
    }

    // get value for key (null if absent)
    public V get(K key) {
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);
        if (di == -1) return null;
        return buckets[bi].get(di).value;
    }

    // remove key, return removed value or null if absent
    public V remove(K key) {
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);
        if (di == -1) return null;
        Node node = buckets[bi].remove(di);
        size--;
        return node.value;
    }

    public boolean containsKey(K key) {
        int bi = hashFunction(key);
        return searchInLL(key, bi) != -1;
    }

    public int size() {
        return size;
    }

    // debugging print
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (LinkedList<Node> bucket : buckets) {
            for (Node n : bucket) {
                if (!first) sb.append(", ");
                sb.append(String.valueOf(n.key)).append("=").append(String.valueOf(n.value));
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }
    public ArrayList<K> keySet() {
    ArrayList<K> keys = new ArrayList<>();

    // go through each bucket
    for (int i = 0; i < buckets.length; i++) {
        LinkedList<Node> ll = buckets[i];

        // go through each node inside the bucket
        for (Node node : ll) {
            keys.add(node.key);
        }
    }

    return keys;
}


    // small test
    public static void main(String[] args) {
        HashmapImplement<String, Integer> map = new HashmapImplement<>();
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("mango", 30);
        map.put(null, 99);        // null key supported (goes to bucket 0)
        map.put("apple", 11);     // replace value

        System.out.println("Map: " + map);
        System.out.println("get(mango): " + map.get("mango"));
        System.out.println("get(null): " + map.get(null));
        System.out.println("contains(banana): " + map.containsKey("banana"));
        System.out.println("remove(banana): " + map.remove("banana"));
        System.out.println("Map after remove: " + map);
        System.out.println("size: " + map.size());

        ArrayList<String> keys = map.keySet();
        System.out.println("Keys: " + keys);
        ArrayList<Integer> values = new ArrayList<>();
        for (String key : keys) {
            values.add(map.get(key));
        }
        System.out.println("Values: " + values);
    }
}

