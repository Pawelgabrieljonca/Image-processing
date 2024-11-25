import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
public class EdgeDetection {
    public static void detectSobelEdges(String inputPath, String outputPath) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(inputPath, Imgcodecs.IMREAD_GRAYSCALE);
        if (src.empty()) {
            System.out.println("Failed to load image from: " + inputPath);
            return;
        }
        Mat gradX = new Mat(); // Gradient w kierunku X
        Mat gradY = new Mat(); // Gradient w kierunku Y
        Mat absGradX = new Mat(); // Bezwzględna wartość gradientu w X
        Mat absGradY = new Mat(); // Bezwzględna wartość gradientu w Y
        Mat edges = new Mat(); // Połączone krawędzie
        Imgproc.Sobel(        // Wykonanie Sobela w kierunku X
                src,              // Obraz wejściowy
                gradX,            // Obraz wyjściowy z gradientem w kierunku X
                CvType.CV_16S,    // Głębia obrazu wyjściowego (16-bitowy ze znakiem, aby uniknąć przepełnienia)
                1,                // dx: Pierwsza pochodna w kierunku X
                0,                // dy: Brak pochodnej w kierunku Y
                3,                // ksize: Rozmiar kernela Sobela (3x3)
                1,                // scale: Skala wzmocnienia gradientu (1 = bez zmian)
                0,                // delta: Dodawana wartość przesunięcia (0 = brak)
                Core.BORDER_DEFAULT // borderType: Obsługa pikseli brzegowych
        );
        Imgproc.Sobel(        // Wykonanie Sobela w kierunku Y
                src,              // Obraz wejściowy
                gradY,            // Obraz wyjściowy z gradientem w kierunku Y
                CvType.CV_16S,    // Głębia obrazu wyjściowego (16-bitowy ze znakiem)
                0,                // dx: Brak pochodnej w kierunku X
                1,                // dy: Pierwsza pochodna w kierunku Y
                3,                // ksize: Rozmiar kernela Sobela (3x3)
                1,                // scale: Skala wzmocnienia gradientu
                0,                // delta: Dodawana wartość przesunięcia
                Core.BORDER_DEFAULT // borderType: Obsługa pikseli brzegowych
        );
        // Konwersja wyników na format 8-bitowy
        Core.convertScaleAbs(gradX, absGradX); // Gradient X -> wartości bezwzględne
        Core.convertScaleAbs(gradY, absGradY); // Gradient Y -> wartości bezwzględne
        // Połączenie gradientów w kierunkach X i Y
        Core.addWeighted(
                absGradX, 0.5,    // Gradient w kierunku X i jego waga (0.5)
                absGradY, 0.5,    // Gradient w kierunku Y i jego waga (0.5)
                0, edges          // Dodawanie wyników i zapisanie do "edges"
        );
        // Zapisz wynikowy obraz
        boolean result = Imgcodecs.imwrite(outputPath, edges);
        if (result) {
            System.out.println("Sobel edge detection completed. Saved to: " + outputPath);
        } else {
            System.out.println("Failed to save the result to: " + outputPath);
        }
    }
}
