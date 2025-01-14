import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
import java.util.ArrayList;
import java.util.List;
public class Zad5 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("images.png");
        if (image.empty()) {
            System.out.println("Nie udało się wczytać obrazu.");
            return;
        }
        Mat gray = new Mat();
        Mat binary = new Mat();
        Mat edges = new Mat();
        Mat contourOutput = new Mat();

        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(gray, binary, 128, 255, Imgproc.THRESH_BINARY);
        Imgproc.Canny(binary, edges, 100, 200);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        contourOutput = Mat.zeros(edges.size(), CvType.CV_8UC3);
        for (int i = 0; i < contours.size(); i++) {
            Imgproc.drawContours(contourOutput, contours, i, new Scalar(0, 255, 0), 2);
        }

        HighGui.imshow("Original Image", image);
        HighGui.imshow("Binary Image", binary);
        HighGui.imshow("Edges", edges);
        HighGui.imshow("Contours", contourOutput);
        HighGui.waitKey();
    }
}
