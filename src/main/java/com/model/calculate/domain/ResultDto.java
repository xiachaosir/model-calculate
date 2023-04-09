package com.model.calculate.domain;

/**
 * @author xiachao
 * @date create in 2020/11/8 9:01 下午
 */
public class ResultDto {

    //水土界面养分释放量
    private String etr;

    //养分流失量
    private String er;

    public String getEtr() {
        return etr;
    }

    public void setEtr(String etr) {
        this.etr = etr;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }
}
