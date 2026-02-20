import java.util.*;

public class MyGraphs {
    static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
 /*graph
   ├─ graph[0] → [ Edge(0→1), Edge(0→2) ]
   ├─ graph[4] → [ Edge(4→3), Edge(4→5) ]
   ├─ graph[2] → [ Edge(2→0), Edge(2→4) ]
   

     0--1
     |  |
     2  |
     |  |
     4--3
     \  /
       5--6        */ 


        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));

        graph[6].add(new Edge(6, 5, 1));
    }

    public static void bfs(ArrayList<Edge> graph[], int start) {//adjacency list se time complexity O(V+E){vertices or edges}
        Queue<Integer> q = new LinkedList<>(); //go to immediate neighbors first , visited is req because there can be cycles , so we may prevent visiting same node again and again
        boolean visited[] = new boolean[graph.length];
        q.add(start);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.print(curr + " ");
                visited[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }
    public static void bfs_connectedComponents(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                bfs(graph, i);
            }
        }
    }


    public static void dfs(ArrayList<Edge> graph[], int curr, boolean visited[]) {//recursion
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void dfs_connectedComponents(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    public static boolean isCycleUtil(ArrayList<Edge> graph[], int curr, boolean visited[], int parent) {
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {    //neighbor not visited-->have to check further like normal dfs
                if (isCycleUtil(graph, e.dest, visited, curr)) {
                    return true;
                }
            } else if (e.dest != parent) { //neighbor visited but not parent(100% sure cycle)
                return true;
            }
            //if neighbor is visited and neighbor is parent then do nothing
        }
        return false;
    }

    public static boolean isCycle(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) { //for disconnected components
            if (!visited[i]) {
                if (isCycleUtil(graph, i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void BipartiteUtil(ArrayList<Edge> graph[], int curr, int colorArr[], int color) {
        colorArr[curr] = color;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (colorArr[e.dest] == -1) { //not colored
                BipartiteUtil(graph, e.dest, colorArr, 1 - color);
            } else if (colorArr[e.dest] == colorArr[curr]) { //neighbor has same color
                System.out.println("Not Bipartite");
                return;
            }
        }
    }
    public static void isBipartite(ArrayList<Edge> graph[]) {
        int colorArr[] = new int[graph.length];
        for (int i = 0; i < colorArr.length; i++) {
            colorArr[i] = -1; //no color
        }
        for (int i = 0; i < graph.length; i++) {
            if (colorArr[i] == -1) {
                BipartiteUtil(graph, i, colorArr, 0);
            }
        }
        System.out.println("Bipartite");
    }
    
    public static void printGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                System.out.println("Vertex " + e.src + " is connected to " + e.dest + " with weight " + e.weight);
            }
        }
    }

    public static boolean hasPath(ArrayList<Edge> graph[], int src, int dest, boolean visited[]) {//Has Path doesn’t care about distance.It cares about existence.that is why dfs is used
        if (src == dest) {
            return true;
        }
        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            //e.dest= neighbor
            if (!visited[e.dest] && hasPath(graph, e.dest, dest, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bfs(graph, 0);
        System.out.println();
        dfs(graph, 0, new boolean[graph.length]);
        System.out.println();
        System.out.println(hasPath(graph, 2, 6, new boolean[graph.length]));
        System.out.println(isCycle(graph));
    }
}