package com.xtool.dtcquery.entity;

/**
 * Created by xtool on 2017/9/14.
 */

public class CarDTO {

    private String cname;

    private String ctype;

    private String cproduct;

    private String cdisplacement;

    private Integer cuid;

    private String key;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCproduct() {
        return cproduct;
    }

    public void setCproduct(String cproduct) {
        this.cproduct = cproduct;
    }

    public String getCdisplacement() {
        return cdisplacement;
    }

    public void setCdisplacement(String cdisplacement) {
        this.cdisplacement = cdisplacement;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
