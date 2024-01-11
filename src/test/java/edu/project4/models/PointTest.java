package edu.project4.models;

import edu.project4.image.FractalImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    @DisplayName("random point inside a rectangle")
    public void random() {
        Rect rect = new Rect(10, 10, 100, 100);
        Point point = Point.random(rect);
        Assertions.assertTrue(rect.contains(point));
    }

    @Test
    @DisplayName("get pixel from wold to image")
    public void getPixelFromWorldToImage() {
        Rect world = new Rect(0, 0, 1, 1);
        FractalImage fractalImage = FractalImage.create(100, 100);
        Point point = new Point(0.5, 0.5);
        Pixel pixel = point.getPixelFromWorldToImage(world, fractalImage);
        Assertions.assertNotNull(pixel);
        point = new Point(1.1, 1.1);
        pixel = point.getPixelFromWorldToImage(world, fractalImage);
        Assertions.assertNull(pixel);
    }

    @Test
    @DisplayName("rotate point")
    public void rotatePoint() {
        Point point = new Point(12, 14);
        Rect rect = new Rect(0, 0, 100 ,100);
        point = point.rotate(rect, 24);
        Assertions.assertEquals(point.x(), 1.2803766889556556);
        Assertions.assertEquals(point.y(), 69.14153349211982);
    }
}
