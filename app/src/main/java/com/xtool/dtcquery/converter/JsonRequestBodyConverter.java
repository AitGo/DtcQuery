package com.xtool.dtcquery.converter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.xtool.dtcquery.bean.DtcCustom;
import com.xtool.dtcquery.utils.AESUtil;
import com.xtool.dtcquery.utils.RSAUtils;
import com.xtool.dtcquery.utils.RandomUtils;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;



/**
 * Created by xtool on 2017/8/23.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody>{


    private static final String TAG = "JsonRequest";

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private Context context;

    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter,Context context) {
        this.gson = gson;
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String json = "{\"dcode\":\"1\",\"did\":0}";
        try {

            DtcCustom dtcCustom = (DtcCustom) value;
            Log.e(TAG,"加密前: " + dtcCustom.toString());

//            DtcCustom dtcCustom = gson.fromJson(value.toString(), DtcCustom.class);
            String uuid = RandomUtils.getRandomValue(16);

            String aesDcode = AESUtil.encrypt(dtcCustom.getDcode(),uuid);
            RSAPublicKey publicKey = RSAUtils.getPublicKeyFromAsset(context);
            String aesKey = RSAUtils.encryptByPublicKey(uuid,publicKey);
            dtcCustom.setAesKey(aesKey);
            dtcCustom.setDcode(aesDcode);
            Log.e(TAG,"uuid: " + uuid);
            Log.e(TAG,"RSA加密key: " + aesKey);
            Log.e(TAG,"AES加密code: " + aesDcode);

            json = gson.toJson(dtcCustom);
            Log.e(TAG,"AES加密json: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
    }
}
