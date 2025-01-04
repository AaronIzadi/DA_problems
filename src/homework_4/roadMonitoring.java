/*The problem is monitoring the roads between cities with an approximation factor of 2.
We have to ensure all roads are covered with minimal personnel while staying within twice the optimal number.*/
package homework_4;

import java.util.*;

public class roadMonitoring {

    private static int vertexCoverApproximation(Map<Integer, List<Integer>> graph, List<int[]> edges) {
        Set<Integer> visited = new HashSet<>();
        int coverCount = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (!visited.contains(u) && !visited.contains(v)) {
                visited.add(u);
                visited.add(v);
                coverCount++;
            }
        }
        return visited.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<int[]> edges = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[]{u, v});
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int result = vertexCoverApproximation(graph, edges);
        System.out.println(result);
    }
}
