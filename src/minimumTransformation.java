//There are two types of transformation for numbers, either multiply by 2 or minus 1.
//Given two integers, the  problem is to find the minimum number of operations to transform integer one to integer two.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class minimumTransformation {

    public static int minTransformations(int n, int m) {
        // Base case
        if (n >= m) {
            return n - m;
        }

        int minNumTransformation = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[m * 2 + 1];

        queue.offer(n);
        visited[n] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // Check if we reached the target
                if (current == m) {
                    return minNumTransformation;
                }

                // Blue transformation: current * 2
                if (current < m && !visited[current * 2]) {
                    queue.offer(current * 2);
                    visited[current * 2] = true;
                }

                // Red transformation: current - 1
                if (current > 1 && !visited[current - 1]) {
                    queue.offer(current - 1);
                    visited[current - 1] = true;
                }
            }
            minNumTransformation++;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println(minTransformations(n, m));
    }
}