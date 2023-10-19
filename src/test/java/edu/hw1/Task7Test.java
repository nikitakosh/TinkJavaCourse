package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {

    @Test
    @DisplayName("сдвиг вправо")
    public void rotateRightTest(){
        Assertions.assertEquals(Task7.rotateRight(8, 1), 4);
    }

    @Test
    @DisplayName("сдвиг влево")
    public void rotateLeftTest(){
        Assertions.assertEquals(Task7.rotateLeft(16, 1), 1);
        Assertions.assertEquals(Task7.rotateLeft(17, 2), 6);

    }
}
