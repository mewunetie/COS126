import java.awt.Color;

/**
 * Description: Draws squares recursively, and stopping when the command-line
 * integer input in is 0. New squares' centers take on the vertex of the corners
 * of the previous square.
 */
public class Art {
    // draws filled squares and outlines them
    private static void filledSquare(double x, double y, double hLength) {
        double halfLength = hLength / 2;
        StdDraw.filledSquare(x, y, halfLength);
        StdDraw.setPenColor(Color.black);
        StdDraw.square(x, y, halfLength);

    }

    // draws squares recursively
    private static void recursiveSquares(int n, double x, double y, double hLength) {
        if (n == 0) return;
        double halfLength = hLength / 2;
        filledSquare(x, y, hLength);

        // takes in color pink when n is even
        // n is subtracted by two when drawing upper-right and lower-left squares
        // again at the end
        if (n % 2 == 0) {
            StdDraw.setPenColor(Color.pink);
            // draws upper-right square
            recursiveSquares(n - 1, x + halfLength, y + halfLength, halfLength);
            // draws upper-left square
            recursiveSquares(n - 1, x - halfLength, y + halfLength, halfLength);
            // draws lower-right square
            recursiveSquares(n - 1, x + halfLength, y - halfLength, halfLength);
            // draws lower-left square
            recursiveSquares(n - 1, x - halfLength, y - halfLength, halfLength);
            // draws upper-right square
            recursiveSquares(n - 2, x + halfLength, y + halfLength, halfLength);
            // draws lower-left square
            recursiveSquares(n - 2, x - halfLength, y - halfLength, halfLength);
        }
        // takes in color cyan for all other n-values
        else {
            StdDraw.setPenColor(Color.cyan);
            // draws upper-right square
            recursiveSquares(n - 1, x + halfLength, y + halfLength, halfLength);
            // draws upper-left square
            recursiveSquares(n - 1, x - halfLength, y + halfLength, halfLength);
            // draws lower-right square
            recursiveSquares(n - 1, x + halfLength, y - halfLength, halfLength);
            // draws lower-left square
            recursiveSquares(n - 1, x - halfLength, y - halfLength, halfLength);
        }

    }

    // calls recursiveSquares in main method
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        recursiveSquares(n, 0.5, 0.5, 0.2);
    }
}
