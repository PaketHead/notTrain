package com.dam.factory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SimpleFactory {

    private static <T> void assignValuesIfNeeded(Class<T> clazz, T result, List<String> defaultValues) {
        try {
            Field[] declaredFields = clazz.getDeclaredFields();
            int idx = 0;
            for (Field field : declaredFields) {
                if (field.getAnnotationsByType(WithDefault.class) != null) {
                    field.setAccessible(true);
                    Object val = field.get(result);
                    if (val == null) {
                        field.set(result, defaultValues.get(idx));
                        idx++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> List<String> getDefaultValues(Class<T> clazz) {
        List<String> result = new ArrayList<>();

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                WithDefault[] annotations = field.getAnnotationsByType(WithDefault.class);
                for (WithDefault annotation : annotations) {
                    result.add(annotation.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <T> T newInstance(Class<T> clazz) {
        T result = null;

        try {
            result = clazz.getDeclaredConstructor().newInstance();
            List<String> defaultValues = getDefaultValues(clazz);
            assignValuesIfNeeded(clazz, result, defaultValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
