import org.opencv.core.Core;
import org.opencv.core.Mat;

import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

import java.util.Random;
public class SaltAndPepperNoise {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }
        // Dodajemy szum typu "salt and pepper" do obrazu
        Mat noisyImage = addSaltAndPepperNoise(image, 0.05);
        // Stosujemy filtr Gaussa
        Mat gaussianFiltered = new Mat();
        Imgproc.GaussianBlur(noisyImage, gaussianFiltered, new Size(5, 5), 0);
        // Stosujemy filtr medianowy
        Mat medianFiltered = new Mat();
        Imgproc.medianBlur(noisyImage, medianFiltered, 5);
        // Stosujemy filtr bilateralny
        Mat bilateralFiltered = new Mat();
        Imgproc.bilateralFilter(noisyImage, bilateralFiltered, 9, 75, 75);
        // Wyświetlenie wyników
        HighGui.imshow("Oryginalny Obraz", image);
        HighGui.imshow("Obraz z Szumem Salt and Pepper", noisyImage);
        HighGui.imshow("Filtr Gaussa", gaussianFiltered);
        HighGui.imshow("Filtr Medianowy", medianFiltered);
        HighGui.imshow("Filtr Bilateralny", bilateralFiltered);

        HighGui.waitKey(0);
        System.exit(0);
    }
    // Metoda dodająca szum "salt and pepper" do obrazu
    public static Mat addSaltAndPepperNoise(Mat image, double noiseRatio) {
        Mat noisyImage = image.clone();
        Random rand = new Random();
        int totalPixels = (int) (noiseRatio * image.rows() * image.cols());
        for (int i = 0; i < totalPixels; i++) {
            int row = rand.nextInt(image.rows());
            int col = rand.nextInt(image.cols());
            // Ustawiamy biały lub czarny piksel (szum)
            double[] color = rand.nextBoolean() ? new double[]{255, 255, 255} : new double[]{0, 0, 0};
            noisyImage.put(row, col, color);
        }
        return noisyImage;
    }
}
