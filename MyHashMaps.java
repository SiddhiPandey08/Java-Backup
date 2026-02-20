import java.util.*;

public class MyHashMaps {
    public static void MajorityElement() {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = { 3, 2, 3, 4, 2, 2, 1, 6, 3 };
        int limit = arr.length / 3;

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (map.get(key) >= limit) {
                System.out.println(key);
            }
        }
    }
    public static String findStartingCity(HashMap<String, String> map) {
       HashMap<String, String> reverseMap = new HashMap<>();
       for (String key : map.keySet()) {
           reverseMap.put(map.get(key), key);
       }
       for (String city : map.keySet()) {
           if (!reverseMap.containsKey(city)) {
               return city;
           }
       }
       return null; // In case there is no valid starting city
    }
    public static void Itinerary() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Chennai", "Bangalore");
        map.put("Mumbai", "Delhi");
        map.put("Goa", "Chennai");
        map.put("Delhi", "Goa");
        String startCity = findStartingCity(map);
        if (startCity == null) {
            System.out.println("No valid starting city found.");
            return;
        }

        while (startCity != null) {
            String dest = map.get(startCity);
            if (dest == null) {
                break;
            }
            System.out.println(startCity + " -> " + dest);
            startCity = dest;
        }
    }
    public static void LargestSubarrayWithZeroSum() {//O(n)
        int[] arr = { 1, 2, -2, 4, -4 };
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum == 0) {
                maxLength = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        System.out.println("Length of largest subarray with zero sum: " + maxLength);
    }

    public static void validAnagram() {
        String s = "anagram";
        String t = "nagarak";

        if (s.length() != t.length()) {
            System.out.println("Not anagrams");
            return;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                System.out.println("Not anagrams");
                return;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        if (map.isEmpty()) {
            System.out.println("Anagrams");
        }
        System.out.println("Not anagrams");

    }

    public static void Union_Intersection() {
        HashSet<Integer> map = new HashSet<>();
        int[] arr1 = { 1, 2, 3, 4 };
        int[] arr2 = { 3, 4, 5, 6 };
        // union
        for (int num : arr1) {
            map.add(num);
        }
        for (int num : arr2) {
            map.add(num);
        }
        System.out.println("union size: " + map.size());
        // intersection
        map.clear();
        for (int num : arr1) {
            map.add(num);
        }
        int count = 0;
        for (int num : arr2) {
            if (map.contains(num)) {
                count++;
                map.remove(num);
            }
        }
        System.out.println("intersection size: " + count);

    }

    public static void main(String[] args) {
        MajorityElement();
        validAnagram();
        Union_Intersection();
        LargestSubarrayWithZeroSum();
    }
}
