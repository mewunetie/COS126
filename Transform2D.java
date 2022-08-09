/**
 * Description: This code allows a polygon's vertices to be scaled, translated,
 * copied, or rotated in different ways.
 */
public class Transform2D {
    // Scales the polygon by the factor alpha.
    public static void scale(double[] x, double[] y, double alpha) {
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] * alpha;
            y[i] = y[i] * alpha;
        }
    }

    // Translates the polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy) {
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }
    }

    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array) {
        double[] copy = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    // Rotates the polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta) {
        theta = Math.toRadians(theta);
        for (int i = 0; i < x.length; i++) {
            double[] cx = copy(x);
            double[] cy = copy(y);
            x[i] = (cx[i] * Math.cos(theta)) - (cy[i] * Math.sin(theta));
            y[i] = (cy[i] * Math.cos(theta)) + (cx[i] * Math.sin(theta));
        }
    }

    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {

        // Original polygon
        double[] x = { 0, 1, 1, 0 };
        double[] y = { 0, 0, 2, 1 };

        // Set the x- and y-scale
        StdDraw.setScale(-5.0, 5.0);
        Transform2D.scale(x, y, 0.5);

        // Copy of original polygon
        double[] cx = Transform2D.copy(x);
        double[] cy = Transform2D.copy(y);

        // Rotate, translate and draw the copy
        Transform2D.rotate(cx, cy, -45.0);
        Transform2D.translate(cx, cy, 1.0, 2.0);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(cx, cy);

        // Draw the original polygon
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x, y);


    }
}
