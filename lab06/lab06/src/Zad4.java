import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class Zad4 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat image = Imgcodecs.imread("geralt.jpg");
        if (image.empty()) {
            System.out.println("Nie udało się wczytać obrazu.");
            return;
        }
        Mat blurred = new Mat();
        Mat edges = new Mat();

        Imgproc.GaussianBlur(image, blurred, new Size(5, 5), 1.5);
        Imgproc.Canny(blurred, edges, 50, 150);

        HighGui.imshow("Original Image", image);
        HighGui.imshow("Edges Reduced", edges);
        HighGui.waitKey();
    }
}
