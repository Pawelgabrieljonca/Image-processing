import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class zad3 {
    public static void main(String[] args) {
        // Załaduj bibliotekę OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Wczytaj obraz
        String imagePath = "selfie.jpg"; // Ścieżka do zdjęcia
        Mat image = Imgcodecs.imread(imagePath);

        if (image.empty()) {
            System.out.println("Nie udało się wczytać obrazu!");
            return;
        }

        // Konwersja obrazu do przestrzeni barw HSV
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);

        // Ustawienia zakresów kolorów HSV (np. dla zieleni)
        Scalar lowHSV = new Scalar(35, 50, 50);  // Dolny zakres HSV
        Scalar highHSV = new Scalar(85, 255, 255); // Górny zakres HSV

        // Filtrowanie obrazu za pomocą inRange
        Mat mask = new Mat();
        Core.inRange(hsvImage, lowHSV, highHSV, mask);

        // Wyświetlenie wyników
        HighGui.imshow("Oryginalny obraz", image);
        HighGui.imshow("Maska", mask);

        // Zatrzymanie programu do momentu zamknięcia okien
        HighGui.waitKey();
        System.exit(0);
    }
}
