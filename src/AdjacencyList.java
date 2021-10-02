import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList {

    /*
    *   Let's represent this undirected graph as an adjacency list
    *          0 ---- 1
    *          |      |
    *          4      2
    *         / \   /
    *        5---3
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

    public static void main(String[] args) {

        AdjacencyList al = new AdjacencyList();
        al.addEdge(0, 1, true);
        al.addEdge(0, 4, true);
        al.addEdge(1, 2, true);
        al.addEdge(2, 3, true);
        al.addEdge(4, 5, true);
        al.addEdge(4, 3, true);
        al.addEdge(3, 5, true);

        al.printGraph();

    }

}
