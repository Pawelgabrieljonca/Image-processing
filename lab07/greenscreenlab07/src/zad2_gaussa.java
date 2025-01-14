import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class zad2_gaussa {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Wczytaj obrazy
        Mat background = Imgcodecs.imread("winter.jpg");
        Mat selfie = Imgcodecs.imread("greenscreen.jpg");
        // Dopasuj wymiary tła do selfie
        Imgproc.resize(background, background, selfie.size());
        // Konwertuj selfie do HSV
        Mat hsvSelfie = new Mat();
        Imgproc.cvtColor(selfie, hsvSelfie, Imgproc.COLOR_BGR2HSV);
        // Zdefiniuj zakres zielonego koloru
        Scalar lowerGreen = new Scalar(35, 55, 55); // Dolny zakres HSV
        Scalar upperGreen = new Scalar(85, 255, 255); // Górny zakres HSV
        // Stwórz maskę zielonego tła
        Mat mask = new Mat();
        Core.inRange(hsvSelfie, lowerGreen, upperGreen, mask);
        // Wygładź krawędzie maski za pomocą filtra Gaussa
        Mat smoothedMask = new Mat();
        Imgproc.GaussianBlur(mask, smoothedMask, new Size(15, 15), 0);
        // Konwertuj wygładzoną maskę na odwrotną maskę
        Mat inverseMask = new Mat();
        Core.bitwise_not(smoothedMask, inverseMask);
        // Zastosuj maskę, aby wyciąć selfie
        Mat selfieWithoutGreen = new Mat();
        Core.bitwise_and(selfie, selfie, selfieWithoutGreen, inverseMask);
        // Zastosuj wygładzoną maskę do tła
        Mat backgroundMasked = new Mat();
        Core.bitwise_and(background, background, backgroundMasked, smoothedMask);
        // Połącz oba obrazy
        Mat results = new Mat();
        Core.add(selfieWithoutGreen, backgroundMasked, results);
        // Zapisz i wyświetl obraz wynikowy
        HighGui.imshow("result", results);
        HighGui.waitKey();
        Imgcodecs.imwrite("zad2_gauss.jpg", results);
        System.out.println("Zapisano obraz wynikowy: result.jpg");
    }
}
