package edu.hw2;


public sealed interface Expr {
    double evaluate();

    record Constant(double incomeValue) implements Expr {

        @Override
        public double evaluate() {
            return incomeValue;
        }
    }

    public record Negate(Expr incomeValue) implements Expr {

        @Override
        public double evaluate() {
            return -incomeValue.evaluate();
        }
    }
    //Тут я остановился

    public record Exponent(Expr incomeBaseOfExponent, double incomeDegreeOfExponent) implements Expr {

        public Exponent {
            double resultOfExponent = Math.pow(incomeBaseOfExponent.evaluate(), incomeDegreeOfExponent);
            if (resultOfExponent != Math.pow(incomeBaseOfExponent.evaluate(), incomeDegreeOfExponent)) {
                throw new RuntimeException("NaN, wrong numbers, ExponentError");
            }
        }

        @Override
        public double evaluate() {
            /*if(Math.pow(baseOfExponent,degreeOfExponent) != Math.pow(baseOfExponent,degreeOfExponent)){
                throw new CommandLine.ExecutionException(,"NaN");
            }*/
            return Math.pow(incomeBaseOfExponent.evaluate(), incomeDegreeOfExponent);
        }
    }

    public record Addition(Expr incomeFirstAddition, Expr incomeSecondAddition) implements Expr {

        @Override
        public double evaluate() {
            //System.out.println(firstAddition + secondAddition);
            //System.out.println(incomeFirstAddition.evaluate());
            //System.out.println(incomeSecondAddition.evaluate());
            return incomeFirstAddition.evaluate() +  incomeSecondAddition.evaluate();
        }
    }

    public record Multiplication(Expr incomeFirstMultiplication, Expr incomeSecondMultiplication) implements Expr {

        @Override
        public double evaluate() {
            double firstMultiplication = incomeFirstMultiplication.evaluate();
            double secondMultiplication = incomeSecondMultiplication.evaluate();
            return firstMultiplication * secondMultiplication;
        }
    }
}
