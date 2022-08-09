public class NBodyFinal {
    public static void main(String[] args) {
        // Step 1. Parse command-line arguments.
        double stoppingTime = Double.parseDouble(args[0]);
        double changeInTime = Double.parseDouble(args[1]);

        // Step 2. Read universe from standard input.
        int n = StdIn.readInt();
        double radius = StdIn.readDouble();
        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }
        // Step 3. Initialize standard drawing.
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        // Step 4. Play music on standard audio.
        StdAudio.play("2001.wav");

        // Step 5. Simulate the universe.
        for (double t = 0.0; t < stoppingTime; t += changeInTime) {
            // StdOut.println("t = " + t);
            double[] fx = new double[n];
            double[] fy = new double[n];
            // Step 5A. Calculate net forces.
            for (int i = 0; i < n; i++) {
                fx[i] = 0;
                fy[i] = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double GRAVITATIONAL_CONSTANT = 6.67e-11;
                        double num = ((GRAVITATIONAL_CONSTANT) * mass[i] * mass[j]);
                        double changeOfX = px[j] - px[i];
                        double changeOfY = py[j] - py[i];
                        int POWER = 2;
                        double powerOfX = Math.pow(changeOfX, POWER);
                        double powerOfY = Math.pow(changeOfY, POWER);
                        double distance = powerOfX + powerOfY;
                        double r = Math.sqrt(distance);
                        double denominator = Math.pow(r, POWER);
                        double force = num / denominator;
                        fx[i] = force * (changeOfX / r);
                        fy[i] = force * (changeOfY / r);
                        fx[i] += fx[i];
                        fy[i] += fy[i];
                    }

                }
                for (int a = 0; a < n; a++) {
                    double ax = fx[i] / mass[i];
                    double ay = fy[i] / mass[i];
                    vx[a] = vx[a] + (ax * changeInTime);
                    vy[a] = vy[a] + (ay * changeInTime);
                    px[a] = px[a] + (vx[a] * changeInTime);
                    py[a] = py[a] + (vy[a] * changeInTime);

                }
            }

            // Step 5B. Update velocities and positions.


            // Step 5C. Draw universe to standard drawing.
            StdDraw.picture(0, 0, "starfield.jpg");

            for (int a = 0; a < n; a++) {
                StdDraw.picture(px[a], py[a], image[a]);
            }

            StdDraw.show();
            StdDraw.pause(40);
        }

        // Step 6. Print universe to standard output.
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }
    }
}



