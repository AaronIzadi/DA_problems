/*You are given three integers; n as number of cities, m as number of roads and q as number of source cities.
In the next m lines, you are given three integers u, v and l such that the u and v cities are connected through a 2-way road of length l.
Afterward, in the next q lines, you are given q_i's such that each presents a source city.
For each source city(s), print out s d t such that d is the distance between s and the farthest city from it, and t represents the number of cities that have the distance d from s.*/
package homework_3;

import java.util.*;

public class TAEscape {

    static class Edge implements Comparable<Edge> {
        int node;
        int distance;

        Edge(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] dijkstra(int n, List<List<Edge>> adj, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.node;
            int currentDist = current.distance;

            if (currentDist > dist[u]) {
                continue;
            }

            for (Edge neighbor : adj.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.distance;
                int newDist = currentDist + weight;

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new Edge(v, newDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int Q = sc.nextInt();

        List<List<Edge>> adjacent = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacent.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int l = sc.nextInt();
            adjacent.get(u).add(new Edge(v, l));
            adjacent.get(v).add(new Edge(u, l));
        }

        for (int q = 0; q < Q; q++) {
            int start = sc.nextInt();

            int[] distance = dijkstra(n, adjacent, start);

            int maxDistance = -1;
            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (distance[i] != Integer.MAX_VALUE) {
                    if (distance[i] > maxDistance) {
                        maxDistance = distance[i];
                        count = 1;
                    } else if (distance[i] == maxDistance) {
                        count++;
                    }
                }
            }

            if (maxDistance == -1) {
                System.out.println(start + " 0 1");
            } else {
                System.out.println(start + " " + maxDistance + " " + count);
            }
        }

        sc.close();
    }
}
