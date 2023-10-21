package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double num) implements Expr {
        @Override
        public double evaluate() {
            return num;
        }
    }

    record Negate(Expr num1) implements Expr {
        @Override
        public double evaluate() {
            return -num1.evaluate();
        }
    }

    record Exponent(Expr num, int exponent) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(num.evaluate(), exponent);
        }
    }

    record Addition(Expr num1, Expr num2) implements Expr {
        @Override
        public double evaluate() {
            return num1.evaluate() + num2.evaluate();
        }
    }

    record Multiplication(Expr num1, Expr num2) implements Expr {
        @Override
        public double evaluate() {
            return num1.evaluate() * num2.evaluate();
        }
    }
}
