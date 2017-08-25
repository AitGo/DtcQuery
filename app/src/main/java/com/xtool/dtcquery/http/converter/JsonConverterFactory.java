package com.xtool.dtcquery.http.converter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by xtool on 2017/8/23.
 */

public class JsonConverterFactory extends Converter.Factory{

    private final Gson gson;
    private Context context;

    private JsonConverterFactory(Gson gson,Context context) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
        this.context = context;
    }

    public static JsonConverterFactory create(Context context) {

        return create(new GsonBuilder().disableHtmlEscaping().create(),context);
    }

    public static JsonConverterFactory create(Gson gson,Context context) {
        return new JsonConverterFactory(gson,context);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonResponseBodyConverter<>(gson, adapter);  //响应
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(gson, adapter,context); //请求
    }
}
