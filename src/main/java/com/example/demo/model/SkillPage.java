package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SkillPage {
    private int skillId;
    private String userId;
    private String cover;
    private String video;
    private String displayPic;
    private String title;
    private String content;
    private double price;
    private String unit;
    private int model; // 0 represent 1->1   1 represent 1->all
    private String fullType;
    private String type;
    private double score;
    private Timestamp date;
    private int commentNum;
    private boolean isStar;

    public SkillPage(){}
}
