package com.example.demo.controller;

import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.service.momentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/moment")
public class MomentController {
    @Autowired
    private momentService momentService;

    //TODO 修改
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse insertMoment(@ModelAttribute Moment moment){
        String result = momentService.insertMoment(moment);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/moment/insert"
                , null);
        if(result.equals(ConstValue.INSERT_SUCCESS)){
            return baseResponse;
        }else {
            baseResponse.setStatus(Code.METHOD_NOT_ALLOWED);
            return baseResponse;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BaseResponse updateMoment(@ModelAttribute Moment moment){
        String result = momentService.updateMoment(moment);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/moment/update"
                , null);
        if(result.equals(ConstValue.UPDATE_SUCCESS)){
            return baseResponse;
        }else {
            baseResponse.setStatus(Code.METHOD_NOT_ALLOWED);
            return baseResponse;
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public BaseResponse getMoments(){
        List<Moment> moments = momentService.getAllMoment();
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/moment/update"
                , null);
        if(moments != null){
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
            baseResponse.setData(moments);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/delete/{momentId}",method = RequestMethod.DELETE)
    public BaseResponse deleteMoment(@PathVariable("momentId") int momentId){
        String result = momentService.deleteMoment(momentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/moment"
                , null);
        if(result.equals(ConstValue.DELETE_SUCCESS)){
            return baseResponse;
        }else {
            baseResponse.setStatus(Code.METHOD_NOT_ALLOWED);
            return baseResponse;
        }
    }




}
