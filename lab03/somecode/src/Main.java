import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat image1 = Imgcodecs.imread("cute.jpg");
        Mat image2 = Imgcodecs.imread("dog.jpg");
        if (image1.empty() || image2.empty()) {
            System.out.println("Something went wrong.");
            return;
        }
        //Nie wszystkie obrazy można nałożyć na siebie bez wcześniejszego skalowania, jeśli mają różne rozmiary lub proporcje.

        // Dopasowanie rozmiaru obrazów, jeśli mają różne wymiary
        if (image1.size().width != image2.size().width || image1.size().height != image2.size().height) {
            Size newSize = new Size(Math.min(image1.width(), image2.width()), Math.min(image1.height(), image2.height()));
            Imgproc.resize(image1, image1, newSize);
            Imgproc.resize(image2, image2, newSize);
        }

        // Nakładanie obrazów z przezroczystością
        Mat overlaying = new Mat();
        double alpha = 0.3;  // Waga dla pierwszego obrazu
        double beta = 1.0 - alpha;  // Waga dla drugiego obrazu
        Core.addWeighted(image1, alpha, image2, beta, 0.0, overlaying);

        // Wyświetlenie obrazu wynikowego
        HighGui.imshow("overlaying images", overlaying);
        HighGui.waitKey();
        HighGui.destroyAllWindows();
    }
}
