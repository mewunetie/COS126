import java.awt.Color;
/*
Description: This program reads from two input files (training and testing) and
trains the MultiPerceptron on the training input and returns predicted input for
the testing inputs. It then prints out the incorrect files for the testing file
as well as the error rate.
 */

public class ImageClassifier {
    // This takes an argument (picture) and returns the gray-scale values for the
    // picture.
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();
        int n = width * height;
        double[] grayScaleValues = new double[n];
        int i = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = picture.get(col, row);
                int r = color.getRed();
                grayScaleValues[i] = r;
                i++;
            }
        }
        return grayScaleValues;
    }

    public static void main(String[] args) {
        // Creates an object In, "trainingInput," and reads from a given file
        In trainingInput = new In(args[0]);
        int m = trainingInput.readInt();
        int width = trainingInput.readInt();
        int h = trainingInput.readInt();
        int n = width * h;

        // Creates the multiPerceptron object
        MultiPerceptron multiPerceptron = new MultiPerceptron(m, n);

        // While file isn't empty, reads Strings, which are the labels and
        // shows the pictures based off the string
        while (!trainingInput.isEmpty()) {
            String trainingFileName = trainingInput.readString();
            Picture picture = new Picture(trainingFileName);
            // picture.show();
            int trainingLabel = trainingInput.readInt();
            double[] x = extractFeatures(picture);
            // Trains the multiPerceptron based on the gray-scale values of the
            // pictures
            multiPerceptron.trainMulti(x, trainingLabel);
        }

        // Takes new file from command line and while file isn't empty, reads
        // Strings, which are the labels and shows the pictures based off the string
        In testingInput = new In(args[1]);
        int m2 = testingInput.readInt();
        int width2 = testingInput.readInt();
        int h2 = testingInput.readInt();
        double counter = 0.0;
        double errors = 0.0;
        while (!testingInput.isEmpty()) {
            String testingFileName = testingInput.readString();
            Picture picture2 = new Picture(testingFileName);
            int testingLabel = testingInput.readInt();
            // picture2.show();
            double[] x = extractFeatures(picture2);

            // Predicts values for each testing file, and if value doesn't equal
            // the testing file value, then it prints out the file name with the
            // error and counts an error
            int multi = multiPerceptron.predictMulti(x);
            if (testingLabel != multi) {
                // StdOut.println(testingFileName);
                StdOut.println("label: " + testingLabel);
                StdOut.println("predicted: " + multi);
                errors++;
            }

            counter++;
        }

        // Prints out error rate
        double errorRate = (errors / counter);
        StdOut.println("test error rate = " + errorRate);
    }
}
