package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.imageProcessors.GammaCorrectionProcessor;
import edu.project4.models.Rect;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.PolarTransformation;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecSingleThreadRender {
    public static final int DEGREE_TO_SECONDS = 9;
    private final static Logger LOGGER = LogManager.getLogger();
    int widthImage;
    int heightImage;
    double xWorld;
    double yWorld;
    double widthWorld;
    double heightWorld;
    int samples;
    int iterPerSample;
    int countAffine;
    int symmetry;
    double gamma;

    @SuppressWarnings("ParameterNumber")
    public ExecSingleThreadRender(
        int widthImage,
        int heightImage,
        double xWorld,
        double yWorld,
        double widthWorld,
        double heightWorld,
        int samples,
        int iterPerSample,
        int countAffine,
        int symmetry,
        double gamma
    ) {
        this.widthImage = widthImage;
        this.heightImage = heightImage;
        this.xWorld = xWorld;
        this.yWorld = yWorld;
        this.widthWorld = widthWorld;
        this.heightWorld = heightWorld;
        this.samples = samples;
        this.iterPerSample = iterPerSample;
        this.countAffine = countAffine;
        this.symmetry = symmetry;
        this.gamma = gamma;
    }

    public void start() {
        SingleThreadRenderer singleThreadRenderer = new SingleThreadRenderer();
        long start = System.nanoTime();
        FractalImage fractalImage = singleThreadRenderer.render(FractalImage.create(widthImage, heightImage),
            new Rect(xWorld, yWorld, widthWorld, heightWorld),
            List.of(new PolarTransformation()),
            samples,
            iterPerSample,
            countAffine,
            symmetry
        );
        GammaCorrectionProcessor gammaCorrectionProcessor = new GammaCorrectionProcessor(gamma);
        gammaCorrectionProcessor.process(fractalImage);
        long finish = System.nanoTime();
        ImageUtils.save(fractalImage,
            Path.of("src/main/resources/fractalFlameImages/fractalFlame.png"),
            ImageFormat.PNG
        );
        LOGGER.info(
            "Fractal flame is drawn\nTime taken is " + (finish - start) / Math.pow(samples, DEGREE_TO_SECONDS) + " s");
    }
}
