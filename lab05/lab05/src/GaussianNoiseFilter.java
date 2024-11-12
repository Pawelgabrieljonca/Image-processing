import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

import java.util.Random;
public class GaussianNoiseFilter {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }
        // Dodajemy szum Gaussa do obrazu
        Mat noisyImage = addGaussianNoise(image, 20);
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
        HighGui.imshow("Obraz z Szumem Gaussa", noisyImage);
        HighGui.imshow("Filtr Gaussa", gaussianFiltered);
        HighGui.imshow("Filtr Medianowy", medianFiltered);
        HighGui.imshow("Filtr Bilateralny", bilateralFiltered);
        HighGui.waitKey(0);
        System.exit(0);
    }
    // Metoda dodająca szum Gaussa do obrazu
    public static Mat addGaussianNoise(Mat image, double stdDev) {
        Mat noisyImage = image.clone();
        Mat gaussianNoise = new Mat(image.size(), image.type());
        Random rand = new Random();
        // Generowanie szumu Gaussa
        Core.randn(gaussianNoise, 0, stdDev);
        // Dodanie szumu do oryginalnego obrazu
        Core.add(noisyImage, gaussianNoise, noisyImage);
        return noisyImage;
    }
}
