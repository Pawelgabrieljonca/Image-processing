import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

public class CannyEdgeDetection {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Wczytaj obraz
        Mat src = Imgcodecs.imread("geralt.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        if (src.empty()) {
            System.out.println("Failed to load image.");
            return;
        }
        // Wykonaj detekcję krawędzi Canny
        Mat edges = new Mat();
        Imgproc.Canny(src, edges, 100, 200);
        Imgcodecs.imwrite("CannyEdge.jpg", edges);
    }
}
