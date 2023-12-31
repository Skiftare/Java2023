package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.utils.SumInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {

    @Test
    @DisplayName("Перехватывание и изменение функции через ByteBuddy")
    public void testThatGetSumClassAndReturnedMultiplyingClass() throws Exception {
        //given: Origin class (a+b) & class with method to inject (a*b)
        Class<?> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class) // подкласс
            .method(ElementMatchers.named("sum")) // метод
            .intercept(Advice.to(SumInterceptor.class))
            .make() // новый класс
            .load(ArithmeticUtils.class.getClassLoader()) // загружаем
            .getLoaded();

        //when: we create class with "+" operation
        ArithmeticUtils modifiedUtils = (ArithmeticUtils) dynamicType.getDeclaredConstructor().newInstance();

        // then: injection works and "+" is replaced by "*";
        int result = modifiedUtils.sum(20, 3);
        Assertions.assertEquals(result, 20*3);

    }

}
