import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
public class zad {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat background = Imgcodecs.imread("winter.jpg");
        Mat selfie = Imgcodecs.imread("greenscreen.jpg");
        // 2. Dopasuj wymiary tła do selfie
        Imgproc.resize(background, background, selfie.size());
        // 3. Konwertuj selfie do HSV
        Mat hsvSelfie = new Mat();
        Imgproc.cvtColor(selfie, hsvSelfie, Imgproc.COLOR_BGR2HSV);
        // 4. Zdefiniuj zakres zielonego koloru
        Scalar lowerGreen = new Scalar(35, 55, 55); // Dolny zakres HSV
        Scalar upperGreen = new Scalar(85, 255, 255); // Górny zakres HSV
        // 5. Stwórz maskę zielonego tła
        Mat mask = new Mat();
        Core.inRange(hsvSelfie, lowerGreen, upperGreen, mask);
        // Odwróć maskę, aby zachować tylko "nie-zielone" obszary
        Mat inverseMask = new Mat();
        Core.bitwise_not(mask, inverseMask);
        // 6. Zastosuj maskę, aby wyciąć selfie
        Mat selfieWithoutGreen = new Mat();
        Core.bitwise_and(selfie, selfie, selfieWithoutGreen, inverseMask);
        // Zastosuj odwrotną maskę do tła
        Mat backgroundMasked = new Mat();
        Core.bitwise_and(background, background, backgroundMasked, mask);
        // 7. Połącz oba obrazy
        Mat result = new Mat();
        Core.add(selfieWithoutGreen, backgroundMasked, result);
        //Zapisuje i wyświetlam obraz
        HighGui.imshow("result", result);
        HighGui.waitKey();
        Imgcodecs.imwrite("zad1result.jpg", result);
        System.out.println("Zapisano obraz wynikowy: result.jpg");
    }
}
