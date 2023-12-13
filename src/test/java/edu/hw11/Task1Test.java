package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class Task1Test {
    @Test
    void helloWorld() {
        try{
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(Task1Test.class.getClassLoader())
            .getLoaded();

        Object instance = dynamicType.newInstance();
        System.out.println(instance);
        assertEquals(instance.toString(), "Hello, ByteBuddy!");
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
