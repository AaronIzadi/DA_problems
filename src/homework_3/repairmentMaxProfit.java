//Given the number of vertices and the edges between them, you have to find the maximum product of the two longest paths that have no vertex in common
package homework_3;

import java.util.*;

public class repairmentMaxProfit {

    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int maxDistance, farthestNode;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            addEdge(scanner.nextInt() - 1, scanner.nextInt() - 1);
        }
        List<int[]> edges = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            for (int v : tree.get(u)) {
                if (u < v) {
                    edges.add(new int[]{u, v});
                }
            }
        }

        int maxProduct = 0;
        int product = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            removeEdge(u, v);

            visited = new boolean[n];
            maxDistance = 0;
            farthestNode = u;
            dfs(u, 0);
            visited = new boolean[n];
            maxDistance = 0;
            dfs(farthestNode, 0);
            int diameter1 = maxDistance;

            visited = new boolean[n];
            maxDistance = 0;
            farthestNode = v;
            dfs(v, 0);
            visited = new boolean[n];
            maxDistance = 0;
            dfs(farthestNode, 0);
            int diameter2 = maxDistance;

            product = diameter1 * diameter2;
            maxProduct = Math.max(maxProduct, product);

            addEdge(u, v);
        }
        System.out.println(maxProduct);
    }

    static void addEdge(int u, int v) {
        tree.get(u).add(v);
        tree.get(v).add(u);
    }

    static void removeEdge(int u, int v) {
        tree.get(u).remove((Integer) v);
        tree.get(v).remove((Integer) u);
    }

    static void dfs(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (int neighbor : tree.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, distance + 1);
            }
        }
    }
}