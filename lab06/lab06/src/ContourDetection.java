import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

public class ContourDetection {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Załaduj bibliotekę OpenCV
        // Wczytaj obraz w skali szarości
        Mat src = Imgcodecs.imread("fig.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        if (src.empty()) {
            System.out.println("Failed to load image.");
            return;
        }
        // 1. Wygładzenie obrazu za pomocą filtra Gaussowskiego
        Mat blurred = new Mat();
        Imgproc.GaussianBlur(src, blurred, new Size(5, 5), 0);

        // 2. Binaryzacja obrazu
        Mat binary = new Mat();
        Imgproc.threshold(blurred, binary, 100, 255, Imgproc.THRESH_BINARY);

        // 3. Wykrywanie krawędzi metodą Canny
        Mat edges = new Mat();
        Imgproc.Canny(binary, edges, 50, 150);

        // 4. Wykrywanie konturów
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        // 5. Narysowanie konturów na kolorowym obrazie
        Mat output = Imgcodecs.imread("fig.jpg"); // Wczytaj oryginalny obraz w kolorze
        Imgproc.drawContours(output, contours, -1, new Scalar(0, 0, 255), 2); // Kontury na czerwono

        // Zapisz wynikowy obraz
        if (Imgcodecs.imwrite("contour.jpg", output)) {
            System.out.println("Contours detected and saved as Contours.jpg");
        } else {
            System.out.println("Failed to save result.");
        }
    }
}
