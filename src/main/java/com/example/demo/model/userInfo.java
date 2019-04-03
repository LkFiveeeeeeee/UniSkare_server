package com.example.demo.model;

import lombok.Data;

@Data
public class userInfo {
    String userId;
    String userName;
    String userMotto;
    String userAvatar;
    boolean isFollow;
    int sex;
}
