package edu.project4.renderers;

import edu.project4.image.FractalImage;
import edu.project4.models.Point;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadRenderer extends ThreadRenderer {

    public static final int THREADS = 4;

    @Override
    public FractalImage render(FractalImage canvas, Rect world,
                               List<Transformation> variations,
                               int samples, int iterPerSample, int countAffine, int symmetry) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Callable<Void> oneSampleIterateTask = () -> {
            oneSampleIterate(Point.random(world), variations, iterPerSample, world, canvas, countAffine, symmetry);
            return null;
        };
        for (int num = 0; num < samples; ++num) {
            futures.add(
                    executorService.submit(oneSampleIterateTask)
            );
        }
        executorService.close();
        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return canvas;
    }
}
