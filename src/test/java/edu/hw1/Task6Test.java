package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    @DisplayName("ввод числа, которому 5 шагов до 6174")
    public void countKTestFiveStep(){
        Assertions.assertEquals(Task6.countK(6621), 5);
    }
    @Test
    @DisplayName("ввод числа, которому 4 шага до 6174")
    public void countKTestFourStep(){
        Assertions.assertEquals(Task6.countK(6554), 4);
    }

    @Test
    @DisplayName("ввод числа, которому 3 шага до 6174")
    public void countKTestThreeStep(){
        Assertions.assertEquals(Task6.countK(1234), 3);
    }
}
