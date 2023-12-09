package edu.project4.transformations;

import edu.project4.models.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.x() * point.x() + point.y() * point.y();
        return new Point(point.x() / r, point.y() / r);
    }
}
