package com.xtool.dtcquery.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by xtool on 2017/8/31.
 */

public class ContextUtil extends Application{
    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
