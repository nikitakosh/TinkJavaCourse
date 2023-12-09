package edu.project4.renderers;

import edu.project4.image.FractalImage;
import edu.project4.models.Point;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;


public class SingleThreadRenderer extends ThreadRenderer {
    @Override
    public FractalImage render(FractalImage canvas, Rect world,
                               List<Transformation> variations,
                               int samples, int iterPerSample, int countAffine, int symmetry) {
        for (int num = 0; num < samples; ++num) {
            oneSampleIterate(Point.random(world), variations, iterPerSample, world, canvas, countAffine, symmetry);
        }
        return canvas;
    }


}
