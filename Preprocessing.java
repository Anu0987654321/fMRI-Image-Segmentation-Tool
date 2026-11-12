import java.awt.image.BufferedImage;

public class Preprocessing {

    public static BufferedImage process(BufferedImage input) {

        int w = input.getWidth();
        int h = input.getHeight();

        BufferedImage gray = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);

        // Convert to grayscale
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int rgb = input.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int val = (r + g + b) / 3;
                gray.setRGB(x, y, (0xFF << 24) | (val << 16) | (val << 8) | val);
            }

        return gray;
    }
}