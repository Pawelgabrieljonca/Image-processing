import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class Main {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("women.png", Imgcodecs.IMREAD_GRAYSCALE);
        if (img.empty()) {
            System.out.println("Failed to load image.");
            return;
        }
        // Segmentacja poprzez binaryzację
        Mat binaryImg = new Mat();
        Imgproc.threshold(img, binaryImg, 127, 255, Imgproc.THRESH_BINARY);

        // Definicje rozmiaru i kształtu elementu strukturalnego
        Size kernelSize = new Size(3, 3);
        int shape = Imgproc.MORPH_RECT;
        Mat element = Imgproc.getStructuringElement(shape, kernelSize);

        // Dylatacja obrazu
        Mat dilatedImg = new Mat();
        Imgproc.dilate(binaryImg, dilatedImg, element);

        // Erozja obrazu
        Mat erodedImg = new Mat();
        Imgproc.erode(binaryImg, erodedImg, element);

        // Metoda 1: Kontury przez dylatację
        Mat contoursByDilation = new Mat();
        Core.subtract(dilatedImg, binaryImg, contoursByDilation);
        Imgcodecs.imwrite("contours_by_dilation.png", contoursByDilation);
        System.out.println("Zapisano: contours_by_dilation.png");

        // Metoda 2: Kontury przez erozję
        Mat contoursByErosion = new Mat();
        Core.subtract(binaryImg, erodedImg, contoursByErosion);
        Imgcodecs.imwrite("contours_by_erosion.png", contoursByErosion);
        System.out.println("Zapisano: contours_by_erosion.png");

        // Metoda 3: Kontury przez różnicę między dylatacją a erozją
        Mat contoursByDifference = new Mat();
        Core.subtract(dilatedImg, erodedImg, contoursByDifference);
        Imgcodecs.imwrite("contours_by_difference.png", contoursByDifference);
        System.out.println("Zapisano: contours_by_difference.png");
    }
}
