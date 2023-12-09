package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.models.Rect;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.transformations.LinearTransformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MultiThreadRendererTest {

    @Test
    @DisplayName("render image")
    public void render() {
        MultiThreadRenderer multiThreadRenderer = new MultiThreadRenderer();
        Assertions.assertDoesNotThrow(() -> multiThreadRenderer.render(
            FractalImage.create(1000, 1000),
            new Rect(0, 0, 1, 1),
            List.of(
                new LinearTransformation()
            ),
            10,
            1000000,
            10,
            10
        ));
    }
}
