import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
public class ImageSharpening {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }
        // Metoda 1: Filtr wyostrzający (proste jądro wyostrzania)
        Mat sharpenedImage1 = new Mat();
        Mat kernel1 = new Mat(3, 3, CvType.CV_32F) {
            {
                put(0, 0, 0);
                put(0, 1, -1);
                put(0, 2, 0);
                put(1, 0, -1);
                put(1, 1, 5);
                put(1, 2, -1);
                put(2, 0, 0);
                put(2, 1, -1);
                put(2, 2, 0);
            }
        };
        Imgproc.filter2D(image, sharpenedImage1, -1, kernel1);
        // Metoda 2: Filtr wysokiej częstotliwości
        Mat sharpenedImage2 = new Mat();
        Mat kernel2 = new Mat(3, 3, CvType.CV_32F) {
            {
                put(0, 0, -1);
                put(0, 1, -1);
                put(0, 2, -1);
                put(1, 0, -1);
                put(1, 1, 9);
                put(1, 2, -1);
                put(2, 0, -1);
                put(2, 1, -1);
                put(2, 2, -1);
            }
        };
        Imgproc.filter2D(image, sharpenedImage2, -1, kernel2);
        // Metoda 3: Wyostrzanie z wykorzystaniem filtru Laplace'a
        Mat laplacianImage = new Mat();
        Imgproc.Laplacian(image, laplacianImage, CvType.CV_8U);
        Mat sharpenedImage3 = new Mat();
        Core.addWeighted(image, 1.5, laplacianImage, -0.5, 0, sharpenedImage3);
        HighGui.imshow("Oryginalny Obraz", image);// Wyświetlenie oryginalnego obrazu i obrazów po wyostrzaniu
        HighGui.imshow("Wyostrzanie - Filtr Wyostrzający", sharpenedImage1);
        HighGui.imshow("Wyostrzanie - Filtr Wysokiej Częstotliwości", sharpenedImage2);
        HighGui.imshow("Wyostrzanie - Filtr Laplace'a", sharpenedImage3);
        HighGui.waitKey(0);
        System.exit(0);
    }
}
