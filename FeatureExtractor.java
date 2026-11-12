import java.awt.image.BufferedImage;

public class FeatureExtractor {

    public static double getActivity(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();

        long sum = 0;

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                sum += (img.getRGB(x, y) >> 16) & 0xFF;

        return (double) sum / (w * h);
    }

    public static String getActivityLabel(double val) {
        if (val > 140) return "HIGH";
        if (val > 70) return "MODERATE";
        return "LOW";
    }

    public static int getPercent(double val) {
        return (int)((val / 255.0) * 100);
    }
}