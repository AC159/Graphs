import java.util.ArrayList;
import java.util.Collections;

public class LargestIsland {

    static int maxAreaHelper(int[][] grid, boolean[][] visited, int n, int m, int area) {

        // Recur on all neighbors in all four directions (up/down, left/right)
        visited[n][m] = true;

        // Check if node has a neighbor above it
        if (n - 1 >= 0 && grid[n-1][m] == 1 && !visited[n-1][m]) {
            area++;
            area = maxAreaHelper(grid, visited, n - 1, m, area);
        }

        // Check if node has a neighbor below it
        if (n + 1 < grid.length && grid[n+1][m] == 1 && !visited[n+1][m]) {
            area++;
            area = maxAreaHelper(grid, visited, n + 1, m, area);
        }

        // Check if node has a neighbor to its right
        if (m + 1 < grid[n].length && grid[n][m+1] == 1 && !visited[n][m+1]) {
            area++;
            area = maxAreaHelper(grid, visited, n, m + 1, area);
        }

        // Check if node has a neighbor to its left
        if (m - 1 >= 0 && grid[n][m-1] == 1 && !visited[n][m-1]) {
            area++;
            area = maxAreaHelper(grid, visited, n, m - 1, area);
        }

        return area;
    }

    static int maxAreaOfIsland(int[][] grid) {

        // Create a new boolean array which will hold all the visited values
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        ArrayList<Integer> al = new ArrayList<>();

        // Depth first search problem

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = maxAreaHelper(grid, visited, i, j, 1);
                    al.add(area);
                }
            }
        }

        System.out.println("Here are the areas found in the grid: ");
        System.out.println(al);

        // Sort the ArrayList and return the highest area
        Collections.sort(al);
        System.out.println("Sorted areas found in the grid: ");
        System.out.println(al);

        return al.get(al.size() - 1);

    }

    public static void main(String[] args) {

        // 4-way DFS problem
        int[][] grid = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0}
            };

        System.out.println("Maximum area found in the grid: " + maxAreaOfIsland(grid));

    }

}
