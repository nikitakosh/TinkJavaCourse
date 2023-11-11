package edu.hw2.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("UncommentedMain")
public class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task1() {
    }

    public static void main(String[] args) {
        final int CONSTANT_NUMBER1 = 2;
        final int CONSTANT_NUMBER2 = 4;
        var two = new Expr.Constant(CONSTANT_NUMBER1);
        var four = new Expr.Constant(CONSTANT_NUMBER2);
        var negOne = new Expr.Negate(new Expr.Constant(1)); // -1
        var sumTwoFour = new Expr.Addition(two, four); // 6
        var mult = new Expr.Multiplication(sumTwoFour, negOne); // -6
        var exp = new Expr.Exponent(mult, CONSTANT_NUMBER1); // 36
        var res = new Expr.Addition(exp, new Expr.Constant(1)); // 37
        LOGGER.info(res + " = " + res.evaluate());
    }
}
