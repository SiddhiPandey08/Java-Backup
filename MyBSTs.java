
import java.util.ArrayList;

public class MyBSTs {
    // time complexity of searching in a BST is O(h) where h is the height of the
    // tree.
    // In the average case, for a balanced BST, h = O(log n), making the search
    // operation efficient.
    // In the worst case, for a skewed BST, h = O(n), where n is the number of nodes
    // in the tree.
    // time complexity of insertion in a BST is also O(h) where h is the height of
    // the tree.
    // time complexity of searching in a binary tree is O(n) in the average and
    // worst cases, as it may require visiting all nodes.
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static Node insert(Node root, int data) { // Insert data into the BST at root position
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            // Insert in the left subtree
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            // Insert in the right subtree
            root.right = insert(root.right, data);
        }
        return root;
    }

    public static void inorder(Node root) { // Inorder traversal of the BST
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void preorder(Node root) { // Preorder traversal of the BST
        if (root != null) {
            System.out.print(root.data + "->");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static boolean search(Node root, int key) { // O(h) time complexity
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        if (key < root.data) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    /*
     * deleting a node:
     * first we need to search the node to be deleted.
     * If the node is found, we need to consider three cases:
     * 1. Node to be deleted is a leaf node (no children): return null to parent -
     * it will Simply remove the node(set parent's pointer to null).(garbage
     * collector will take care of it)
     * 2. Node to be deleted has one child: Remove the node and link its parent to
     * its child.
     * 3. Node to be deleted has two children(inorder sucessor has 0 or 1 child):
     * Find the inorder successor (smallest or leftmost node in the right subtree)
     * or inorder predecessor (largest node in the left subtree),
     * copy its value to the node to be deleted, and then delete the inorder
     * successor or predecessor.
     */
    public static Node delete(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {// root.data == key
            // Node to be deleted found
            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: One child
            else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Case 3: Two children
            else {
                // Find inorder successor (smallest in the right subtree)
                Node IS = findInorderSucc(root.right);
                root.data = IS.data; // Copy the inorder successor's value to this node
                root.right = delete(root.right, IS.data); // Delete the inorder successor
            }
        }
        return root;
    }

    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.data >= k1 && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data + " ");
            printInRange(root.right, k1, k2);
        } else if (root.data < k1) {
            printInRange(root.right, k1, k2);
        } else {
            printInRange(root.left, k1, k2);
        }
    }

    public static Node findInorderSucc(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void printRootToLeaf(Node root, String path) { // using backtracking and string builder
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            System.out.println(path + root.data);
            return;
        }
        printRootToLeaf(root.left, path + root.data + "->");
        printRootToLeaf(root.right, path + root.data + "->");
    }

    public static void printRootToLeaf(Node root, ArrayList<Integer> path) { // using backtracking and arraylist
        if (root == null) {
            return;
        }
        path.add(root.data);
        if (root.left == null && root.right == null) {
            // print the path
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + " ");
            }
            System.out.println();
        } else {
            printRootToLeaf(root.left, path);
            printRootToLeaf(root.right, path);
        }
        path.remove(path.size() - 1);
    }

    public static boolean isBST(Node root, Node min, Node max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.data <= min.data) {
            return false;
        }
        if (max != null && root.data >= max.data) {
            return false;
        }
        return isBST(root.left, min, root) && isBST(root.right, root, max);
    }

    public static Node mirrorBST(Node root) {
        if (root == null) {
            return null;
        }
        // swap
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorBST(root.left);
        mirrorBST(root.right);
        return root;
    }

    public static Node sortedArr2BST(int[] arr, int st, int end) { // O(n) time complexity
        if (st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = sortedArr2BST(arr, st, mid - 1);
        root.right = sortedArr2BST(arr, mid + 1, end);
        return root;
    }

    public static Node sortedArr2BST(ArrayList<Integer> arr, int st, int end) { // O(n) time complexity
        if (st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        Node root = new Node(arr.get(mid));
        root.left = sortedArr2BST(arr, st, mid - 1);
        root.right = sortedArr2BST(arr, mid + 1, end);
        return root;
    }

    public static Node BST2balacedBST(Node root) { // O(n) time complexity
        ArrayList<Integer> arr = new ArrayList<>();
        storeInorder(root, arr);
        return sortedArr2BST(arr, 0, arr.size() - 1);
    }

    public static void storeInorder(Node root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }
        storeInorder(root.left, arr);
        arr.add(root.data);
        storeInorder(root.right, arr);
    }

    public static void main(String[] args) {
        int arr[] = { 3, 5, 7, 10, 15, 20, 25 };

        Node root = new Node(8);
        root.left.right = new Node(6);
        root.left = new Node(5);
        root.left.left = new Node(3);
        
        BST2balacedBST(root);
        // Node root = new Node(8);
        // root.left = new Node(5);
        // root.right = new Node(10);
        // root.left.left = new Node(3);
        // root.left.right = new Node(6);
        // root.left.left.left = new Node(1);
        // root.left.left.right = new Node(4);
        // root.right.right = new Node(11);
        // root.right.right.right = new Node(14);
        // preorder(root);
        // root = mirrorBST(root);
        // System.out.println();
        // preorder(root);
        // int values[] = { 8, 5, 3, 1, 4, 6, 10, 11, 14 };
        // Node root = null;
        // for (int value : values) {
        // root = insert(root, value);
        // }
        // inorder(root); // Print inorder traversal of the BST
        // System.out.println(search(root, 8) ? "Found" : "Not Found"); // Search for a
        // key in the BST
        // root = delete(root, 10); // Delete a node from the BST
        // System.out.println();
        // inorder(root); // Print inorder traversal after deletion
        // System.out.println();
        // printInRange(root, 0, 8);
        // System.out.println(isBST(root, null, null));
        // printRootToLeaf(root, new ArrayList<>());
        // printRootToLeaf(root, "");
    }
}
