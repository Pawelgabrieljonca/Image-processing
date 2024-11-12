import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
public class CustomFilter {
    public static void processImage() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);

        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }
        // Tworzenie własnego jądra filtra (np. 3x3 filtr rozmycia)
        Mat customKernel = new Mat(3, 3, CvType.CV_32F) {
            {
                put(0, 0, 1.0 / 9);
                put(0, 1, 1.0 / 9);
                put(0, 2, 1.0 / 9);
                put(1, 0, 1.0 / 9);
                put(1, 1, 1.0 / 9);
                put(1, 2, 1.0 / 9);
                put(2, 0, 1.0 / 9);
                put(2, 1, 1.0 / 9);
                put(2, 2, 1.0 / 9);
            }
        };
        // Stworzenie macierzy dla obrazu po przefiltrowaniu
        Mat customFilteredImage = new Mat();
        // Zastosowanie własnego filtra na obrazie
        Imgproc.filter2D(image, customFilteredImage, -1, customKernel);
        // Wyświetlenie oryginalnego obrazu oraz obrazu po zastosowaniu filtra
        HighGui.imshow("Oryginalny Obraz", image);
        HighGui.imshow("Obraz po zastosowaniu własnego filtra", customFilteredImage);
        HighGui.waitKey(0);
        System.exit(0);
    }
    public static void main(String[] args) {
        processImage();
    }
}
