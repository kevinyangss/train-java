package com.kevin.base.invoke;

import com.kevin.base.invoke.annotation.MyAnno;

/**
 * @ClassName Person
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-15 18:06
 */
public class Person {
    @MyAnno
    private String stra;
    private String strb;
    private String strc;

    public Person(String str1,String str2,String str3){
        super();
        this.stra = str1;
        this.strb = str2;
        this.strc = str3;
    }

    public String getStra() {
        return stra;
    }

    public void setStra(String stra) {
        this.stra = stra;
    }

    public String getStrb() {
        return strb;
    }

    public void setStrb(String strb) {
        this.strb = strb;
    }

    public String getStrc() {
        return strc;
    }

    public void setStrc(String strc) {
        this.strc = strc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "stra='" + stra + '\'' +
                ", strb='" + strb + '\'' +
                ", strc='" + strc + '\'' +
                '}';
    }
}
