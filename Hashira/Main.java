import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Represents a point (x, y)
    static class Point {
        final double x;
        final double y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // Precision for decimal calculations
        MathContext mc = new MathContext(20);

        // List of decoded points
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, Math.round(logBase(4, 10))));    // log_10(4)
        points.add(new Point(2, Math.round(logBase(7, 2))));     // log_2(111 base 2 = 7)
        points.add(new Point(3, Math.round(logBase(12, 10))));   // log_10(12)
        points.add(new Point(6, Math.round(logBase(35, 4))));    // log_4(213 base 4 = 35)

        // Print points
        System.out.println("Interpolating points:");
        for (Point p : points) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }

        // Compute the interpolated value at x = 3 (secret)
        double secretC = lagrangeInterpolate(points, 3.0);

        System.out.println("\nSecret constant (C) at x = 3: " + secretC);
    }

    // Computes log_b(n)
    static double logBase(double n, double base) {
        return Math.log(n) / Math.log(base);
    }

    // Lagrange interpolation
    static double lagrangeInterpolate(List<Point> points, double x) {
        double result = 0.0;

        for (int i = 0; i < points.size(); i++) {
            double term = points.get(i).y;
            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    term *= (x - points.get(j).x) / (points.get(i).x - points.get(j).x);
                }
            }
            result += term;
        }

        return result;
    }
}
