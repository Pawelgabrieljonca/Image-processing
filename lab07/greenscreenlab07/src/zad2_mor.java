import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class zad2_mor {
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
        // Wygładź krawędzie maski za pomocą operacji morfologicznych
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Mat openedMask = new Mat();
        Imgproc.morphologyEx(mask, openedMask, Imgproc.MORPH_OPEN, kernel);  // Otwarcie
        Mat closedMask = new Mat();
        Imgproc.morphologyEx(openedMask, closedMask, Imgproc.MORPH_CLOSE, kernel);  // Domknięcie
        // Konwertuj wygładzoną maskę na odwrotną maskę
        Mat inverseMask = new Mat();
        Core.bitwise_not(closedMask, inverseMask);
        // Zastosuj maskę, aby wyciąć selfie
        Mat selfieWithoutGreen = new Mat();
        Core.bitwise_and(selfie, selfie, selfieWithoutGreen, inverseMask);
        // Zastosuj wygładzoną maskę do tła
        Mat backgroundMasked = new Mat();
        Core.bitwise_and(background, background, backgroundMasked, closedMask);
        // Połącz oba obrazy
        Mat results = new Mat();
        Core.add(selfieWithoutGreen, backgroundMasked, results);
        // Zapisz i wyświetl obraz wynikowy
        HighGui.imshow("result", results);
        HighGui.waitKey();
        Imgcodecs.imwrite("zad2_mor.jpg", results);
        System.out.println("Zapisano obraz wynikowy: result.jpg");
    }
}
