package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    @DisplayName("ввод 4-значного числа")
    public void countDigitsTestInput4Digits() {
        Assertions.assertEquals(Task2.countDigits(4666), 4);
        Assertions.assertEquals(Task2.countDigits(0), 1 );
    }

    @Test
    @DisplayName("ввод 3-значного числа")
    public void countDigitsTestInput3Digits() {
        Assertions.assertEquals(Task2.countDigits(544), 3);
    }
    @Test
    @DisplayName("ввод нуля")
    public void countDigitsTestInputZero() {
        Assertions.assertEquals(Task2.countDigits(0), 1 );
    }
}
