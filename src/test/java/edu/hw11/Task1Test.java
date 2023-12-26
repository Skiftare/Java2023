package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("Создание класса с переобпределнным методом .toString()")
    void testThatCreateClassWithByteBuddyAndReturnedHelloMessage() {
        //given: string to generate
        String expectedResult = "Hello, ByteBuddy!";
        try {
            //when: we create class with byteBuddy
            Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(expectedResult))
            .make()
            .load(Task1Test.class.getClassLoader())
            .getLoaded();

        Object instance = dynamicType.getDeclaredConstructor().newInstance();
        //then: we get expected string from class.toString() method
        Assertions.assertEquals(expectedResult, instance.toString());

        } catch (Exception e) {
           ErrorLogger.createLogError(e.getMessage());
        }
    }
}
