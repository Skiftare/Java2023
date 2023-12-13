package edu.hw11;

import edu.hw11.utils.ArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class Task2 {

    @Test
    public void testThatBla() throws Exception {
        // Используем библиотеку ByteBuddy для изменения поведения метода sum
        Class<?> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class) // определяем подкласс от класса ArithmeticUtils
            .method(ElementMatchers.named("sum")) // указываем метод, который хотим изменить
            .intercept(Advice.to(SumInterceptor.class)) // применяем интерцептор SumInterceptor
            .make() // создаем новый класс
            .load(ArithmeticUtils.class.getClassLoader()) // загружаем класс в ClassLoader
            .getLoaded(); // получаем загруженный класс

        // создаем экземпляр измененного класса
        ArithmeticUtils modifiedUtils = (ArithmeticUtils) dynamicType.getDeclaredConstructor().newInstance();

        // проверяем результат измененного метода sum
        int result = modifiedUtils.sum(2, 3);
        assertEquals(result,6);

    }
    static class SumInterceptor {


        @Advice.OnMethodExit
        static void exit(@Advice.Return(readOnly = false) int result, @Advice.Argument(0) int a, @Advice.Argument(1) int b) {
            result = a * b; // заменяем результат сложения на умножение
        }
    }
}
