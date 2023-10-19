package edu.hw2;

public sealed interface Expr {
    double evaluate();

    public record Constant(double incomeValue) implements Expr {
        private static double constantValue;
        public Constant{
            constantValue = incomeValue;
        }
        @Override
        public double evaluate() {
            return constantValue;
        }
    }
    public record Negate(Expr incomeValue) implements Expr {
        private static double negateValue;

        public Negate{
            negateValue = incomeValue.evaluate();
        }
        @Override
        public double evaluate() {
            return negateValue;
        }
    }
    //Тут я остановился
    public record Exponent(Expr baseOfExponent, double degreeOfExponent) implements Expr {

        @Override
        public double evaluate() {
            return 0;
        }
    }
    public record Addition() implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
    public record Multiplication() implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
}
