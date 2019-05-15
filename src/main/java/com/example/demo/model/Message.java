package com.example.demo.model;

import lombok.Data;

@Data
public class Message {
    private int conversationId;
    private String content;
    private String date;
}
