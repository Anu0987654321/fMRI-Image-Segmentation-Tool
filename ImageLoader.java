import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path)); // supports jpg, png, etc.
        } catch (Exception e) {
            System.out.println("Error loading image: " + e);
            return null;
        }
    }
}