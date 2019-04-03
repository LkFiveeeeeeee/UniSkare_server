package com.example.demo.controller;

import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.model.momentShow;
import com.example.demo.service.imageService;
import com.example.demo.service.momentService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/moment")
public class MomentController {
    @Autowired
    private momentService momentService;
    @Autowired
    private imageService imageService;

    //TODO 修改
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse insertMoment(@ModelAttribute Moment moment){
        int data = momentService.insertMoment(moment);
        String result = ConstValue.OPERATION_FAIL;
        if(data != -1){
            result = ConstValue.INSERT_SUCCESS;
        }
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/moment/insert"
                , data);
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

    @RequestMapping(value = "/all/{userId}",method = RequestMethod.GET)
    public BaseResponse getMoments(@PathVariable("userId") String userId, @RequestParam("page") int page){
        PageInfo<momentShow> moments = momentService.getAllMoment(page,userId);
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


    @RequestMapping(value = "/edit/{momentId}/delDisplayPic",method = RequestMethod.POST)
    public BaseResponse deleteSomePic(@PathVariable("momentId") int momentId,
                                      @RequestParam("deleteUrl") String deleteUrl){
        String result = imageService.deleteMomentDisPic(deleteUrl,momentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/moment/edit/{momentId}/delDisplayPic"
                , null);
        if(!result.equals(ConstValue.UPDATE_SUCCESS)){
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
            baseResponse.setMessage(Code.NO_MESSAGE_AVAIABLE);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/update/{momentId}/displayPic",method = RequestMethod.POST)
    public BaseResponse updatePic(@PathVariable("momentId") int momentId,
                                  @RequestParam("file") MultipartFile file){
        String result = imageService.upLoadMomentPhoto(file,momentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/moment/update/{momentId}/displayPic"
                , null);
        if(!result.equals(ConstValue.UPDATE_SUCCESS)){
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
            baseResponse.setMessage(Code.NO_MESSAGE_AVAIABLE);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/{momentId}/info",method = RequestMethod.GET)
    public BaseResponse selectMomentByMomentId(@PathVariable("momentId") int momentId){
        Moment moment = momentService.selectMomentByMomentId(momentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , ConstValue.OPERATION_FAIL
                , "/moment/update/{momentId}/displayPic"
                , null);
        if(moment != null){
            baseResponse.setData(moment);
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
        }
        return baseResponse;
    }


}
