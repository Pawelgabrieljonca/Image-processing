import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
public class Zad2 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("geralt.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        if (src.empty()) {
            System.out.println("Failed to load image.");
            return;
        }
        // Wykonaj detekcję krawędzi Laplacian
        Mat edges = new Mat();
        // Parametry: źródło, cel, głębokość, rozmiar operatora Sobela (kernela), skalowanie, delta, borderType
        Imgproc.Laplacian(src, edges, CvType.CV_16S, 3, 1, 0, 4);
        // Przekształć wynik na format 8-bitowy (widoczny)
        Mat absEdges = new Mat();
        Core.convertScaleAbs(edges, absEdges);

        HighGui.imshow("Original Image", src);
        HighGui.imshow("LaplacianEdge", absEdges);
        HighGui.waitKey(0);
    }
}
