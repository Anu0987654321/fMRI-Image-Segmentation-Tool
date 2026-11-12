import java.awt.image.BufferedImage;

public class ROIExtractor {

    public static BufferedImage extract(BufferedImage img) {

        int w = img.getWidth();
        int h = img.getHeight();

        int x = w / 4;
        int y = h / 4;
        int rw = w / 2;
        int rh = h / 2;

        BufferedImage roi = new BufferedImage(rw, rh, BufferedImage.TYPE_BYTE_GRAY);

        for (int i = 0; i < rw; i++)
            for (int j = 0; j < rh; j++)
                roi.setRGB(i, j, img.getRGB(x+i, y+j));

        return roi;
    }
}