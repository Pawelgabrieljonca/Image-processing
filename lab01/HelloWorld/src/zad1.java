import org.opencv.core.Core;  // Importowanie klasy Core z biblioteki OpenCV
import org.opencv.core.Mat;   // Importowanie klasy Mat, która reprezentuje macierz (obraz)
import org.opencv.imgcodecs.Imgcodecs; // Importowanie klasy Imgcodecs do wczytywania i zapisywania obrazów
import org.opencv.highgui.HighGui; // Importowanie klasy HighGui do wyświetlania obrazów
import org.opencv.imgproc.Imgproc; // Importowanie klasy Imgproc do przetwarzania obrazów
import org.opencv.core.Point; // Importowanie klasy Point do reprezentacji punktów
import org.opencv.core.Scalar; // Importowanie klasy Scalar do reprezentacji kolorów
public class zad1 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("dog.jpg");
        if (image.empty()) {// Sprawdzenie, czy obraz został poprawnie wczytany
            System.out.println("fail");
            return;
        }
        // Rysowanie okręgu (środek, promień, kolor, grubość)
        Imgproc.circle(image, new Point(200, 200), 30, new Scalar(240, 200, 100), 3); // Zielony okrąg
        // Rysowanie prostokąta (lewy górny róg, prawy dolny róg, kolor, grubość)
        Imgproc.rectangle(image, new Point(100, 100), new Point(200, 150), new Scalar(255, 140, 22), 4); // Niebieski prostokąt
        // Rysowanie linii (punkt początkowy, punkt końcowy, kolor, grubość)
        Imgproc.line(image, new Point(100, 50), new Point(500, 150), new Scalar(100, 66, 255), 4); //
        // Wyświetlenie obrazu z dodanymi elementami w nowym oknie
        HighGui.imshow("Obraz z kształtami", image);
        HighGui.waitKey(); // Oczekiwanie na naciśnięcie dowolnego klawisza
        HighGui.destroyAllWindows(); // Zamknięcie wszystkich otwartych okien
    }
}
