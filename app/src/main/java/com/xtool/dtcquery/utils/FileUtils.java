package com.xtool.dtcquery.utils;

import android.content.Context;

/**
 * Created by xtool on 2017/8/31.
 */

public class FileUtils {

    public static String getCacheDir(Context context) {
        return context.getCacheDir().toString();
    }
}
