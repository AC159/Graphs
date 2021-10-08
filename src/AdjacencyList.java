import java.util.*;

public class AdjacencyList {

    /*
    *   Let's represent this undirected graph as an adjacency list
    *          0 ---- 1
    *          |      |
    *          4      2
    *         / \   /
    *        5---3
    *        |
    *        6
    * */

    public HashMap<Integer, List<Integer>> hm;

    public AdjacencyList() {
        hm = new HashMap<>();
    }

    void addEdge(int key, int value, boolean undirected) {

        // Add edge to key
        if (hm.get(key) == null) {
            hm.put(key, new ArrayList<>(List.of(value)));
        } else {
            hm.get(key).add(value);
        }

        if (undirected) {
            // Add edge to value
            if (hm.get(value) == null) {
                hm.put(value, new ArrayList<>(List.of(key)));
            } else {
                hm.get(value).add(key);
            }
        }

    }

    void printGraph() {

        for (int node : this.hm.keySet()) {
            System.out.println(node + " ---> " + this.hm.get(node));
        }
    }

    void breadthFirstSearch(int source, int destination) {

        boolean[] visited = new boolean[hm.size()];
        visited[source] = true;
        int[] dist = new int[hm.size()];
        int[] parent = new int[hm.size()];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new ArrayDeque<>(hm.size());
        q.add(source);
        parent[source] = source;
        dist[source] = 0;

        while (!q.isEmpty()) {

            int frontElement = q.remove();
            List<Integer> ls = hm.get(frontElement);

            // Add to the queue all the neighbors of the current element
            for (Integer l : ls) {
                if (!visited[l]) {
                    q.add(l);
                    visited[l] = true;
                    // update parent and distance to find the shortest path
                    parent[l] = frontElement;
                    dist[l] = dist[frontElement] + 1;
                }
            }
            System.out.println(frontElement);
        }

        // Print the shortest distance
        System.out.println("Shortest distance: ");
        for (int k = 0; k < dist.length; k++) {
            System.out.println("Shortest distance to " + k + " is " + dist[k]);
        }

        // Print the path from a source to any destination
        if (destination != -1) {
            System.out.println("Print path from source " + source + " to destination " + destination);
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            int temp = destination;
            deque.addFirst(temp);
            while (temp != source) {
                temp = parent[temp];
                deque.addFirst(temp);
            }

            for (Iterator<Integer> it = deque.iterator(); it.hasNext();) {
                Integer i = it.next();
                if (it.hasNext()) System.out.print(i + " ---> ");
                else System.out.print(i);
            }

        }

    }

    void DFSHelper(int node, boolean[] visited) {

        visited[node] = true;
        System.out.print(node + " ---> ");

        for (Integer n : hm.get(node)) {
            if (!visited[n]) {
                DFSHelper(n, visited);
            }
        }
    }

    void depthFirstSearch(int source) {
        boolean[] visited = new boolean[hm.size()];
        Arrays.fill(visited, false);
        DFSHelper(source, visited);
        System.out.print(" .");
    }

    public static void main(String[] args) {

        AdjacencyList al = new AdjacencyList();
        al.addEdge(0, 1, true);
        al.addEdge(1, 2, true);
        al.addEdge(2, 3, true);
        al.addEdge(3, 5, true);
        al.addEdge(5, 6, true);
        al.addEdge(4, 5, true);
        al.addEdge(0, 4, true);
        al.addEdge(3, 4, true);

        System.out.println("Printing graph: ");
        al.printGraph();
        System.out.println("Breadth first search: ");
        al.breadthFirstSearch(1, 6);

        System.out.println("\nDepth first search: ");
        al.depthFirstSearch(1);

    }

}
