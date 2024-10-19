import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  // Załadowanie biblioteki OpenCV
        Mat image = Imgcodecs.imread("dog.jpg");  // Wczytanie "dog.jpg" do obiektu typu Mat (macierzy)

        if (image.empty()) {
            System.out.println("Nie udało się wczytać obrazu, spróbuj ponownie");
            return;
        }
        // Powiększanie obrazu o współczynnik 1,5
        Mat resizedImage = new Mat();
        Imgproc.resize(image, resizedImage, new Size(image.cols() * 1.5, image.rows() * 1.5), 0, 0, Imgproc.INTER_LINEAR);

        // Zapisywanie powiększonego obrazu
        Imgcodecs.imwrite("resized_1_5x.jpg", resizedImage);
    }
}
