package com.xtool.dtcquery.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.xtool.dtcquery.bean.DtcCustom;
import com.xtool.dtcquery.bean.Message;
import com.xtool.dtcquery.utils.AESUtil;
import com.xtool.dtcquery.utils.Base64Utils;
import com.xtool.dtcquery.utils.ContextUtil;
import com.xtool.dtcquery.utils.DeCodingUtils;
import com.xtool.dtcquery.utils.GsonUtils;
import com.xtool.dtcquery.utils.RSAUtils;

import java.io.IOException;
import java.lang.reflect.Type;
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
        Message<List<DtcCustom>> message = gson.fromJson(StrRes, new TypeToken<Message<List<DtcCustom>>>() {}.getType());
        if(message.getCode() != 0) {
            throw new IOException(message.getMsg());
        }
        List<DtcCustom> list = message.getData();
        for (DtcCustom dtcCustom : list) {
            String key = dtcCustom.getKey();
            String publicKey = null;
            try {
                publicKey = RSAUtils.getKey(ContextUtil.getInstance().getAssets().open("publicKey.cer"));
                byte[] dekey = Base64Utils.decode(key);
                byte[] keybyte = RSAUtils.decryptByPublicKey(dekey, publicKey);
                String AESKey = new String(keybyte);
                DeCodingUtils.DeCoding(dtcCustom,AESKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) list;
    }
}
