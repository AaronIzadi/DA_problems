/*You are given int n and an array in which arr[i][j] describes the distance between vertices i and j.
The question is to find the minimum number of edges to implement this graph that is describing a field.*/
package homework_3;

import java.util.*;

public class fieldExistence {

    private static boolean isValidPasture(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (matrix[i][j] != matrix[j][i]) {
                        return false;
                    }
                    for (int k = 0; k < n; k++) {
                        if (matrix[i][j] + matrix[j][k] < matrix[i][k]) {
                            return false;
                        }
                    }
                } else if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int calculateMinimumEdges(int[][] matrix, int n) {
        int edgeCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean necessary = true;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j && matrix[i][k] + matrix[k][j] == matrix[i][j]) {
                        necessary = false;
                        break;
                    }
                }
                if (necessary) {
                    edgeCount++;
                }
            }
        }
        return edgeCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] field = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = scanner.nextInt();
            }
        }

        if (!isValidPasture(field, n)) {
            System.out.println(-1);
        } else {
            int minEdges = calculateMinimumEdges(field, n);
            System.out.println(minEdges);
        }
    }
}
