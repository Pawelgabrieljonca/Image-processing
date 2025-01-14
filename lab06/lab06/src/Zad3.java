import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
public class Zad3 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("geralt.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        Mat sobelX = new Mat();
        Mat sobelY = new Mat();
        Mat sobel = new Mat();

        Imgproc.Sobel(image, sobelX, CvType.CV_64F, 1, 0);
        Imgproc.Sobel(image, sobelY, CvType.CV_64F, 0, 1);
        Core.magnitude(sobelX, sobelY, sobel);

        Mat sobelConverted = new Mat();
        sobel.convertTo(sobelConverted, CvType.CV_8U);

        HighGui.imshow("Original Image", image);
        HighGui.imshow("Sobel Edge Detection", sobelConverted);
        HighGui.waitKey(0);
    }
}
