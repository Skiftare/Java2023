package edu.hw11;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class Main {
    private static byte[] getByteCode() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/java/edu/hw11/utils/Fibonacci.class");
        byte[] byteCode = fis.readAllBytes();
        fis.close();
        return byteCode;
    }

    public static void main(String[] args)
        throws IOException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException,
        IllegalAccessException, InstantiationException {
        byte[] bytecode = getByteCode(); // Получите байт-код из файла, например, с помощью FileInputStream

        MyClassLoader myClassLoader = new MyClassLoader();
        // Загрузите класс с помощью ClassLoader
        Class<?> myClass =
            myClassLoader.loadClass("Fibonacci"); // Здесь "com.example" - пакет вашего класса, "MyClass" - имя вашего класса

        // Получите объект Method для вызова статической функции
        Method staticMethod = myClass.getMethod("fibonacci",
            int.class
        ); // Здесь "myStaticMethod" - имя вашей статической функции, String.class и int.class - типы параметров функции, если таковые имеются

        // Вызовите статическую функцию
        System.out.println(staticMethod.invoke(
            null,
            6
        ));
    }
}
