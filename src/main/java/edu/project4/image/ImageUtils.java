package edu.project4.image;

import edu.project4.models.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static final int RED_SHIFT = 16;
    public static final int GREEN_SHIFT = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                int rgb = (pixel.getR() << RED_SHIFT) | (pixel.getG() << GREEN_SHIFT) | pixel.getB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        try {
            File output = filename.toFile();
            ImageIO.write(bufferedImage, format.name().toLowerCase(), output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
