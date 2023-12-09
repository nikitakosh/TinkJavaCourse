package edu.project4.transformations;

import edu.project4.models.AffineCoef;
import edu.project4.models.Color;
import edu.project4.models.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation implements Transformation {

    private static final int MAX_RGB = 256;
    private final AffineCoef affineCoef;
    private final Color color;

    public AffineTransformation(AffineCoef affineCoef, Color color) {
        this.affineCoef = affineCoef;
        this.color = color;
    }

    public static List<AffineTransformation> generate(int countTransformations) {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < countTransformations; i++) {
            affineTransformations.add(new AffineTransformation(
                    AffineCoef.create(),
                    new Color(random.nextInt(MAX_RGB), random.nextInt(MAX_RGB), random.nextInt(MAX_RGB))
            ));
        }
        return affineTransformations;
    }

    @Override
    public Point apply(Point point) {
        return new Point(
                affineCoef.a() * point.x() + affineCoef.b() * point.y() + affineCoef.c(),
                affineCoef.d() * point.x() + affineCoef.e() * point.y() + affineCoef.f()
        );
    }

    public Color getColor() {
        return color;
    }
}
