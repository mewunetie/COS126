/**
 * Description: Draws a Sierpinski triangle using a command-line integer input,
 * n. The n specifies to what order the Sierpinski triangle should go to.
 */
public class Sierpinski {

    //  Height of an equilateral triangle whose sides are of the specified length.
    public static double height(double length) {
        return (length * Math.sqrt(3)) / 2;
    }

    //  Draws a filled equilateral triangle whose bottom vertex is (x, y)
    //  of the specified side length.
    public static void filledTriangle(double x, double y, double length) {
        double newLength = length / 2;
        double h = height(length);
        double upperRightX = x + (newLength);
        double upperLeftX = x - (newLength);
        double[] dx = { x, upperLeftX, upperRightX };
        double[] dy = { y, y + h, y + h };
        StdDraw.filledPolygon(dx, dy);
    }

    //  Draws a Sierpinski triangle of order n, such that the largest filled
    //  triangle has bottom vertex (x, y) and sides of the specified length.
    public static void sierpinski(int n, double x, double y, double length) {
        if (n == 0) return;
        filledTriangle(x, y, length);

        double newLength = length / 2;
        double h = height(length);

        double x0 = x + (newLength);
        double x1 = x - (newLength);
        double y0 = y + h;

        sierpinski(n - 1, x, y0, newLength); // upper triangle
        sierpinski(n - 1, x0, y, newLength); // right-hand triangle
        sierpinski(n - 1, x1, y, newLength); // left-hand triangle
    }

    //  Takes an integer command-line argument n;
    //  draws the outline of an equilateral triangle (pointed upwards) of length 1;
    //  whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and
    //  draws a Sierpinski triangle of order n that fits snugly inside the outline.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double h = height(1.0);
        StdDraw.line(1, 0, 0, 0);
        StdDraw.line(0, 0, 0.5, h);
        StdDraw.line(1, 0, 0.5, h);
        sierpinski(n, 0.5, 0, 0.5);
    }
}
