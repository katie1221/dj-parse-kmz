package com.example.djpointdemo.util;

import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qzz
 * @date 2025/8/28
 */
public class VoUtils {
    public VoUtils() {
    }

    public static <T> T copyProperties(Object source, T target) {
        Field[] targetFields = getAllFields(target);
        Field[] sourceFields = getAllFields(source);
        Field[] var4 = sourceFields;
        int var5 = sourceFields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            String fieldFirstUpcase = capitalFor(field.getName());
            String getMethod = "get" + fieldFirstUpcase;
            String setMethod = "set" + fieldFirstUpcase;
            if (!StringUtils.isNotEmpty(field.getName()) || !"serialVersionUID".equals(field.getName())) {
                try {
                    Object value = source.getClass().getMethod(getMethod).invoke(source);
                    List<String> fieldNameList = (List) Arrays.stream(targetFields).map(Field::getName).distinct().collect(Collectors.toList());
                    if (fieldNameList.contains(field.getName()) && value != null) {
                        target.getClass().getMethod(setMethod, value instanceof List ? List.class : value.getClass()).invoke(target, value);
                    }
                } catch (Exception var13) {
                }
            }
        }

        return target;
    }

    public static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();

        ArrayList fieldList;
        for(fieldList = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
            fieldList.addAll(new ArrayList(Arrays.asList(clazz.getDeclaredFields())));
        }

        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    public static String capitalFor(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        } else {
            if (name.length() == 1) {
                name = name.toUpperCase();
            } else {
                String str = name.substring(0, 2);
                if (Character.isLowerCase(str.charAt(0))) {
                    if (Character.isUpperCase(str.charAt(1))) {
                        return name;
                    }

                    String var10000 = name.substring(0, 1).toUpperCase();
                    name = var10000 + name.substring(1);
                }
            }

            return name;
        }
    }
}
