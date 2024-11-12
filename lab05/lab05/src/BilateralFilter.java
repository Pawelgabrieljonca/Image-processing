import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class BilateralFilter {
    public static void processImage() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imagePath = "geralt.jpg";
        Mat image = Imgcodecs.imread(imagePath);

        if (image.empty()) {
            System.out.println("Failed to load image!");
            return;
        }

        // Tworzenie obiektów Mat dla przetworzonych obrazów
        Mat bilateralBlurredImage1 = new Mat();
        Mat bilateralBlurredImage2 = new Mat();
        Mat bilateralBlurredImage3 = new Mat();

        // Rozmycie bilateralne dla trzech różnych rozmiarów filtra
        Imgproc.bilateralFilter(image, bilateralBlurredImage1, 9, 75, 75);
        Imgproc.bilateralFilter(image, bilateralBlurredImage2, 15, 150, 150);
        Imgproc.bilateralFilter(image, bilateralBlurredImage3, 25, 300, 300);

        // Wyświetl oryginalny obraz oraz obrazy po rozmyciu bilateralnym
        HighGui.imshow("Oryginalny Obraz", image);
        HighGui.imshow("Rozmycie Bilateralne - 9", bilateralBlurredImage1);
        HighGui.imshow("Rozmycie Bilateralne - 15", bilateralBlurredImage2);
        HighGui.imshow("Rozmycie Bilateralne - 25", bilateralBlurredImage3);

        HighGui.waitKey(0);
        System.exit(0);
    }
}
