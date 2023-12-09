package edu.project4.models;

import java.util.concurrent.ThreadLocalRandom;

public record AffineCoef(double a, double b, double d, double e, double c, double f) {

    public static AffineCoef create() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double a = random.nextDouble(2) - 1;
        double b = random.nextDouble(2) - 1;
        double d = random.nextDouble(2) - 1;
        double e = random.nextDouble(2) - 1;
        double c = random.nextDouble(2) - 1;
        double f = random.nextDouble(2) - 1;
        while (a * a + d * d >= 1 || b * b + e * e >= 1
                || a * a + b * b + d * d + e * e >= 1 + (a * e - b * d) * (a * e - b * d)) {
            a = random.nextDouble(2) - 1;
            b = random.nextDouble(2) - 1;
            d = random.nextDouble(2) - 1;
            e = random.nextDouble(2) - 1;
        }
        return new AffineCoef(a, b, d, e, c, f);
    }
}
