package edu.project4.imageProcessors;

import edu.project4.image.FractalImage;
import edu.project4.models.Pixel;

public class GammaCorrectionProcessor implements ImageProcessor {

    private final double gamma;

    public GammaCorrectionProcessor(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    if (pixel.getNormal() > max) {
                        max = pixel.getNormal();
                    }
                }
            }
        }
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.setNormal(pixel.getNormal() / max);
                pixel.setColor(
                        (int) (pixel.getR() * Math.pow(pixel.getNormal(), (1.0 / gamma))),
                        (int) (pixel.getG() * Math.pow(pixel.getNormal(), (1.0 / gamma))),
                        (int) (pixel.getB() * Math.pow(pixel.getNormal(), (1.0 / gamma)))
                );
            }
        }
    }
}
