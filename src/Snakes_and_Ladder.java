import java.util.*;

public class Snakes_and_Ladder {

    static int minimumDiceThrows(int n, int[][] snakes, int[][] ladders) {

        HashMap<Integer, List<Integer>> board = createBoardAdjacencyList(n, snakes, ladders);

        // Do a breadth first search algorithm
        boolean[] visited = new boolean[board.size() + 1]; // We are going to treat this array as 1 indexed
        visited[1] = true;
        int[] dist = new int[board.size() + 1];
        int[] parent = new int[board.size() + 1];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new ArrayDeque<>(board.size() + 1);
        q.add(1);
        parent[1] = 1;
        dist[1] = 0;

        while (!q.isEmpty()) {

            int frontElement = q.remove();
            ArrayList<Integer> ls = (ArrayList<Integer>) board.get(frontElement);

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
        }

        // Print the path from a source (start of the board which is 1) to the end of the board
        int destination = n;
        int count = 0;
        if (destination != -1) {
            System.out.println("Shortest path: ");
            int temp = destination;
            while (temp != 1) {
                System.out.print(temp + " --- ");
                temp = parent[temp];
                count++;
            }
            System.out.println(temp);
        }

        return count;

    }

    static HashMap<Integer, List<Integer>> createBoardAdjacencyList(int n, int[][] snakes, int[][] ladders) {

        // Create a new hashmap to store the graph of the board
        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        for (int i = 1; i <= n; i++) {

            ArrayList<Integer> al = new ArrayList<>();

            // I am assuming the board configuration is valid: i.e. there cannot be a snake and a ladder at the same square
            int snake = isPresentInSnakesArray(i, snakes);
            int ladder = isPresentInLaddersArray(i, ladders);

            if (snake != -1) {
                al.add(snake);
                hm.put(i, al);
            }

            if (ladder != -1) {
                al.add(ladder);
                hm.put(i, al);
            }

            if (snake == -1 && ladder == -1) {

                // If this square does not have a ladder or a snake, then from this square qe can reach the next six squares
                int j = i + 1;
                int count = 0;
                while (j <= n && count <= 5) {

                    // Check if from the following squares there is a snake or a ladder
                    int isSnake = isPresentInSnakesArray(j, snakes);
                    int isLadder = isPresentInLaddersArray(j, ladders);

                    if (isSnake != -1) {
                        if (!al.contains(isSnake)) al.add(isSnake);
                    } else if (isLadder != -1) {
                        if (!al.contains(isLadder)) al.add(isLadder);
                    } else {
                        if (!al.contains(j)) al.add(j);
                    }

                    j++;
                    count++;
                }
                hm.put(i, al);

            }
        }

        System.out.println("Adjacency list of the board: ");
        System.out.println(hm);
        return hm;
    }

    static int isPresentInSnakesArray(int n, int[][] snakes) {

        for (int[] array: snakes) {
            if (array[0] == n) {
                // Return the destination square since this square contains a snake that leads to a lower square
                return array[1];
            }
        }
        return -1; // This square contains no snake
    }

    static int isPresentInLaddersArray(int n, int[][] ladders) {

        for (int[] array: ladders) {
            if (array[0] == n) {
                // Return the destination square since this square contains a snake that leads to a lower square
                return array[1];
            }
        }
        return -1; // This square contains no snake
    }

    public static void main(String[] args) {

        int n = 36;
        int[][] ladders = { {2, 15}, {5, 7}, {9, 27}, {18, 29}, {25, 35} };
        int[][] snakes = { {17, 4}, {20, 6}, {34, 12}, {24, 16}, {32, 30} };

       System.out.println("Minimum number of dice throws: " +  minimumDiceThrows(n, snakes, ladders));

        int n1 = 36;
        int[][] ladders1 = { {3, 16}, {15, 25}, {21, 32} };
        int[][] snakes1 = { {12, 2}, {30, 4}, {35, 22} };

        System.out.println("Minimum number of dice throws: " +  minimumDiceThrows(n1, snakes1, ladders1));

    }

}
