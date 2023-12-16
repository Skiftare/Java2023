package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random;



    public RandomObjectGenerator() {
        random = new Random();
    }

    public <T> T nextObject(Class<T> clazz) {
        try {

            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T obj = constructor.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            boolean isMinAnnotation = false;
            boolean isMaxAnnotation = false;
            Integer minValue = null;
            Integer maxValue  = null;
            for (Field field : fields) {
                field.setAccessible(true);

                Annotation[] annotations = field.getDeclaredAnnotations();
                Object value = null;
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == NotNull.class) {
                        value = generateNotNullValue(field.getType());

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
                if(isMaxAnnotation && isMinAnnotation){
                    value = generateValueInRange(field.getType(),minValue,maxValue);
                }
                else if(isMaxAnnotation){
                    value = generateMaxValue(field.getType(),maxValue);
                }
                else if(isMinAnnotation){
                    value = generateMinValue(field.getType(), minValue);
                }

                Class<?> fieldType = field.getType();
                if(value == null){
                    value = generateValue(fieldType);
                }
                field.set(obj, value);
            }

            return obj;
        }
        catch (Exception e){
            return (T) generateValue(clazz);
        }
    }

    private Object generateValueInRange(Class<?> type, int minValue, int maxValue) {
            if (type == int.class || type == Integer.class) {
                return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
            } else {
                return generateValue(type);
            }
        }

        private Object generateNotNullValue(Class<?> type) {
            Object value = generateValue(type);
            while (value == null) {
                value = generateValue(type);
            }
            return value;
        }

        private Object generateMinValue(Class<?> type, int minValue) {
            Object value = generateValue(type);
            while ((Integer) value < minValue) {
                value = generateValue(type);
            }
            return value;
        }

        private Object generateMaxValue(Class<?> type, int maxValue) {
            Object value = generateValue(type);
            while ((Integer) value > maxValue) {
                value = generateValue(type);
            }
            return value;
        }
        private Object generateValue(Class<?> type) {
            if (type == boolean.class || type == Boolean.class) {
                return Math.random() >= 0.5;
            } else if (type == byte.class || type == Byte.class) {
                return (byte) (Math.random() * 256 - 128);
            } else if (type == short.class || type == Short.class) {
                return (short) (Math.random() * 65536 - 32768);
            } else if (type == int.class || type == Integer.class) {
                return (int) (Math.random() * Integer.MAX_VALUE);
            } else if (type == long.class || type == Long.class) {
                return (long) (Math.random() * Long.MAX_VALUE);
            } else if (type == float.class || type == Float.class) {
                return (float) Math.random();
            } else if (type == double.class || type == Double.class) {
                return Math.random();
            } else if (type == char.class || type == Character.class) {
                return (char) (Math.random() * 65536);
            } else if (type == String.class) {
                int length = (int) (Math.random() * 10) + 1;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    char c = (char) (Math.random() * 26 + 'a');
                    sb.append(c);
                }
                return sb.toString();
            } else {
                try {
                    Constructor<?> constructor = type.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

/*
    private <T> T generateRecord(Class<T> clazz)
        throws Exception {
        Constructor<T> constructor = clazz.getConstructor(Object.class);
        constructor.setAccessible(true);
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        Object[] args = new Object[parameterAnnotations.length];
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            args[i] = generateValueFromAnnotations(annotations);
        }
        return constructor.newInstance(args);
    }

    private <T> T generatePojo(Class<T> clazz)
        throws Exception {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            Object[] args = new Object[parameterAnnotations.length];
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                args[i] = generateValueFromAnnotations(annotations);
            }
            return constructor.newInstance(args);
        }
        catch (Exception e){
            return generateRandomValue(clazz.)
        }
    }
    private <T> T generateObjectWithoutConstructor(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    private Object generateValueFromAnnotations(Annotation[] annotations) throws Exception {
        int mask = 0;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                mask|=1;
            } else if (annotation instanceof Min) {
                Min minAnnotation = (Min) annotation;
                //return generateRandomValueGreaterOrEqual(Object.class, minAnnotation.value());
                mask|=2;
            } else if (annotation instanceof Max) {
                mask|=3;
            }
        }
        return generateRandomValue(Object.class);
    }

    private Object generateRandomValue(Class<?> type) throws Exception {
        if (type.isPrimitive()) {
            return generateRandomPrimitiveValue(type);
        } else if (type == String.class) {
            return generateRandomString();
        } else {
            return nextObject(type);
        }
    }

    private Object generateRandomPrimitiveValue(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return random.nextInt();
        } else if (type == long.class || type == Long.class) {
            return random.nextLong();
        } else if (type == double.class || type == Double.class) {
            return random.nextDouble();
        } else if (type == float.class || type == Float.class) {
            return random.nextFloat();
        } else if (type == boolean.class || type == Boolean.class) {
            return random.nextBoolean();
        } else if (type == char.class || type == Character.class) {
            return (char) random.nextInt(Character.MAX_VALUE + 1);
        } else if (type == short.class || type == Short.class) {
            return (short) random.nextInt(Short.MAX_VALUE + 1);
        } else if (type == byte.class || type == Byte.class) {
            return (byte) random.nextInt(Byte.MAX_VALUE + 1);
        }
        return null;
    }

    private String generateRandomString() {
        int length = random.nextInt(10) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    private Object generateRandomValueGreaterOrEqual(Class<?> type, int minValue) {
        if (type == int.class || type == Integer.class) {
            return random.nextInt(Integer.MAX_VALUE - minValue + 1) + minValue;
        } else if (type == long.class || type == Long.class) {
            return random.nextLong() + minValue;
        } else if (type == double.class || type == Double.class) {
            return random.nextDouble() * (Double.MAX_VALUE - minValue) + minValue;
        } else if (type == float.class || type == Float.class) {
            return random.nextFloat() * (Float.MAX_VALUE - minValue) + minValue;
        } else if (type == short.class || type == Short.class) {
            return (short) (random.nextInt(Short.MAX_VALUE - minValue + 1) + minValue);
        } else if (type == byte.class || type == Byte.class) {
            return (byte) (random.nextInt(Byte.MAX_VALUE - minValue + 1) + minValue);
        }
        return null;
    }

    private Object generateRandomValueLessOrEqual(Class<?> type, int maxValue) {
        if (type == int.class || type == Integer.class) {
            return random.nextInt(maxValue + 1);
        } else if (type == long.class || type == Long.class) {
            return random.nextLong() + (maxValue + 1);
        } else if (type == double.class || type == Double.class) {
            return random.nextDouble() * maxValue;
        } else if (type == float.class || type == Float.class) {
            return random.nextFloat() * maxValue;
        } else if (type == short.class || type == Short.class) {
            return (short) (random.nextInt(maxValue + 1));
        } else if (type == byte.class || type == Byte.class) {
            return (byte) (random.nextInt(maxValue + 1));
        }
        return null;
    }
}
*/
