import java.awt.image.BufferedImage;

public class Segmentation {

    public static BufferedImage segment(BufferedImage input) {

        int w = input.getWidth();
        int h = input.getHeight();

        long sum = 0;

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                sum += (input.getRGB(x, y) >> 16) & 0xFF;

        int threshold = (int)(sum / (w * h) * 0.8);

        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int val = ((input.getRGB(x, y) >> 16) & 0xFF) > threshold ? 255 : 0;
                out.setRGB(x, y, (0xFF<<24)|(val<<16)|(val<<8)|val);
            }

        return out;
    }
}