package edu.hw10.Task1.classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();
    private static final int STRING_BOUND = 7;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public <T> T nextObject(Class<T> clazz) {
        return nextObject(clazz, null);
    }

    public <T> T nextObject(Class<T> clazz, String methodName) {
        if (methodName != null) {
            try {
                Method method = clazz.getDeclaredMethod(methodName);
                return (T) method.invoke(clazz.getDeclaredConstructor().newInstance(), generateArgs(method));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try {
            var constructors = clazz.getDeclaredConstructors();
            int len = constructors.length;
            int numOfConstructor = RANDOM.nextInt(len);
            var constructor = constructors[numOfConstructor];

            return (T) constructor.newInstance(generateArgs(constructor));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] generateArgs(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        final int fieldsLen = parameters.length;
        Object[] values = new Object[fieldsLen];

        for (int i = 0; i < fieldsLen; ++i) {
            values[i] = generateValue(parameters[i]);
        }

        return values;
    }

    private Object[] generateArgs(Method method) {
        Parameter[] parameters = method.getParameters();
        final int parametersLen = parameters.length;
        Object[] values = new Object[parametersLen];

        for (int i = 0; i < parametersLen; ++i) {
            values[i] = generateValue(parameters[i].getType());
        }

        return values;
    }

    private Object generateValue(Parameter parameter) {
        Class<?> type = parameter.getType();
        Annotation[] annotations = parameter.getDeclaredAnnotations();
        Object generatedValue = generateValue(type);
        Integer minValue = null;
        Integer maxValue = null;

        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                assert generatedValue != null;
                return generatedValue;
            } else if (annotation instanceof Min) {
                minValue = ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                maxValue = ((Max) annotation).value();
            }
        }

        return generateInteger(generatedValue, minValue, maxValue);
    }

    private Object generateValue(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return RANDOM.nextInt();
        } else if (type == String.class) {
            return generateString();
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    private int generateInteger(Object value, Integer minValue, Integer maxValue) {
        if (minValue != null && maxValue != null) {
            return RANDOM.nextInt(minValue, maxValue);
        } else if (minValue != null) {
            return Math.max((Integer) value, minValue);
        } else if (maxValue != null) {
            return Math.min((Integer) value, maxValue);
        }

        return (int) value;
    }

    private String generateString() {
        int randomLength = RANDOM.nextInt(STRING_BOUND);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < randomLength; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
