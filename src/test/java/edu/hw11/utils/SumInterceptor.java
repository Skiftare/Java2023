package edu.hw11.utils;

import net.bytebuddy.asm.Advice;

public class SumInterceptor {
    @Advice.OnMethodExit
    static void exit(@Advice.Return(readOnly = false) int result, @Advice.Argument(0) int a, @Advice.Argument(1) int b) {
        result = a * b; // заменяем результат сложения на умножение
    }
}
