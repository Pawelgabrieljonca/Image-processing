import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("dog.png");
        if (image.empty()) {
            System.out.println("Failed to load dog.png.");
            return;
        }

        // Rozdzielenie obrazu na trzy kanały: B, G, R
        java.util.List<Mat> channels = new java.util.ArrayList<>();
        Core.split(image, channels);

        // Tworzenie matryc dla poszczególnych składowych
        Mat blueChannel = new Mat();
        Mat greenChannel = new Mat();
        Mat redChannel = new Mat();

        // Inicjalizacja matryc o takich samych wymiarach jak oryginalny obraz, ale z zerowymi wartościami dla dwóch kanałów
        blueChannel = channels.get(0);
        greenChannel = channels.get(1);
        redChannel = channels.get(2);

        // Zapisanie każdego kanału jako osobnego obrazka
        Imgcodecs.imwrite("blue_channel.png", blueChannel);
        Imgcodecs.imwrite("green_channel.png", greenChannel);
        Imgcodecs.imwrite("red_channel.png", redChannel);

        System.out.println("Obrazy składowe zostały zapisane.");
    }
}
