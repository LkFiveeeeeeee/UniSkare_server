package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class commentInfo {
    String avatar;
    String nickName;
    int commentId;
    String userId;
    int score;
    String content;
    String pic;
    Timestamp time;
}
