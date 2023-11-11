package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task1Test {

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("test constant number")
    public void constantTest(int num){
        var constant = new Expr.Constant(num);
        Assertions.assertEquals(constant.evaluate(), num);
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

    @Test
    @DisplayName("test addition of two multiplication result")
    public void additionOfTwoMultiplicationResultTest(){
        var addition = new Expr.Addition(new Expr.Multiplication(new Expr.Constant(2), new Expr.Constant(3)),
                                     new Expr.Multiplication(new Expr.Constant(4), new Expr.Constant(5)));
        Assertions.assertEquals(addition.evaluate(), 26);
    }

}
