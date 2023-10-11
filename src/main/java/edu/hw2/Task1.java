package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaluate();

        public record Constant() implements Expr {
            private static double valueOfConstant;

            public void constantInit(double value) {
                valueOfConstant = value;
            }

            @Override
            public double evaluate() {
                return valueOfConstant;
            }
        }

        public record Negate() implements Expr {
            private static double valueOfConstant;

            public void negateInit(double value) {
                valueOfConstant = -value;
            }

            @Override
            public double evaluate() {
                return valueOfConstant;
            }
        }



    }
}
