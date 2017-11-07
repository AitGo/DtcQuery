package com.xtool.dtcquery.utils;

import android.util.Log;

import com.xtool.dtcquery.entity.Key;
import com.xtool.dtcquery.entity.UserDTO;

import java.lang.reflect.Field;

/**
 * Created by xtool on 2017/9/2.
 */

public class CodingUtils {

	private static String keyName = "key";

	private static String publicKeyName = "publicKey.cer";

	public static <T> void encodingByPublicKey(T t,String uuid) throws Exception {
		Class<? extends Object> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			Object object = field.get(t);
			if(object != null && !object.equals("")) {
				if(field.getName().equals(keyName)) {
					//RSA
//					String publicKey = RSAUtils.getKey(ContextUtil.getInstance().getAssets().open(publicKeyName));
					byte[] endata = RSAUtils.encryptByPublicKey(uuid.getBytes(), Key.key);
					String encode = Base64Utils.encode(endata);
					field.set(t, encode);
				}else {
					//AES
					String type = field.getType().toString();
					if(type.endsWith("String")) {
						String encrypt = AESUtil.encrypt(object.toString(), uuid);
						field.set(t, encrypt);
					}else if (!type.endsWith("String") && !type.endsWith("Integer") && !type.endsWith("long")) {
						encodingByPublicKey(field.get(t),uuid);
					}
				}
			}

		}

	}

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
					} else if (type.endsWith("CarDTO")) {
						DeCoding(((UserDTO)t).getCarDTO(),uuid);
					}
				}
			}
		}
	}
}
