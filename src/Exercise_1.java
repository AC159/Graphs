import java.util.HashMap;

public class Exercise_1 {

    static int findCenterOfStarGraph(int[][] edges) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int[] edge : edges) {

            if (hm.get(edge[0]) == null) {
                hm.putIfAbsent(edge[0], 1);
            } else {
                hm.put(edge[0], hm.get(edge[0]) + 1);
            }

            if (hm.get(edge[1]) == null) {
                hm.putIfAbsent(edge[1], 1);
            } else {
                hm.put(edge[1], hm.get(edge[1]) + 1);
            }
        }

        System.out.println(hm);
        int highest = hm.get(edges[0][0]);
        for (int key : hm.keySet()) {
            if (hm.get(key) > hm.get(highest)) highest = key;
        }
        return highest;

    }

    public static void main(String[] args) {

        int[][] edges = { { 1, 2 }, { 2, 3 }, { 4, 2 } };
        System.out.println(findCenterOfStarGraph(edges));

    }

}
