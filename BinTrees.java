import java.util.*;

public class BinTrees {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        public Node buildTree(int nodes[]) { // O(n)
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        public void preorder(Node root) { // O(n)
            if (root == null) {
                System.out.print(" -1 ");
                return;
            }
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }

        public void inorder(Node root) { // O(n)
            if (root == null) {
                System.out.print(" -1 ");
                return;
            }
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        public void postorder(Node root) { // O(n)
            if (root == null) {
                System.out.print(" -1 ");
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }

        public void levelOrder(Node root) { // O(n)
            if (root == null) {
                return;
            }
            Queue<Node> queue = new java.util.LinkedList<>();
            queue.add(root);
            queue.add(null); // Level delimiter

            while (!queue.isEmpty()) {
                Node currNode = queue.remove();
                if (currNode == null) {
                    System.out.println(); // End of current level
                    if (queue.isEmpty()) {
                        break;
                    } else {
                        queue.add(null); // Add level delimiter for next level
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        queue.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        queue.add(currNode.right);
                    }
                }
            }
        }

        public int height(Node root) { // O(n)
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }

        public int countNodes(Node root) { // O(n)
            if (root == null) {
                return 0;
            }
            int leftCount = countNodes(root.left);
            int rightCount = countNodes(root.right);
            return leftCount + rightCount + 1;
        }

        public int sumNodes(Node root) { // O(n)
            if (root == null) {
                return 0;
            }
            int leftSum = sumNodes(root.left);
            int rightSum = sumNodes(root.right);
            return leftSum + rightSum + root.data;
        }

        public int diameter(Node root) { // O(n^2)
            if (root == null) {
                return 0;
            }
            int leftDiameter = diameter(root.left);
            int rightDiameter = diameter(root.right);
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            int selfDiameter = leftHeight + rightHeight + 1;
            return Math.max(selfDiameter, Math.max(leftDiameter, rightDiameter));
        }

        static class TreeInfo {
            int height;
            int diameter;

            TreeInfo(int height, int diameter) {
                this.height = height;
                this.diameter = diameter;
            }
        }

        public TreeInfo diameterOptimized(Node root) { // O(n)
            if (root == null) {
                return new TreeInfo(0, 0);
            }
            TreeInfo left = diameterOptimized(root.left);
            TreeInfo right = diameterOptimized(root.right);
            int height = Math.max(left.height, right.height) + 1;
            int selfDiameter = left.height + right.height + 1;
            int diameter = Math.max(selfDiameter, Math.max(left.diameter, right.diameter));
            return new TreeInfo(height, diameter);
        }

        public static boolean isIdentical(Node root, Node subRoot) { // O(m)
            if (root == null && subRoot == null) {
                return true;
            }
            if (root == null || subRoot == null) {
                return false;
            }
            if (root.data == subRoot.data) {
                return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
            }
            return false;
        }

        public static boolean isSubtree(Node root, Node subRoot) { // O(n*m)
            if (root == null) {
                return false;
            }
            if (root.data == subRoot.data) {
                if (isIdentical(root, subRoot)) {
                    return true;
                }
            }
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        static class HDNode {
            Node node;
            int hd;

            public HDNode(Node node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }

        public static void TopView(Node root) {
            Queue<HDNode> queue = new LinkedList<>();
            HashMap<Integer, Node> map = new HashMap<>();
            int min = 0, max = 0;
            queue.add(new HDNode(root, 0));
            queue.add(null);
            while (!queue.isEmpty()) {
                HDNode curr = queue.remove();
                if (curr == null) {
                    if (queue.isEmpty()) {
                        break;
                    } else {
                        queue.add(null);
                    }
                } else {
                    if (!map.containsKey(curr.hd)) {
                        map.put(curr.hd, curr.node);
                    }
                    if (curr.node.left != null) {
                        queue.add(new HDNode(curr.node.left, curr.hd - 1));
                        min = Math.min(min, curr.hd - 1);
                    }
                    if (curr.node.right != null) {
                        queue.add(new HDNode(curr.node.right, curr.hd + 1));
                        max = Math.max(max, curr.hd + 1);
                    }
                }
            }
            for (int i = min; i <= max; i++) {
                System.out.print(map.get(i).data + " ");
            }
            System.out.println();
        }
    }
    public static void KLevel(Node root, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            System.out.print(root.data + " ");
            return;
        }
        KLevel(root.left, k - 1);
        KLevel(root.right, k - 1);
    }
    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.data == n) {
            return true;
        }
        boolean foundLeft = getPath(root.left, n, path);
        boolean foundRight = getPath(root.right, n, path);
        if (foundLeft || foundRight) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
    public static Node lastCommonAncestor2(Node root, int n1, int n2) { // O(n) time O(1) space
        if (root == null) {
            return null;
        }
        if (root.data == n1 || root.data == n2) {
            return root;
        }
        Node leftLCA = lastCommonAncestor2(root.left, n1, n2);
        Node rightLCA = lastCommonAncestor2(root.right, n1, n2);
        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        if (leftLCA != null) {
            return leftLCA;
        }
        return rightLCA;
    }
    public static void lastCommonAncestor(Node root, int n1, int n2) { // O(n) space O(n)
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();


        getPath(root, n1, path1);
        getPath(root, n2, path2);


        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }
        Node lca = path1.get(i - 1);
        System.out.println("LCA: " + lca.data);
    }
    public static int distanceFromLCA(Node lca , int n , int distance){
        if(lca == null){
            return -1;
        }
        if(lca.data == n){
            return distance;
        }
        int leftDistance = distanceFromLCA(lca.left , n , distance + 1);
        if(leftDistance != -1){
            return leftDistance;
        }
        return distanceFromLCA(lca.right , n , distance + 1);
    }
    public static int minDistanceNodes(Node root , int n1 , int n2){
        Node lca = lastCommonAncestor2(root , n1 , n2);
        int d1 = distanceFromLCA(lca , n1 , 0);
        int d2 = distanceFromLCA(lca , n2 , 0);
        return d1 + d2;
    }
    public static void KthAncestor(Node root, int n, int k) {
        ArrayList<Node> path = new ArrayList<>();
        getPath(root, n, path);
        int index = path.size() - 1 - k;
        if (index >= 0) {
            System.out.println(k + "th ancestor is: " + path.get(index).data);
        } else {
            System.out.println("No ancestor found");
        }
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree tree = new BinaryTree();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.right.left = new Node(7);
        BinaryTree subTree = new BinaryTree();
        Node subRoot = new Node(3);
        subRoot.left = new Node(7);
        subRoot.right = new Node(6);
        // tree.TopView(root);
        // System.out.println("Is subtree: " + BinaryTree.isSubtree(root, subRoot));
        // System.out.println("Height of tree: " + tree.height(root));
        // System.out.println("diameter of tree: " + tree.diameter(root));
        // System.out.println("diameter of tree optimized: " +
        // tree.diameterOptimized(root).diameter);
        // System.out.println(root.data);
        // The binary tree is now built with 'root' as the root node.
        // tree.preorder(root);
        // tree.inorder(root);
        // tree.levelOrder(root);
        // KLevel(root, 2);
    //   System.out.println(lastCommonAncestor2(root, 4, 7).data);
        System.out.println("Minimum distance between nodes: " + minDistanceNodes(root,4,6));
    }
}