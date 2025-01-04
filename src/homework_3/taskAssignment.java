/*There are multiple jobs and some number of teacher assistants.
The hardness of the i-th job is h_i.
The integer s_i,j represents the j_th TA at i_th job.( It means that if this TA wants to complete this job, he has to spend h_i/s_i,j time and becomes exhausted h_i units.)
And also, j_th TA has the maximum capacity of m_j.(To get exhausted.)
Note that jobs can be done partially.
Our problem is to find the minimum time that is needed for the jobs to be done.*/
package homework_3;

import java.util.*;

public class taskAssignment {

    static class Edge {
        int to;
        int capacity;
        double cost;
        int reverse;

        Edge(int to, int capacity, double cost, int reverse) {
            this.to = to;
            this.capacity = capacity;
            this.cost = cost;
            this.reverse = reverse;
        }
    }

    static class MinCostMaxFlow {

        List<Edge>[] graph;
        int n;
        static final double EPS = 1e-10;

        MinCostMaxFlow(int n) {
            this.n = n;
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void addEdge(int from, int to, int capacity, double cost) {
            graph[from].add(new Edge(to, capacity, cost, graph[to].size()));
            graph[to].add(new Edge(from, 0, -cost, graph[from].size() - 1));
        }

        double minCostMaxFlow(int source, int sink) {
            double totalCost = 0;
            int[] parent = new int[n];
            Edge[] parentEdge = new Edge[n];
            double[] dist = new double[n];

            while (true) {
                Arrays.fill(dist, Double.MAX_VALUE);
                dist[source] = 0;
                PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> dist[o]));
                queue.add(source);

                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (Edge e : graph[u]) {
                        if (e.capacity > 0 && dist[u] + e.cost < dist[e.to] - EPS) {
                            dist[e.to] = dist[u] + e.cost;
                            parent[e.to] = u;
                            parentEdge[e.to] = e;
                            queue.add(e.to);
                        }
                    }
                }

                if (dist[sink] == Double.MAX_VALUE) {
                    break;
                }

                int flow = Integer.MAX_VALUE;
                for (int u = sink; u != source; u = parent[u]) {
                    flow = Math.min(flow, parentEdge[u].capacity);
                }

                for (int u = sink; u != source; u = parent[u]) {
                    Edge e = parentEdge[u];
                    e.capacity -= flow;
                    graph[e.to].get(e.reverse).capacity += flow;
                    totalCost += flow * e.cost;
                }
            }

            return totalCost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int[] h = new int[k];
        for (int i = 0; i < k; i++) {
            h[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int[] m = new int[n];
        for (int j = 0; j < n; j++) {
            m[j] = scanner.nextInt();
        }

        double[][] s = new double[k][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = scanner.nextDouble();
            }
        }

        int source = k + n;
        int sink = source + 1;
        MinCostMaxFlow flow = new MinCostMaxFlow(sink + 1);

        for (int i = 0; i < k; i++) {
            flow.addEdge(source, i, h[i], 0);
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                double cost = 1.0 / s[i][j];
                flow.addEdge(i, k + j, Integer.MAX_VALUE, cost);
            }
        }

        for (int j = 0; j < n; j++) {
            flow.addEdge(k + j, sink, m[j], 0);
        }

        System.out.println(flow.minCostMaxFlow(source, sink));
    }
}
