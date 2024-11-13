import java.util.Scanner;

public class gardenTransformation {

    public static int minTransformationSteps(int[] types) {
        int n = types.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = dp[i][j - 1] + 1;

                for (int k = i; k < j; k++) {
                    if (types[k] == types[j]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j - 1]);
                    }
                }
            }
        }

        return dp[0][n - 1] - 1;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] types = new int[n];
            for (int i = 0; i < n; i++) {
                types[i] = scanner.nextInt();
            }

            System.out.println(minTransformationSteps(types));
        }
    }
}
