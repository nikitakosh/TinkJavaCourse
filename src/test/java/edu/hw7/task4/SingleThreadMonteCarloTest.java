package edu.hw7.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingleThreadMonteCarloTest {

    @Test
    @DisplayName("calculate pi")
    public void calculatePi() {
        SingleThreadMonteCarlo singleThreadMonteCarlo = new SingleThreadMonteCarlo();
        Assertions.assertTrue(
                Math.abs(singleThreadMonteCarlo.calculatePi(1000000) - Math.PI) < 0.01
        );
        Assertions.assertTrue(
                Math.abs(singleThreadMonteCarlo.calculatePi(5000000) - Math.PI) < 0.01
        );
    }

    @Test
    @DisplayName("is in circle")
    public void isInCircle() {
        SingleThreadMonteCarlo singleThreadMonteCarlo = new SingleThreadMonteCarlo();
        Assertions.assertTrue(singleThreadMonteCarlo.isInCircle(1, 1));
        Assertions.assertTrue(singleThreadMonteCarlo.isInCircle(1.5, 1.5));
        Assertions.assertFalse(singleThreadMonteCarlo.isInCircle(2, 2));
    }
}
