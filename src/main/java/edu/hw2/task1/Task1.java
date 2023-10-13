package edu.hw2.task1;

public class Task1 {
    public static void main(String[] args) {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1)); // -1
        var sumTwoFour = new Expr.Addition(two, four); // 6
        var mult = new Expr.Multiplication(sumTwoFour, negOne); // -6
        var exp = new Expr.Exponent(mult, 2); // 36
        var res = new Expr.Addition(exp, new Expr.Constant(1)); // 37
        System.out.println(res + " = " + res.evaluate());
    }
}
