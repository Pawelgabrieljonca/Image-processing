import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("dog.jpg");
        if (image.empty()) {
            System.out.println("Failed to load image");
            return;
        }
        List<Mat> bgrPlanes = new ArrayList<>();  // Rozdziel obraz na trzy kanały: B, G, R
        Core.split(image, bgrPlanes);
        int histSize = 256; // 256 przedziałów intensywności pikseli
        MatOfInt histSizeMat = new MatOfInt(histSize);
        MatOfFloat histRange = new MatOfFloat(0, 256); // Zakres intensywności (0 do 255)
        boolean accumulate = false;
        // Histogramy dla każdego z kanałów
        Mat bHist = new Mat();
        Mat gHist = new Mat();
        Mat rHist = new Mat();
        // Oblicz histogram dla kanałów B, G, R
        Imgproc.calcHist(Collections.singletonList(bgrPlanes.get(0)), new MatOfInt(0), new Mat(), bHist, histSizeMat, histRange, accumulate);
        Imgproc.calcHist(Collections.singletonList(bgrPlanes.get(1)), new MatOfInt(0), new Mat(), gHist, histSizeMat, histRange, accumulate);
        Imgproc.calcHist(Collections.singletonList(bgrPlanes.get(2)), new MatOfInt(0), new Mat(), rHist, histSizeMat, histRange, accumulate);
        // Ustawienia wykresu histogramu
        int histW = 512; // Szerokość wykresu
        int histH = 400; // Wysokość wykresu
        int binWidth = (int) Math.round((double) histW / histSize);
        Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
        // Normalizacja histogramu do rozmiarów wykresu
        Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);
        // Rysowanie histogramu dla każdego kanału
        for (int i = 1; i < histSize; i++) {
            // Niebieski kanał
            Imgproc.line(
                    histImage,
                    new Point(binWidth * (i - 1), histH - Math.round(bHist.get(i - 1, 0)[0])),
                    new Point(binWidth * i, histH - Math.round(bHist.get(i, 0)[0])),
                    new Scalar(255, 0, 0), 2, 8, 0);
            // Zielony kanał
            Imgproc.line(
                    histImage,
                    new Point(binWidth * (i - 1), histH - Math.round(gHist.get(i - 1, 0)[0])),
                    new Point(binWidth * i, histH - Math.round(gHist.get(i, 0)[0])),
                    new Scalar(0, 255, 0), 2, 8, 0);
            // Czerwony kanał
            Imgproc.line(
                    histImage,
                    new Point(binWidth * (i - 1), histH - Math.round(rHist.get(i - 1, 0)[0])),
                    new Point(binWidth * i, histH - Math.round(rHist.get(i, 0)[0])),
                    new Scalar(0, 0, 255), 2, 8, 0);
        }
        Imgcodecs.imwrite("histogram.png", histImage);
        System.out.println("Histogram zapisany jako histogram.png");
    }
}
