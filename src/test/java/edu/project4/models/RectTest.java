package edu.project4.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RectTest {

    @Test
    @DisplayName("is rectangle contains point")
    public void contains() {
        Point point = new Point(10, 10);
        Rect rect = new Rect(0, 0, 100, 100);
        Assertions.assertTrue(rect.contains(point));
        point = new Point(-1, -1);
        Assertions.assertFalse(rect.contains(point));
    }
}
