package edu.project4.transformations;

import edu.project4.models.Point;

public class HyperbolicTransformation implements Transformation {
    public static final double DEGREE = 0.5;

    @Override
    public Point apply(Point point) {
        double r = Math.pow(point.x() * point.x() + point.y() * point.y(), DEGREE);
        double theta = Math.atan(point.x() / point.y());
        return new Point(
            Math.sin(theta) / r,
            r * Math.cos(theta)
        );
    }
}