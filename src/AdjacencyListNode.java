import java.util.ArrayList;
import java.util.HashMap;


/*
   Directed graph
                                        Delhi
*                                         |
*                                          >
*   Delhi ---> Paris ---> New York ---> London
*
* */

public class AdjacencyListNode {

    public HashMap<String, Node> hm;

    public AdjacencyListNode(String[] cities) {
        this.hm = new HashMap<>();
        for (String city: cities) {
            this.hm.put(city, new Node(city));
        }
    }

    public void addEdge(String name, String destination, boolean undirected) {
        this.hm.get(name).al.add(destination);
        if (undirected) this.hm.get(destination).al.add(name);
    }

    public void printGraph() {
        for(String city: this.hm.keySet()) {
            System.out.println(city + " ---> " + this.hm.get(city).al);
        }
    }

    public static void main(String[] args) {

        String[] arr = { "Delhi", "New York", "London", "Paris" };
        AdjacencyListNode graph = new AdjacencyListNode(arr);

        graph.addEdge("Delhi", "London", false);
        graph.addEdge("New York", "London", false);
        graph.addEdge("Delhi", "Paris", false);
        graph.addEdge("Paris", "New York", false);

        graph.printGraph();
    }

}


class Node {

    public String name;
    ArrayList<String> al;

    public Node(String nodeName) {
        this.name = nodeName;
        this.al = new ArrayList<>();
    }

}

