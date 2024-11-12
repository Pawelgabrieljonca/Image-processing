import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
public class MedianFilter {
    public static void processImage() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);

        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }
        // Tworzenie obiektów Mat dla przetworzonych obrazów
        Mat medianBlurredImage1 = new Mat();
        Mat medianBlurredImage2 = new Mat();
        Mat medianBlurredImage3 = new Mat();

        // Rozmycie medianowe dla trzech różnych rozmiarów filtra
        Imgproc.medianBlur(image, medianBlurredImage1, 3);   // 3x3
        Imgproc.medianBlur(image, medianBlurredImage2, 7);   // 7x7
        Imgproc.medianBlur(image, medianBlurredImage3, 15);  // 15x15

        // Wyświetl oryginalny obraz oraz obrazy po rozmyciu medianowym
        HighGui.imshow("Oryginalny Obraz", image);
        HighGui.imshow("Rozmycie Medianowe - 3x3", medianBlurredImage1);
        HighGui.imshow("Rozmycie Medianowe - 7x7", medianBlurredImage2);
        HighGui.imshow("Rozmycie Medianowe - 15x15", medianBlurredImage3);

        HighGui.waitKey(0);
        System.exit(0);
    }
}
