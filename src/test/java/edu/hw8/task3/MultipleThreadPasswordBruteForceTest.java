package edu.hw8.task3;

import edu.task3.MultipleThreadPasswordBruteForce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MultipleThreadPasswordBruteForceTest {

    @Test
    @DisplayName("multiple thread password brute force")
    public void bruteForce() {
        long start = System.nanoTime();
        MultipleThreadPasswordBruteForce multipleThreadPasswordBruteForce = new MultipleThreadPasswordBruteForce();
        Assertions.assertEquals(multipleThreadPasswordBruteForce.bruteForce(),
                Map.of(
                        "a.v.petrov", "ert",
                        "v.v.belv", "grt",
                        "v.v.bel", "ght"
                ));
        long finish = System.nanoTime();
        System.out.println((finish - start) / Math.pow(10, 9));
    }
}
