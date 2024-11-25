public class Main {
    public static void main(String[] args) {
        // Ścieżka do obrazu wejściowego
        String inputPath = "geralt.jpg";
        // Ścieżka do obrazu wyjściowego
        String outputPath = "SobelEdge.jpg";

        // Wywołanie metody do detekcji krawędzi
        EdgeDetection.detectSobelEdges(inputPath, outputPath);
    }
}
