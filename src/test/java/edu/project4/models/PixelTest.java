package edu.project4.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PixelTest {
    private Pixel pixel;

    @BeforeEach
    public void setUp() {
        pixel = new Pixel(0, 0, 0, 0, 0.0);
    }

    @Test
    @DisplayName("saturate rgb")
    public void saturateRGB() {
        pixel.saturateRGB(2, 2, 2);
        Assertions.assertEquals(pixel.getR(), 1);
        Assertions.assertEquals(pixel.getG(), 1);
        Assertions.assertEquals(pixel.getB(), 1);
    }
    @Test
    @DisplayName("set color")
    public void setColor() {
        pixel.setColor(2, 2, 2);
        Assertions.assertEquals(pixel.getR(), 2);
        Assertions.assertEquals(pixel.getG(), 2);
        Assertions.assertEquals(pixel.getB(), 2);
    }

    @Test
    @DisplayName("saturate pixel")
    public void saturatePixel() {
        pixel.saturate(new Color(10, 10, 10));
        Assertions.assertEquals(10, pixel.getR());
        Assertions.assertEquals(10, pixel.getG());
        Assertions.assertEquals(10, pixel.getB());
        Assertions.assertEquals(1, pixel.getHitCount());
        pixel.saturate(new Color(10, 10, 10));
        Assertions.assertEquals(10, pixel.getR());
        Assertions.assertEquals(10, pixel.getG());
        Assertions.assertEquals(10, pixel.getB());
        Assertions.assertEquals(2, pixel.getHitCount());
    }
}

