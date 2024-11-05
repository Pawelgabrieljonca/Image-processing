import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class Gauss {

    public static void processImage() {
        // Załaduj bibliotekę OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);

        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }

        // Tworzenie obiektów Mat dla przetworzonych obrazów
        Mat blurredImage1 = new Mat();
        Mat blurredImage2 = new Mat();
        Mat blurredImage3 = new Mat();

        // Rozmycie Gaussa dla trzech różnych rozmiarów filtra
        Imgproc.GaussianBlur(image, blurredImage1, new Size(3, 3), 0);
        Imgproc.GaussianBlur(image, blurredImage2, new Size(7, 7), 0);
        Imgproc.GaussianBlur(image, blurredImage3, new Size(15, 15), 0);

        // Wyświetl oryginalny obraz oraz obrazy po rozmyciu
        HighGui.imshow("Oryginalny Obraz", image);
        HighGui.imshow("Rozmycie Gaussa - 3x3", blurredImage1);
        HighGui.imshow("Rozmycie Gaussa - 7x7", blurredImage2);
        HighGui.imshow("Rozmycie Gaussa - 15x15", blurredImage3);

        HighGui.waitKey(0);
        System.exit(0);
    }
}
