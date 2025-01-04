/*Consider a set of n numbers. The "special number" of this set is defined as the largest number that is divisible by at least half of these numbers.
The job is to print one number – the "special number" corresponding to the input array of numbers.*/
package homework_4;

import java.util.*;

public class specialNumberFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        Map<Integer, Integer> divisorCount = new HashMap<>();
        int half = (n + 1) / 2;
        int result = 1;

        for (int num : numbers) {
            for (int divisor = 1; divisor * divisor <= num; divisor++) {
                if (num % divisor == 0) {
                    divisorCount.put(divisor, divisorCount.getOrDefault(divisor, 0) + 1);
                    if (divisor != num / divisor) {
                        divisorCount.put(num / divisor, divisorCount.getOrDefault(num / divisor, 0) + 1);
                    }
                }
            }
        }

        for (int key : divisorCount.keySet()) {
            if (divisorCount.get(key) >= half) {
                result = Math.max(result, key);
            }
        }

        System.out.println(result);
    }
}
