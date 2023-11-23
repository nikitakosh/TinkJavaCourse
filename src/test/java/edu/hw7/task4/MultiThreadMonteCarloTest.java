package edu.hw7.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultiThreadMonteCarloTest {


    @Test
    @DisplayName("calculate pi")
    public void calculatePi() {
        MultiThreadMonteCarlo multiThreadMonteCarlo = new MultiThreadMonteCarlo();
        Assertions.assertTrue(
                Math.abs(multiThreadMonteCarlo.calculatePi(10000000, 4) - Math.PI) < 0.01
        );
        Assertions.assertTrue(
                Math.abs(multiThreadMonteCarlo.calculatePi(100000000, 4) - Math.PI) < 0.01
        );
        Assertions.assertTrue(
                Math.abs(multiThreadMonteCarlo.calculatePi(100000000, 4) - Math.PI) < 0.01
        );
    }

}
