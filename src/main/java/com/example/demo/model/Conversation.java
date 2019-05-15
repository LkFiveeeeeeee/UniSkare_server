package com.example.demo.model;

import lombok.Data;

@Data
public class Conversation {
    private int conversationId;
    private String userId;
    private String otherId;
}
