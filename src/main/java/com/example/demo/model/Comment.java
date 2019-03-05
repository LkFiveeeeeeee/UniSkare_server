package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private int commentId;
    private int userId;
    private int score;
    private String content;
    private String pic;
    private Timestamp time;

    public Comment(){
        commentId = 0;
        userId = 0;
        score = 0;
        content = null;
        pic = null;
    }
}
