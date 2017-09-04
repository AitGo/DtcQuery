package com.xtool.dtcquery.bean;

/**
 * Created by xtool on 2017/8/22.
 */

public class DtcCustom {

    private int did;
    private String dcode;
    private String dname;
    private String dinfo;
    private String dcause;
    private String dfix;
    private String key;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDinfo() {
        return dinfo;
    }

    public void setDinfo(String dinfo) {
        this.dinfo = dinfo;
    }

    public String getDcause() {
        return dcause;
    }

    public void setDcause(String dcause) {
        this.dcause = dcause;
    }

    public String getDfix() {
        return dfix;
    }

    public void setDfix(String dfix) {
        this.dfix = dfix;
    }

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
