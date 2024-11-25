import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

public class EdgeReduction {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("geralt.jpg", Imgcodecs.IMREAD_GRAYSCALE); // Wczytaj obraz w skali szarości
        if (src.empty()) { // Sprawdź, czy obraz został poprawnie wczytany
            System.out.println("Failed to load image.");
            return;
        }
        Mat blurred = new Mat();
        Imgproc.GaussianBlur(src, blurred, new Size(5, 5), 0); // Rozmyj obraz filtrem Gaussowskim (usuwa szumy)

        Mat gradX = new Mat(), gradY = new Mat(), absGradX = new Mat(), absGradY = new Mat();
        Imgproc.Sobel(blurred, gradX, CvType.CV_16S, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT); // Oblicz gradient w kierunku X
        Imgproc.Sobel(blurred, gradY, CvType.CV_16S, 0, 1, 3, 1, 0, Core.BORDER_DEFAULT); // Oblicz gradient w kierunku Y

        Core.convertScaleAbs(gradX, absGradX); // Konwertuj gradient X na wartości bezwzględne (CV_8U)
        Core.convertScaleAbs(gradY, absGradY); // Konwertuj gradient Y na wartości bezwzględne (CV_8U)

        Mat edges = new Mat();
        Core.addWeighted(absGradX, 0.4, absGradY, 0.6, 0, edges); // Połącz gradienty z wagami: X=0.4, Y=0.6

        Mat thresholded = new Mat();
        Imgproc.threshold(edges, thresholded, 50, 255, Imgproc.THRESH_BINARY); // Odrzuć słabsze krawędzie (progowanie)

        if (Imgcodecs.imwrite("ReducedEdges.jpg", thresholded)) { // Zapisz wynikowy obraz
            System.out.println("Result saved as ReducedEdges.jpg");
        } else {
            System.out.println("Failed to save result.");
        }
    }
}
