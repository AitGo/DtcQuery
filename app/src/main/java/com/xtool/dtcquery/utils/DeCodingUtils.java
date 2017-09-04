package com.xtool.dtcquery.utils;

import java.lang.reflect.Field;

/**
 * Created by xtool on 2017/9/2.
 */

public class DeCodingUtils {

    private static String keyName = "key";

    public static <T> void DeCoding(T t, String uuid) throws IllegalAccessException {
        Class<? extends Object> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object object = field.get(t);
            if (object != null && !object.equals("")) {
                if (!field.getName().equals(keyName)) {
                    //AES
                    //判断类型
                    String type = field.getType().toString();
                    if (type.endsWith("String")) {
                        String encrypt = AESUtil.decrypt(object.toString(), uuid);
                        field.set(t, encrypt);
                    } else if (type.endsWith("int") || type.endsWith("Integer")) {
//						Integer i = (Integer)object;
//						String string = String.valueOf(i);
//						String encrypt = AESUtil.encrypt(string, uuid);
//						field.set(t, Integer.valueOf(encrypt));
                    }
                }
            }
        }
    }
}
