package com.xtool.dtcquery.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.xtool.dtcquery.utils.CodingUtils;
import com.xtool.dtcquery.utils.RandomUtils;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.RequestBody;
import retrofit2.Converter;


/**
 * Created by xtool on 2017/8/23.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {


    private static final String TAG = "JsonRequest";

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        String json = null;
        String uuid = RandomUtils.getRandomValue(16);
        try {
            Class<? extends Object> clazz = value.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getType().toString().endsWith("Integer") && !field.getType().toString().endsWith("String")) {
                    Field[] objectFields = field.getType().getDeclaredFields();
                    for (Field objectField : objectFields) {
                        objectField.setAccessible(true);
                        Object object = field.get(value);
                        if (object != null && !object.equals("")) {
                            if (objectField.getName().equals("key")) {
                                objectField.set(field.get(value), uuid);
                            }
                        }

                    }
                } else {
                    field.setAccessible(true);
                    if (field.getName().equals("key")) {
                        field.set(value, uuid);
                    }
                }
            }
            Log.e(TAG, "before AES加密json: " + gson.toJson(value));
            CodingUtils.encodingByPublicKey(value, uuid);
            json = gson.toJson(value);
            Log.e(TAG, "AES加密json: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
    }
}