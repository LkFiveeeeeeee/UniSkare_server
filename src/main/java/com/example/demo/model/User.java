package com.example.demo.model;

public class User {
    private Long uni_uuid;
    private String uni_avatarUrl;
    private String uni_nickName;
    private int uni_momentNum;
    private int uni_fansNum;
    private int uni_followsNum;
    private String uni_indiSign;
    private boolean uni_isStu;
    private boolean uni_passPhone;

    public User(){}

    public boolean isUni_passPhone() {
        return uni_passPhone;
    }

    public void setUni_passPhone(boolean uni_passPhone) {
        this.uni_passPhone = uni_passPhone;
    }

    public boolean isUni_isStu() {
        return uni_isStu;
    }

    public void setUni_isStu(boolean uni_isStu) {
        this.uni_isStu = uni_isStu;
    }

    public String getUni_indiSign() {
        return uni_indiSign;
    }

    public void setUni_indiSign(String uni_indiSign) {
        this.uni_indiSign = uni_indiSign;
    }

    public int getUni_followsNum() {
        return uni_followsNum;
    }

    public void setUni_followsNum(int uni_followsNum) {
        this.uni_followsNum = uni_followsNum;
    }

    public int getUni_fansNum() {
        return uni_fansNum;
    }

    public void setUni_fansNum(int uni_fansNum) {
        this.uni_fansNum = uni_fansNum;
    }

    public int getUni_momentNum() {
        return uni_momentNum;
    }

    public void setUni_momentNum(int uni_momentNum) {
        this.uni_momentNum = uni_momentNum;
    }

    public String getUni_nickName() {
        return uni_nickName;
    }

    public void setUni_nickName(String uni_nickName) {
        this.uni_nickName = uni_nickName;
    }

    public Long getUni_uuid() {
        return uni_uuid;
    }

    public void setUni_uuid(Long uni_uuid) {
        this.uni_uuid = uni_uuid;
    }

    public String getUni_avatarUrl() {
        return uni_avatarUrl;
    }

    public void setUni_avatarUrl(String uni_avatarUrl) {
        this.uni_avatarUrl = uni_avatarUrl;
    }
}
