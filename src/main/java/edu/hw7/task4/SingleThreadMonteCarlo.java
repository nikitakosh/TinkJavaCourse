package edu.hw7.task4;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SingleThreadMonteCarlo {

    public static final int CIRCLE_AREA = 4;

    public double calculatePi(int countIteration) {
        int totalCount = 0;
        int circleCount = 0;
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < countIteration; i++) {
            double x = secureRandom.nextDouble() * 2;
            double y = secureRandom.nextDouble() * 2;
            circleCount += isInCircle(x, y) ? 1 : 0;
            totalCount++;
        }
        return CIRCLE_AREA * ((double) circleCount / totalCount);
    }

    public boolean isInCircle(double x, double y) {
        return Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1;
    }

}
