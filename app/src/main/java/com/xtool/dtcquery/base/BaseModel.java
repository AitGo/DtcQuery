package com.xtool.dtcquery.base;

import android.content.Context;

import com.xtool.dtcquery.utils.SPUtils;

/**
 * Created by xtool on 2017/11/13.
 */

public interface BaseModel {

    String getParamFromSP(Context context, String key);

    void setParamToSP(Context context, String key, String value);
}
