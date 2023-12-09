package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.models.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class FractalImageTest {

    @Test
    @DisplayName("crate fractal image")
    public void create() {
        FractalImage fractalImage = FractalImage.create(100, 100);
        Arrays.stream(fractalImage.data()).forEach(pixel -> {
            Assertions.assertEquals(0, pixel.getR());
            Assertions.assertEquals(0, pixel.getG());
            Assertions.assertEquals(0, pixel.getB());
            Assertions.assertEquals(0, pixel.getHitCount());
            Assertions.assertEquals(0.0, pixel.getNormal());
        });
        Assertions.assertEquals(fractalImage.width(), 100);
        Assertions.assertEquals(fractalImage.height(), 100);
    }

    @Test
    @DisplayName("is image contains (x, y)")
    public void contains() {
        FractalImage fractalImage = FractalImage.create(10, 10);
        Assertions.assertTrue(fractalImage.contains(5, 5));
        Assertions.assertFalse(fractalImage.contains(-1, -1));
        Assertions.assertFalse(fractalImage.contains(11, 11));
    }

    @Test
    @DisplayName("get pixel from image")
    public void pixel() {
        FractalImage fractalImage = FractalImage.create(10, 10);
        fractalImage.data()[25] = new Pixel(1, 1, 1, 1, 1);
        Pixel pixel = fractalImage.pixel(5, 2);
        Assertions.assertEquals(1, pixel.getR());
        Assertions.assertEquals(1, pixel.getG());
        Assertions.assertEquals(1, pixel.getB());
        Assertions.assertEquals(1, pixel.getHitCount());
        Assertions.assertEquals(1, pixel.getNormal());
    }
}
