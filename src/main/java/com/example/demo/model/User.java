package com.example.demo.model;


import lombok.Data;

@Data
public class User {
    private String uni_uuid;
    private String uni_avatarUrl;
    private String uni_nickName;
    private int uni_sex;   // 1 => male  2 =》 female
    private int uni_momentNum;
    private int uni_fansNum;
    private int uni_followsNum;
    private String uni_indiSign;
    private boolean uni_isStu;
    private boolean uni_passPhone;
    private boolean change_nickName;
    private boolean change_avatar;


}
