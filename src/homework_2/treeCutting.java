/*There are n trees with position x and height h, we can cut them if either [x,x+h] or [x-h,x] is empty for the tree to fall.
The problem is to find the maximum number of trees that can be cut.*/
package homework_2;

import java.util.Scanner;

public class treeCutting {

    public static int maxCutTrees(int n, int[] x, int[] h) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1];
            if (x[i] - h[i] > x[i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
            if (i == n - 1 || x[i] + h[i] < x[i + 1]) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] position = new int[n];
        int[] height = new int[n];

        for (int i = 0; i < n; i++) {
            position[i] = scanner.nextInt();
            height[i] = scanner.nextInt();
        }
        System.out.println(maxCutTrees(n, position, height));
    }
}
