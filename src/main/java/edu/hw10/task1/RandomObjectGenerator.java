package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random;

    public RandomObjectGenerator() {
        random = new Random();
    }

    public <T> T nextObject(Class<T> clazz) {
        try {
            // Проверяем, является ли класс record
            if (clazz.isRecord()) {
                return generateRecord(clazz);
            }

            // Иначе, класс POJO
            return generatePojo(clazz);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T generateRecord(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Получаем конструктор record
        Constructor<T> constructor = clazz.getDeclaredConstructor(Object[].class);
        constructor.setAccessible(true);

        // Получаем аннотации параметров конструктора
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();

        // Генерируем случайные значения для каждого параметра
        Object[] args = new Object[parameterAnnotations.length];
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            args[i] = generateValueFromAnnotations(annotations);
        }

        // Создаем и возвращаем объект record
        return constructor.newInstance(args);
    }

    private <T> T generatePojo(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Получаем конструктор или фабричный метод
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();

        // Генерируем случайные значения для каждого параметра
        Object[] args = new Object[parameterAnnotations.length];
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            args[i] = generateValueFromAnnotations(annotations);
        }

        // Создаем и возвращаем объект POJO
        return constructor.newInstance(args);
    }

    private Object generateValueFromAnnotations(Annotation[] annotations) {
        // Проверяем аннотации на присутствие и обработаем их значения, если они есть
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                return generateRandomValue();
            } else if (annotation instanceof Min) {
                Min minAnnotation = (Min) annotation;
                return generateRandomValueGreaterOrEqual(minAnnotation.value());
            } else if (annotation instanceof Max) {
                Max maxAnnotation = (Max) annotation;
                return generateMaxValue(maxAnnotation.value());
            }
        }

        // Если нет аннотаций, то возвращаем случайное значение по умолчанию
        return generateRandomValue();
    }


    private Object generateRandomValue(Class<?> type, Parameter parameter) throws Exception {
        if (type.isPrimitive()) {
            return generateRandomPrimitiveValue(type);
        } else if (type == String.class) {
            return generateRandomString(parameter);
        } else {
            Class<?> declaringClass = parameter.getDeclaringExecutable().getDeclaringClass();
            return nextObject(declaringClass, null); // Рекурсивный вызов для генерации вложенных объектов
        }
    }



    private Object generateRandomPrimitiveValue(Class<?> type) {
        return switch (type.getSimpleName()) {
            case "int", "Integer" -> random.nextInt();
            case "long", "Long" -> random.nextLong();
            case "double", "Double" -> random.nextDouble();
            case "float", "Float" -> random.nextFloat();
            case "boolean", "Boolean" -> random.nextBoolean();
            case "char", "Character" -> (char) random.nextInt(Character.MAX_VALUE + 1);
            case "short", "Short" -> (short) random.nextInt(Short.MAX_VALUE + 1);
            case "byte", "Byte" -> (byte) random.nextInt(Byte.MAX_VALUE + 1);
            default -> null;
        };

    }

    private String generateRandomString(Parameter parameter) {
        int length = parameter.getAnnotation(Max.class).value();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    private Object generateRandomValueWithAnnotations(Class<?> type, Parameter parameter) throws Exception {
        NotNull notNullAnnotation = parameter.getAnnotation(NotNull.class);
        if (notNullAnnotation != null) {
            return generateRandomValue(type, parameter);
        }

        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);
        if (minAnnotation != null && maxAnnotation != null) {
            int minValue = minAnnotation.value();
            int maxValue = maxAnnotation.value();
            if (minValue <= maxValue) {
                return generateRandomValueInRange(type, minValue, maxValue);
            }
        } else if (minAnnotation != null) {
            int minValue = minAnnotation.value();
            return generateRandomValueGreaterOrEqual(type, minValue);
        } else if (maxAnnotation != null) {
            int maxValue = maxAnnotation.value();
            return generateRandomValueLessOrEqual(type, maxValue);
        }

        return generateRandomValue(type, parameter);
    }

    private Object generateRandomValueInRange(Class<?> type, int minValue, int maxValue) {
        if (type == int.class || type == Integer.class) {
            return random.nextInt(maxValue - minValue + 1) + minValue;
        } else if (type == long.class || type == Long.class) {
            return random.nextInt((int) (maxValue - minValue + 1)) + minValue;
        } else if (type == double.class || type == Double.class) {
            return random.nextDouble() * (maxValue - minValue) + minValue;
        } else if (type == float.class || type == Float.class) {
            return random.nextFloat() * (maxValue - minValue) + minValue;
        } else if (type == short.class || type == Short.class) {
            return random.nextInt((maxValue - minValue + 1)) + minValue;
        } else if (type == byte.class || type == Byte.class) {
            return random.nextInt((maxValue - minValue + 1)) + minValue;
        }
        return 0;
    }

    private Object generateRandomValueGreaterOrEqual(Class<?> type, int minValue) {
        return switch (type.getSimpleName()) {
            case "int", "Integer" -> random.nextInt(Integer.MAX_VALUE - minValue + 1) + minValue;
            case "long", "Long" -> random.nextInt((int) (Long.MAX_VALUE - minValue + 1)) + minValue;
            case "double", "Double" -> random.nextDouble() * (Double.MAX_VALUE - minValue) + minValue;
            case "float", "Float" -> random.nextFloat() * (Float.MAX_VALUE - minValue) + minValue;
            case "short", "Short" -> random.nextInt((Short.MAX_VALUE - minValue + 1)) + minValue;
            case "byte", "Byte" -> random.nextInt((Byte.MAX_VALUE - minValue + 1)) + minValue;
            default -> 0;
        };
    }

    private Object generateRandomValueLessOrEqual(Class<?> type, int maxValue) {
        if (type == int.class || type == Integer.class) {
            return random.nextInt(maxValue + 1);
        } else if (type == long.class || type == Long.class) {
            return random.nextInt((int) (maxValue + 1));
        } else if (type == double.class || type == Double.class) {
            return random.nextDouble() * maxValue;
        } else if (type == float.class || type == Float.class) {
            return random.nextFloat() * maxValue;
        } else if (type == short.class || type == Short.class) {
            return random.nextInt(maxValue + 1);
        } else if (type == byte.class || type == Byte.class) {
            return random.nextInt(maxValue + 1);
        }
        return 0;
    }



}
