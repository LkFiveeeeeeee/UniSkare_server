package com.example.demo.model;

import lombok.Data;

@Data
public class messageInfo {
    int conversationId;
    String friendId;
    String friendName;
    String friendHeadUrl;
    String content;
    String timeStr;
    int unread;
    boolean isTouchMove;

    public messageInfo(int conversationId,String friendId,String friendName,String friendHeadUrl,String content,
                       String timeStr,int unread){
        this.conversationId = conversationId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendHeadUrl = friendHeadUrl;
        this.content = content;
        this.timeStr = timeStr;
        this.unread = unread;
        isTouchMove = false;
    }
}
