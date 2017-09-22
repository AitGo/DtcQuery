package com.xtool.dtcquery.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.xtool.dtcquery.entity.MessageDTO;
import com.xtool.dtcquery.utils.Base64Utils;
import com.xtool.dtcquery.utils.CodingUtils;
import com.xtool.dtcquery.utils.ContextUtil;
import com.xtool.dtcquery.utils.RSAUtils;

import java.io.IOException;
import java.lang.reflect.Field;
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
    public T convert(ResponseBody value) throws IOException{
        String StrRes = value.string();
        Log.e("返回的Json", StrRes);
        MessageDTO<T> messageDTO = gson.fromJson(StrRes, new TypeToken<MessageDTO<T>>() {}.getType());
        if(messageDTO.getCode() != 0) {
            throw new IOException(messageDTO.getMsg());
        }
        String dataJson = gson.toJson(messageDTO.getData());
        List list = (List) adapter.fromJson(dataJson);
        for (Object object : list) {
            try {
                Field field = object.getClass().getDeclaredField("key");
                field.setAccessible(true);
                Object key = field.get(object);
                String publicKey = RSAUtils.getKey(ContextUtil.getInstance().getAssets().open("publicKey.cer"));
                byte[] dekey = Base64Utils.decode(key.toString());
                byte[] keybyte = RSAUtils.decryptByPublicKey(dekey, publicKey);
                String AESKey = new String(keybyte);
                CodingUtils.DeCoding(object,AESKey);
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
        return (T) list;
    }
}
