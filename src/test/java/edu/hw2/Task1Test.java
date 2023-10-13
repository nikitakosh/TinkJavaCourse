package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("test constant number")
    public void constantTest(){
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        Assertions.assertEquals(two.evaluate(), 2);
        Assertions.assertEquals(four.evaluate(), 4);
    }

    @Test
    @DisplayName("test negative number")
    public void negateTest(){
        var negOne = new Expr.Negate(new Expr.Constant(1));
        Assertions.assertEquals(negOne.evaluate(), -1);
    }

    @Test
    @DisplayName("test addition of two number")
    public void additionTest(){
        var sumTwoFour = new Expr.Addition(new Expr.Constant(2), new Expr.Constant(4));
        Assertions.assertEquals(sumTwoFour.evaluate(), 6);
    }

    @Test
    @DisplayName("test multiplication of two number")
    public void multiplicationTest(){
        var mult = new Expr.Multiplication(new Expr.Constant(6), new Expr.Constant(2));
        Assertions.assertEquals(mult.evaluate(), 12);
    }

    @Test
    @DisplayName("test exponent")
    public void exponentTest(){
        var exp = new Expr.Exponent(new Expr.Constant(6), 2);
        Assertions.assertEquals(exp.evaluate(), 36);
    }

}
