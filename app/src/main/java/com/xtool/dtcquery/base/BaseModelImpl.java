package com.xtool.dtcquery.base;

import android.content.Context;

import com.xtool.dtcquery.utils.SPUtils;

/**
 * Created by xtool on 2017/11/13.
 */

public class BaseModelImpl implements BaseModel{
    @Override
    public String getParamFromSP(Context context,String key) {
        return (String) SPUtils.getParam(context,key,"");
    }

    @Override
    public void setParamToSP(Context context, String key, String value) {
        SPUtils.setParam(context,key,value);
    }
}
