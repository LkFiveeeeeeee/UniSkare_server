package com.example.demo.model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class momentShow {
    int momentId;
    String userId;
    String userName;
    String avatar;
    Timestamp time;
    String content;
    boolean islike;
    String pic;
    int likeNum;
}
