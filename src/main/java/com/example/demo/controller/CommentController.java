package com.example.demo.controller;


import com.example.demo.model.Comment;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private commentService commentService;

    @RequestMapping(value = "/insert/{skillId}", method = RequestMethod.POST)
    public BaseResponse insertComment(@ModelAttribute Comment comment, @PathVariable("skillId") int skillId){
        String result = commentService.insertComment(comment,skillId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/comment/insert",
                null);
        if(!result.equals(ConstValue.INSERT_SUCCESS)){
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/belongToSkill/{skillId}",method = RequestMethod.GET)
    public BaseResponse getCommentBySkillId(@PathVariable("skillId") int skillId){
        List<Comment> comments = commentService.getComments(skillId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                Code.NO_MESSAGE_AVAIABLE,
                "/comment/belongToSkill/",
                null);

        if(comments != null){
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
            baseResponse.setData(comments);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/delete/{commentId}",method = RequestMethod.POST)
    public BaseResponse deleteComment(@PathVariable("commentId") int commentId){
        String result = commentService.deleteComment(commentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/delete/{commentId}",
                null);
        if(!result.equals(ConstValue.DELETE_SUCCESS)){
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
        }
        return baseResponse;
    }
}
