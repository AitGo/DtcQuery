package com.xtool.dtcquery.http.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.xtool.dtcquery.bean.DtcCustom;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by xtool on 2017/8/23.
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String StrRes = value.string();
        String result = StrRes.substring(1,StrRes.length()-1);
        List<DtcCustom> dtcCustom = gson.fromJson(StrRes,  new TypeToken<List<DtcCustom>>() {}.getType());
//        List<DtcCustom> list = GsonUtils.GsonToList(result,DtcCustom.class);
        return (T) dtcCustom;
    }
}
