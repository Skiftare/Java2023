package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    @DisplayName("Тест из условия")
    void testThatGetHardExpressionAndReturnedResultOfIt(){
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        assertEquals(res.evaluate(), 37);
    }

    @Test
    @DisplayName("Тест для констант")
    void testThatGetConstantNumberAndReturnedCreatedConstant(){
        double valueOfConstant = 2.457;
        var constant = new Expr.Constant(valueOfConstant);
        assertEquals(constant.evaluate(), valueOfConstant);
    }

    @Test
    @DisplayName("Тест для обратного отностильно сложения элемента")
    void testThatGetNumberInClassAndReturnedCreatedNegateConstant(){
        double valueOfNegate = 2.457;
        var negate = new Expr.Negate(new Expr.Constant(valueOfNegate));
        assertEquals(negate.evaluate(), -valueOfNegate);
    }

    @Test
    @DisplayName("Тест на сложение двух чисел")
    void testThatGetTwoNumbersAsClassesAndReturnedAdditionOfThem(){
        double incomeFst = 2.34;
        double incomeSec = 34589.56;
        var constFst = new Expr.Constant(incomeFst);
        var constSec = new Expr.Constant(incomeSec);

        var sumOfIncomes = new Expr.Addition(constFst, constSec);
        assertEquals(sumOfIncomes.evaluate(),incomeFst + incomeSec);

        sumOfIncomes = new Expr.Addition(new Expr.Constant(incomeSec), new Expr.Constant(incomeFst));
        assertEquals(sumOfIncomes.evaluate(),incomeFst + incomeSec);

    }

    @Test
    @DisplayName("Тест на умножение двух положительных чисел")
    void testThatGetTwoPositiveNumbersAndReturnedMultiplication(){
        double incomeFst = 2.34;
        double incomeSec = 34589.56;
        var constFst = new Expr.Constant(incomeFst);
        var constSec = new Expr.Constant(incomeSec);

        var multOfIncomes = new Expr.Multiplication(constFst, constSec);
        assertEquals(multOfIncomes.evaluate(),incomeFst * incomeSec);

        multOfIncomes = new Expr.Multiplication(new Expr.Constant(incomeSec), new Expr.Constant(incomeFst));
        assertEquals(multOfIncomes.evaluate(),incomeFst * incomeSec);

    }

    @Test
    @DisplayName("Тест на умножение двух отрицательных чисел")
    void testThatGetTwoNegativeNumbersAndReturnedMultiplication(){
        double incomeFst = -2.34;
        double incomeSec = -34589.56;
        var constFst = new Expr.Constant(incomeFst);
        var constSec = new Expr.Constant(incomeSec);

        var multOfIncomes = new Expr.Multiplication(constFst, constSec);
        assertEquals(multOfIncomes.evaluate(),incomeFst * incomeSec);

        multOfIncomes = new Expr.Multiplication(new Expr.Constant(incomeSec), new Expr.Constant(incomeFst));
        assertEquals(multOfIncomes.evaluate(),(-incomeFst) * (-incomeSec));

    }

    @Test
    @DisplayName("Тест на возведение в степень NaN")
    void testThatGetTwoNumbersAndReturnedExceptionOfExponent(){
        double baseNan = -7.525;
        double degreeNan = 1.490;
        Throwable ex = assertThrows(RuntimeException.class,()-> new Expr.Exponent(new Expr.Constant(baseNan), degreeNan));
        Assertions.assertEquals("NaN, wrong numbers, ExponentError", ex.getMessage());
    }

    @Test
    @DisplayName("Тест на возведение в степень ")
    void testThatGetTwoNumbersAndReturnedExponent(){
        double base = 7.525;
        double degree = 1.490;
        var res = new Expr.Exponent(new Expr.Constant(base), degree);
        Assertions.assertEquals(res.evaluate(), Math.pow(base,degree));
    }


}
