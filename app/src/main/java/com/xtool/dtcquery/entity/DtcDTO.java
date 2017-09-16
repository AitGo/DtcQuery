package com.xtool.dtcquery.entity;

/**
 * Created by xtool on 2017/8/22.
 */

public class DtcDTO {

    private String dcode;
    private String dname;
    private String dinfo;
    private String dcause;
    private String dfix;
    private String key;

    private Integer s;
    private Integer ps;

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
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
        return "DtcDTO{" +
                "key='" + key + '\'' +
                '}';
    }
}
