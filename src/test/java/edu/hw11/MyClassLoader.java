package edu.hw11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyClassLoader extends ClassLoader {
    private final Path classFilePath = Path.of("src/test/java/edu/hw11/utils/Fibonacci.class"); // Путь к файлу с байт-кодом класса

    public MyClassLoader() {

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = Files.readAllBytes(classFilePath);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class", e);
        }
    }
}
