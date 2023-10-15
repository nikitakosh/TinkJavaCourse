package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("ввод строки из цифр")
    public void fixStringTestDigits(){
        Assertions.assertEquals(Task4.fixString("123456"), "214365");
        Assertions.assertEquals(Task4.fixString("badce"), "abcde");
    }
    @Test
    @DisplayName("ввод строки из символов с пробелами")
    public void fixStringTestCharsWithSpaces(){
        Assertions.assertEquals(Task4.fixString("hTsii  s aimex dpus rtni.g"), "This is a mixed up string.");
    }
    @Test
    @DisplayName("ввод строки из символов")
    public void fixStringTestChars(){
        Assertions.assertEquals(Task4.fixString("badce"), "abcde");
    }

    @Test
    @DisplayName("ввод строки из 1 символа")
    public void fixStringTestChar(){
        Assertions.assertEquals(Task4.fixString("a"), "a");
    }
}
