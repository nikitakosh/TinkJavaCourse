package edu.project4.models;


import edu.project4.image.FractalImage;
import java.util.concurrent.ThreadLocalRandom;

public record Point(double x, double y) {
    public static Point random(Rect world) {
        return new Point(
                ThreadLocalRandom.current().nextDouble(world.x(), world.x() + world.width()),
                ThreadLocalRandom.current().nextDouble(world.y(), world.y() + world.height())
        );
    }

    public Pixel getPixelFromWorldToImage(Rect world, FractalImage canvas) {
        int pixelX = (int) ((x - world.x()) / world.width() * canvas.width());
        int pixelY = (int) ((y - world.y()) / world.height() * canvas.height());
        if (canvas.contains(pixelX, pixelY)) {
            return canvas.pixel(pixelX, pixelY);
        } else {
            return null;
        }
    }

    public Point rotate(Rect world, double theta2) {
        double centerX = world.x() + world.width() / 2;
        double centerY = world.y() + world.height() / 2;
        return new Point(
                (this.x - centerX) * Math.cos(theta2)
                        - (this.y - centerY) * Math.sin(theta2) + centerX,
                (this.x - centerX) * Math.sin(theta2)
                        + (this.y - centerY) * Math.cos(theta2) + centerY
        );
    }
}
