//Given two arrays of integers, the question is to find the longest common ascending subsequence.
package homework_2;

import java.util.Scanner;

public class lcas {

    public static int lcs(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        int[][] dp = new int[n + 1][m + 1];
        int maxLength = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = 1;
                    for (int k = 1; k < i; k++) {
                        for (int l = 1; l < j; l++) {
                            if (a[k - 1] < a[i - 1] && b[l - 1] < b[j - 1]) {
                                dp[i][j] = Math.max(dp[i][j], dp[k][l] + 1);
                            }
                        }
                    }
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs(a, b));

        scanner.close();
    }
}
