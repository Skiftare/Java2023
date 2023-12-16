package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void testThatCreateClassWithByteBuddyAndReturnedHelloMessage() {
        String expectedResult = "Hello, ByteBuddy!";
        try
        { Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(Task1Test.class.getClassLoader())
            .getLoaded();

        Object instance = dynamicType.getDeclaredConstructor().newInstance();

        Assertions.assertEquals(expectedResult, instance.toString());

        } catch (Exception e) {
           ErrorLogger.createLogError(e.getMessage());
        }
    }
}
