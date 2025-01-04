//Reduction is possible to a string if there exists substring of "BB" or "AB". The problem is to find the length of the string after reduction.
package homework_2;

import java.util.Scanner;

public class stringReduction {

    public static int reduceString(String string) {
        int n = string.length();
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            if ((string.charAt(i - 1) == 'A' && string.charAt(i) == 'B') || (string.charAt(i - 1) == 'B' && string.charAt(i) == 'B')) {
                dp[i] = (i >= 2) ? dp[i - 2] : 0;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            strings[i] = scanner.next();
        }

        for (int i = 0; i < n; i++) {
            System.out.println(reduceString(strings[i]));
        }
    }

}
