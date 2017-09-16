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

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody>{


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
        try {
            String uuid = RandomUtils.getRandomValue(16);
            Field field = value.getClass().getDeclaredField("key");
            field.setAccessible(true);
            field.set(value,uuid);
            CodingUtils.encodingByPublicKey(value,uuid);
            json = gson.toJson(value);
            Log.e(TAG,"AES加密json: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
    }
}
