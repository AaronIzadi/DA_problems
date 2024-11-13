//Given a 2D table, the problem is to find if there exists a specific word in the table.

import java.util.Scanner;

public class tableSearch {

    public static boolean exist(char[][] table, String word) {
        if (table == null || table.length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int m = table.length;
        int n = table[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(table, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean dfs(char[][] board, String word, int m, int n, int index) {
        if (index == word.length()) {
            return true;
        }

        if (m < 0 || m >= board.length || n < 0 || n >= board[0].length || board[m][n] != word.charAt(index)) {
            return false;
        }

        char dummy = board[m][n];
        board[m][n] = '*';

        boolean found = dfs(board, word, m + 1, n, index + 1) ||
                dfs(board, word, m - 1, n, index + 1) ||
                dfs(board, word, m, n + 1, index + 1) ||
                dfs(board, word, m, n - 1, index + 1);

        board[m][n] = dummy;

        return found;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();

        char[][] table = new char[m][n];

        for (int i = 0; i < m; i++) {
            String dummy = scanner.nextLine();
            int counter = 0;

            for (int j = 0; j < n; j++) {
                if (j < dummy.length()) {
                    table[i][j] = dummy.charAt(counter);
                    counter += 2;
                }
            }
        }

        String word = scanner.next();

        String answer = exist(table, word) ? "YES" : "NO";
        System.out.println(answer);
    }
}