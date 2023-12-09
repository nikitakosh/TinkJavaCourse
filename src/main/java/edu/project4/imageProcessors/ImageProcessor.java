package edu.project4.imageProcessors;

import edu.project4.image.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
