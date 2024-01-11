package edu.hw8.task3;

import edu.task3.SingleThreadPasswordBruteForce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SingleThreadPasswordBruteForceTest {

    @Test
    @DisplayName("single thread password brute force")
    public void bruteForce() {
        long start = System.nanoTime();
        SingleThreadPasswordBruteForce singleThreadPasswordBruteForce = new SingleThreadPasswordBruteForce();
        Assertions.assertEquals(singleThreadPasswordBruteForce.singleThreadBruteForce(),
                Map.of(
                        "a.v.petrov", "ert",
                        "v.v.belv", "grt",
                        "v.v.bel", "ght"
                ));
        long finish = System.nanoTime();
        System.out.println((finish - start) / Math.pow(10, 9));
    }

}
