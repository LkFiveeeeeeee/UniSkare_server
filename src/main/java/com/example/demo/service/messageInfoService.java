package com.example.demo.service;

import com.example.demo.mapper.messageMapper;
import com.example.demo.model.Conversation;
import com.example.demo.model.Message;
import com.example.demo.model.messageInfo;
import com.example.demo.model.unread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageInfoService {
    @Autowired
    messageMapper messageMapper;

    public int insertMessageInfo(Message message,String userId,String otherId){
        int result = messageMapper.checkExistOnConversation(userId,otherId);
        if(result != 0){
            int con = messageMapper.selectConversationId(userId,otherId);
            message.setConversationId(con);
        }
        messageMapper.insertUser(message);
        int conversationId = message.getConversationId();
        if(result == 0){
            Conversation conversation = new Conversation();
            conversation.setConversationId(conversationId);
            conversation.setUserId(userId);
            conversation.setOtherId(otherId);
            messageMapper.insertConversation(conversation);
        }
        unread unread = new unread();
        unread.setConversationId(conversationId);
        unread.setUserId(otherId);
        unread.setUnread(1);
        unread read = new unread();
        read.setConversationId(conversationId);
        read.setUserId(userId);
        read.setUnread(0);
        messageMapper.insertUnread(unread);
        messageMapper.insertUnread(read);
        return conversationId;
    }

    //TODO 分页

    public List<messageInfo> getChatList(String id){
        return messageMapper.selectMessage(id);
    }
}
