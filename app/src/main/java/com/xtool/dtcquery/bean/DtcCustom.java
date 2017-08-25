package com.xtool.dtcquery.bean;

/**
 * Created by xtool on 2017/8/22.
 */

public class DtcCustom extends Dtc {

    private String aesKey;

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public String toString() {
        return "DtcCustom{" +
                "aesKey='" + aesKey + '\'' +
                '}';
    }
}
