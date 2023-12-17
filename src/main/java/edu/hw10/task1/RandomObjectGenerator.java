package edu.hw10.task1;

import edu.hw10.ErrorLogger;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RandomObjectGenerator {
    private static boolean isMinAnnotation = false;
    private static boolean isMaxAnnotation = false;
    private static boolean isNullAnnotation = false;
    private static Integer minValue = null;
    private static Integer maxValue = null;
    private static final String ERROR_CONFLICT_BORDERS = "Conflict of minimal & maximal borders";

    private static Boolean randomizeFiftyPercent() {
        return Math.ceil(Math.random()) == 0;
    }


    private static void parseAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == NotNull.class) {
                isNullAnnotation = true;
            } else if (annotation.annotationType() == Min.class) {
                Min minAnnotation = (Min) annotation;
                minValue = minAnnotation.value();
                isMinAnnotation = true;
            } else if (annotation.annotationType() == Max.class) {
                Max maxAnnotation = (Max) annotation;
                maxValue = maxAnnotation.value();
                isMaxAnnotation = true;
            }
        }
    }

    public <T> T nextObject(Class<T> clazz) {
        try {

            if (clazz.isRecord()) {
                var recordComponents = clazz.getRecordComponents();

                var componentTypes = new Class[recordComponents.length];
                var componentValues = new Object[recordComponents.length];
                Field[] fields = clazz.getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {
                    var field = fields[i];
                    var recordComponent = recordComponents[i];
                    var componentType = recordComponent.getType();
                    Object value = null;
                    Annotation[] annotations = field.getAnnotations();

                    parseAnnotations(annotations);

                    if (!isMinAnnotation && !isMaxAnnotation && !isNullAnnotation) {
                        if (randomizeFiftyPercent()) {
                            value = generateValueInRange(componentType, minValue, maxValue);
                        }
                    } else {
                        value = generateValueInRange(componentType, minValue, maxValue);
                    }
                    componentTypes[i] = componentType;
                    componentValues[i] = value;
                }

                return clazz.getDeclaredConstructor(componentTypes).newInstance(componentValues);
            } else {
                Constructor<T> constructor = clazz.getDeclaredConstructor();
                T obj = constructor.newInstance();

                constructor.setAccessible(true);

                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);

                    Annotation[] annotations = field.getDeclaredAnnotations();
                    Object value;

                    parseAnnotations(annotations);

                    if (!isNullAnnotation) {
                        if (randomizeFiftyPercent()) {
                            value = generateValueInRange(field.getType(), minValue, maxValue);
                        } else {
                            value = null;
                        }
                    } else {
                        value = generateValueInRange(field.getType(), minValue, maxValue);
                    }

                    field.set(obj, value);
                }
                return obj;

            }

        } catch (Exception e) {
            ErrorLogger.createLogError(e.getMessage());
            return (T) generateValueInRange(clazz, null, null);
        }
    }

    public <T> T nextObject(Class<T> clazz, String methodName) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T obj = constructor.newInstance();

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Object[] args = new Object[parameterTypes.length];

                    for (int i = 0; i < parameterTypes.length; i++) {
                        args[i] = generateValueInRange(parameterTypes[i], null, null);
                    }

                    method.setAccessible(true);
                    method.invoke(obj, args);
                }
            }

            return obj;
        } catch (Exception e) {
            ErrorLogger.createLogError(e.getMessage());

        }

        return null;
    }

    private Object generateValueInRange(Class<?> type, Integer minValue, Integer maxValue) {
        Object resultOfGeneration;

        if (minValue != null && maxValue != null && minValue > maxValue) {
            throw new RuntimeException(ERROR_CONFLICT_BORDERS);
        }

        resultOfGeneration = switch (type.getSimpleName()) {
            case "boolean", "Boolean" -> generateBoolean(minValue, maxValue);
            case "int", "Integer" -> generateInteger(minValue, maxValue);
            case "char", "Character" -> generateCharacter(minValue, maxValue);
            case "byte", "Byte" -> generateByte(minValue, maxValue);
            case "short", "Short" -> generateShort(minValue, maxValue);
            case "long", "Long" -> generateLong(minValue, maxValue);
            case "float", "Float" -> generateFloat(minValue, maxValue);
            case "double", "Double" -> generateDouble(minValue, maxValue);
            case "String" -> generateString(minValue, maxValue);
            default -> throw new RuntimeException("Unsupported type");
        };

        return resultOfGeneration;
    }

    private Boolean generateBoolean(Integer minValue, Integer maxValue) {
        int min = (minValue != null ? 0 : minValue);
        int max = (maxValue != null ? 1 : maxValue);
        if (min != 0 && max == 0) {
            throw new RuntimeException(ERROR_CONFLICT_BORDERS);
        } else {
            if (min == 0 && max != 0) {
                return randomizeFiftyPercent();
            } else {
                return (min != 0);
            }

        }
    }

    private Integer generateInteger(Integer minValue, Integer maxValue) {
        int min = minValue != null ? minValue : Integer.MIN_VALUE;
        int max = maxValue != null ? maxValue : Integer.MAX_VALUE;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private Character generateCharacter(Integer minValue, Integer maxValue) {
        char min = minValue != null ? (char) minValue.intValue() : Character.MIN_VALUE;
        char max = maxValue != null ? (char) maxValue.intValue() : Character.MAX_VALUE;
        return (char) (Math.random() * (max - min + 1) + min);
    }

    private Byte generateByte(Integer minValue, Integer maxValue) {
        byte min = minValue != null ? minValue.byteValue() : Byte.MIN_VALUE;
        byte max = maxValue != null ? maxValue.byteValue() : Byte.MAX_VALUE;
        return (byte) (Math.random() * (max - min + 1) + min);
    }

    private Short generateShort(Integer minValue, Integer maxValue) {
        short min = minValue != null ? minValue.shortValue() : Short.MIN_VALUE;
        short max = maxValue != null ? maxValue.shortValue() : Short.MAX_VALUE;
        return (short) (Math.random() * (max - min + 1) + min);
    }

    private Long generateLong(Integer minValue, Integer maxValue) {
        long min = minValue != null ? minValue.longValue() : Long.MIN_VALUE;
        long max = maxValue != null ? maxValue.longValue() : Long.MAX_VALUE;
        return (long) (Math.random() * (max - min + 1) + min);
    }

    private Float generateFloat(Integer minValue, Integer maxValue) {
        float min = minValue != null ? minValue.floatValue() : Float.MIN_VALUE;
        float max = maxValue != null ? maxValue.floatValue() : Float.MAX_VALUE;
        return (float) (Math.random() * (max - min + 1) + min);
    }

    private Double generateDouble(Integer minValue, Integer maxValue) {
        double min = minValue != null ? minValue.doubleValue() : Double.MIN_VALUE;
        double max = maxValue != null ? maxValue.doubleValue() : Double.MAX_VALUE;
        return Math.random() * (max - min + 1) + min;
    }

    private String generateString(Integer minValue, Integer maxValue) {
        int min = minValue != null ? minValue : 0;
        int max = maxValue != null ? maxValue : Integer.MAX_VALUE;
        int length = (int) (Math.random() * (max - min + 1) + min);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (Math.random() * Byte.MAX_VALUE);
            sb.append(c);
        }
        return sb.toString();
    }

}

