package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("ввод числа которое само является палиндромом")
    public void isPalindromeDescendantTestSelfPalindrome(){
        Assertions.assertTrue(Task5.isPalindromeDescendant(123321));
    }

    @Test
    @DisplayName("ввод числа третий потомок, которого является палиндромом")
    public void isPalindromeDescendantTestThirdDescendantIsPalindrome(){
        Assertions.assertTrue(Task5.isPalindromeDescendant(11211230));
    }

    @Test
    @DisplayName("ввод числа второй потомок, которого является палиндромом")
    public void isPalindromeDescendantTestSecondDescendantIsPalindrome(){
        Assertions.assertTrue(Task5.isPalindromeDescendant(13001120));
    }
    @Test
    @DisplayName("ввод числа первый потомок, которого является палиндромом")
    public void isPalindromeDescendantTestFirstDescendantIsPalindrome(){
        Assertions.assertTrue(Task5.isPalindromeDescendant(23336014));
    }
    @Test
    @DisplayName("ввод числа который не ялвяется палиндромом, как и его предки")
    public void isPalindromeDescendantTestNoPalindrome(){
        Assertions.assertFalse(Task5.isPalindromeDescendant(12));
    }
    @Test
    @DisplayName("ввод одной цифры")
    public void isPalindromeDescendantTestOneDigit(){
        Assertions.assertFalse(Task5.isPalindromeDescendant(1));
    }
}
