import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class Main {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("p.png", Imgcodecs.IMREAD_GRAYSCALE);

        if (img.empty()) {
            System.out.println("Failed to load image.");
            return;
        }
        // Binaryzacja obrazu
        Mat binaryImg = new Mat();
        Imgproc.threshold(img, binaryImg, 127, 255, Imgproc.THRESH_BINARY);

        // Szkieletowanie przy pomocy iteracyjnej erozji i dylatacji
        Mat skeleton = Mat.zeros(binaryImg.size(), CvType.CV_8UC1);
        Mat temp = new Mat();
        Mat eroded = new Mat();
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(3, 3));
        boolean done;
        do {
            // Wykonanie erozji
            Imgproc.erode(binaryImg, eroded, element);

            // Wykonanie dylatacji na erodowanym obrazie
            Imgproc.dilate(eroded, temp, element);

            // Odejmowanie, aby uzyskać różnicę
            Core.subtract(binaryImg, temp, temp);

            // Sumowanie wyniku z dotychczasowym szkieletem
            Core.bitwise_or(skeleton, temp, skeleton);

            // Ustawienie erodowanego obrazu jako nowego obrazu wejściowego
            eroded.copyTo(binaryImg);
            // Sprawdzenie, czy obraz został całkowicie erodowany
            done = Core.countNonZero(binaryImg) == 0;
        } while (!done);
        // Zapisanie obrazu szkieletu do pliku
        String outputFilename = "skeleton.png";
        Imgcodecs.imwrite(outputFilename, skeleton);
        System.out.println("Zapisano: " + outputFilename);
    }
}
