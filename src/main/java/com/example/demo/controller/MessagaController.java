package com.example.demo.controller;

import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Message;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.model.messageInfo;
import com.example.demo.service.messageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessagaController {
    @Autowired
    private messageInfoService messageInfoService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResponse insertComment(@ModelAttribute Message message,String userId,String otherId){
        int id = messageInfoService.insertMessageInfo(message,userId,otherId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                ConstValue.INSERT_SUCCESS,
                "/comment/insert",
                id);
        return baseResponse;
    }

    @RequestMapping(value = "/select/{userId}",method = RequestMethod.GET)
    public BaseResponse selectChatInfo(@PathVariable("userId") String id){
        List<messageInfo> infoList = messageInfoService.getChatList(id);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                ConstValue.QUERY_SUCCESS,
                "/comment/insert",
                infoList);
        return baseResponse;
    }

}
