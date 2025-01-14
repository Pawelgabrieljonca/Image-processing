import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

public class Zad1 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("geralt.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        if (src.empty()) {
            System.out.println("Failed to load image.");
            return;
        }
        Mat edges = new Mat();
        Imgproc.Canny(src, edges, 100, 200);

        HighGui.imshow("CannyEdge.jpg", edges);
        HighGui.imshow("Orginal image", src);
        HighGui.waitKey(0);
    }
}
