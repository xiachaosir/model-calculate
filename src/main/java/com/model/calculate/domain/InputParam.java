package com.model.calculate.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 输入参数
 */
public class InputParam implements Serializable {


    //氮 磷
    private String type;

    //土壤渗透系数
    private String k;
    private BigDecimal kDec;

    //初始田面水位高度
    private String d0;
    private BigDecimal d0Dec;

    //田块面积
    private String s;
    private BigDecimal sDec;

    //降雨强度
    private String r;
    private BigDecimal rDec;

    //田埂高度
    private String ht;
    private BigDecimal htDec;

    //田面水浓度
    private String ct;
    private BigDecimal ctDec;

    //田面水初始浓度
    private String c0;
    private BigDecimal c0Dec;

    //输入参数之降水中养分浓度
    private String cr;
    private BigDecimal crDec;

    //输入参数之时间步长
    private String t;
    private BigDecimal tDec;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public BigDecimal getCrDec() {
        return crDec;
    }

    public void setCrDec(BigDecimal crDec) {
        this.crDec = crDec;
    }

    public String getC0() {
        return c0;
    }

    public void setC0(String c0) {
        this.c0 = c0;
    }

    public BigDecimal getC0Dec() {
        return c0Dec;
    }

    public void setC0Dec(BigDecimal c0Dec) {
        this.c0Dec = c0Dec;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public BigDecimal getCtDec() {
        return ctDec;
    }

    public void setCtDec(BigDecimal ctDec) {
        this.ctDec = ctDec;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public BigDecimal getkDec() {
        return kDec;
    }

    public void setkDec(BigDecimal kDec) {
        this.kDec = kDec;
    }

    public String getD0() {
        return d0;
    }

    public void setD0(String d0) {
        this.d0 = d0;
    }

    public BigDecimal getD0Dec() {
        return d0Dec;
    }

    public void setD0Dec(BigDecimal d0Dec) {
        this.d0Dec = d0Dec;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public BigDecimal getsDec() {
        return sDec;
    }

    public void setsDec(BigDecimal sDec) {
        this.sDec = sDec;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public BigDecimal getrDec() {
        return rDec;
    }

    public void setrDec(BigDecimal rDec) {
        this.rDec = rDec;
    }

    public String getHt() {
        return ht;
    }

    public void setHt(String ht) {
        this.ht = ht;
    }

    public BigDecimal getHtDec() {
        return htDec;
    }

    public void setHtDec(BigDecimal htDec) {
        this.htDec = htDec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
