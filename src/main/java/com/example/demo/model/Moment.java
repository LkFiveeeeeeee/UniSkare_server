package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Moment {
    private int momentId;
    private String userId;
    private String content;
    private String pic;
    private boolean canSee;
    private int likesNum;
    private Timestamp time;

    public Moment(){}
}
