//The question is to find the two points which have the shortest distance.

import java.util.Comparator;
import java.util.Scanner;

public class closestPairOfPoints {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class PairOfPoints {
        Point point1, point2;
        double distance;

        PairOfPoints(Point point1, Point point2, double distance) {
            this.point1 = point1;
            this.point2 = point2;
            this.distance = distance;
        }

        void print() {
            if (point1 != null && point2 != null) {
                if (point1.x < point2.x) {
                    System.out.println(point1.x + " " + point1.y + " " + point2.x + " " + point2.y);
                } else {
                    System.out.println(point2.x + " " + point2.y + " " + point1.x + " " + point1.y);
                }
            } else {
                System.out.println("No closest pair found.");
            }
        }
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

    static PairOfPoints min(PairOfPoints r1, PairOfPoints r2) {
        return (r1.distance < r2.distance) ? r1 : r2;
    }

    static PairOfPoints shortestDistance(Point[] points, int size) {
        PairOfPoints minPairOfPointsDistance = new PairOfPoints(null, null, Double.MAX_VALUE);

        mergeSort(points, 0, size - 1, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (points[j].y - points[i].y) < minPairOfPointsDistance.distance; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minPairOfPointsDistance.distance) {
                    minPairOfPointsDistance = new PairOfPoints(points[i], points[j], dist);
                }
            }
        }

        return minPairOfPointsDistance;
    }

    static PairOfPoints closestUtil(Point[] points, int left, int right) {
        if (right - left <= 0) {
            return new PairOfPoints(null, null, Double.MAX_VALUE);
        }

        if (right - left == 1) {
            return new PairOfPoints(points[left], points[right], distance(points[left], points[right]));
        }

        int mid = left + (right - left) / 2;
        Point midPoint = points[mid];

        PairOfPoints leftPairOfPoints = closestUtil(points, left, mid);
        PairOfPoints rightPairOfPoints = closestUtil(points, mid + 1, right);
        PairOfPoints minPairOfPoints = min(leftPairOfPoints, rightPairOfPoints);

        Point[] strip = new Point[right - left + 1];
        int j = 0;

        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < minPairOfPoints.distance) {
                strip[j++] = points[i];
            }
        }

        return min(minPairOfPoints, shortestDistance(strip, j));
    }

    static void mergeSort(Point[] points, int left, int right, Comparator<Point> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(points, left, mid, comparator);
            mergeSort(points, mid + 1, right, comparator);

            merge(points, left, mid, right, comparator);
        }
    }

    static void merge(Point[] points, int left, int mid, int right, Comparator<Point> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Point[] leftArray = new Point[n1];
        Point[] rightArray = new Point[n2];

        System.arraycopy(points, left, leftArray, 0, n1);
        System.arraycopy(points, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                points[k++] = leftArray[i++];
            } else {
                points[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            points[k++] = leftArray[i++];
        }

        while (j < n2) {
            points[k++] = rightArray[j++];
        }
    }

    static PairOfPoints closest(Point[] points) {
        mergeSort(points, 0, points.length - 1,
                Comparator.comparingInt(p -> p.x));

        return closestUtil(points, 0, points.length - 1);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }

        PairOfPoints closestPair = closest(points);
        closestPair.print();
        scanner.close();
    }
}