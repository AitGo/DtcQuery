package com.xtool.dtcquery.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xtool on 2017/8/21.
 */

public class GsonUtils {

    // 将Json数组解析成相应的映射对象列表
     public static <T> List<T> GsonToList(String jsonData, Class<T> type) {
         Gson gson = new Gson();
         List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {}.getType());
         return result;
     }

}
