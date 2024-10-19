import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  // Załadowanie biblioteki OpenCV
        Mat image = Imgcodecs.imread("dog.jpg");  // Wczytanie "dog.jpg" do obiektu typu Mat (macierzy)

        if (image.empty()) {
            System.out.println("Mission failed");
            return;
        }

        // Pomniejszanie obrazu za pomocą resize
        Mat resizedHalf = new Mat();
        Mat resizedQuarter = new Mat();
        Imgproc.resize(image, resizedHalf, new Size(image.cols() / 2, image.rows() / 2), 0, 0, Imgproc.INTER_LINEAR);
        Imgproc.resize(image, resizedQuarter, new Size(image.cols() / 4, image.rows() / 4), 0, 0, Imgproc.INTER_LINEAR);

        // Zapisywanie pomniejszonych obrazów za pomocą resize
        Imgcodecs.imwrite("resized_half.jpg", resizedHalf);
        Imgcodecs.imwrite("resized_quarter.jpg", resizedQuarter);

        // Pomniejszanie obrazu za pomocą pyrDown
        Mat pyrDownHalf = new Mat();
        Mat pyrDownQuarter = new Mat();
        Imgproc.pyrDown(image, pyrDownHalf, new Size(image.cols() / 2, image.rows() / 2));
        Imgproc.pyrDown(pyrDownHalf, pyrDownQuarter, new Size(image.cols() / 4, image.rows() / 4));

        // Zapisywanie pomniejszonych obrazów za pomocą pyrDown
        Imgcodecs.imwrite("pyrdown2.jpg", pyrDownHalf);
        Imgcodecs.imwrite("pyrdown4.jpg", pyrDownQuarter);

    }
}
