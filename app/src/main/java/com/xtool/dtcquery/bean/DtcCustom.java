package com.xtool.dtcquery.bean;

/**
 * Created by xtool on 2017/8/22.
 */

public class DtcCustom extends Dtc {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "DtcCustom{" +
                "key='" + key + '\'' +
                '}';
    }
}
