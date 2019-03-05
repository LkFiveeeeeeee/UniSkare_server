package com.example.demo.controller;

import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.model.Skill;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(){
        String result = userService.login();
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/login",
                null);
        return baseResponse;
    }

    @RequestMapping(value = "/information/{id}",method = RequestMethod.GET)
    public BaseResponse getUserInfo(@PathVariable("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/"
                , null);
        User user = userService.getUserInfoById(id);
        if(user != null){
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
            baseResponse.setData(user);
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/updateMomentNum",method = RequestMethod.POST)
    public BaseResponse updateMomentNum(@RequestParam("infoNum") int num,@RequestParam("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/updateMomentNum"
                , null);
        int result = userService.updateInfoNum(id,num,1);
        if(result != 0){
            baseResponse.setMessage(ConstValue.UPDATE_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/updateFansNum",method = RequestMethod.POST)
    public BaseResponse updateFansNum(@RequestParam("infoNum") int num,@RequestParam("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/updateFansNum"
                , null);
        int result = userService.updateInfoNum(id,num,2);
        if(result != 0){
            baseResponse.setMessage(ConstValue.UPDATE_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/updateFollowsNum",method = RequestMethod.POST)
    public BaseResponse updateFollowsNum(@RequestParam("infoNum") int num,@RequestParam("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/updateFollowsNum"
                , null);
        int result = userService.updateInfoNum(id,num,3);
        if(result != 0){
            baseResponse.setMessage(ConstValue.UPDATE_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/avatarAndnickName",method = RequestMethod.POST)
    public BaseResponse updateAvatarAndnickName(@RequestParam("avatar") String avatar,
                                                @RequestParam("nickName") String nickName,
                                                @RequestParam("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/avatarAndnickName"
                , null);
        int result = userService.updateAvatarAndName(avatar,nickName,id);
        if(result != 0){
            baseResponse.setMessage(ConstValue.UPDATE_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/{id}/follows",method = RequestMethod.GET)
    public BaseResponse getFollowsInfo(@PathVariable("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/follows/"
                , null);
        List<User> userList = userService.findFollows(id);
        if(userList != null){
            baseResponse.setData(userList);
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/{id}/fans",method = RequestMethod.GET)
    public BaseResponse getFansInfo(@PathVariable("id") int id){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/fans/"
                , null);
        List<User> userList = userService.findFans(id);
        if(userList != null){
            baseResponse.setData(userList);
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/{id}/moments",method = RequestMethod.GET)
    public BaseResponse getMoments(@PathVariable("id") int userId){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/moments/"
                , null);
        List<Moment> moments = userService.findUserMoments(userId);
        if(moments != null){
            baseResponse.setData(moments);
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/{id}/skills",method = RequestMethod.GET)
    public BaseResponse getSkills(@PathVariable("id") int userId){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/information/skills/"
                , null);
        List<Skill> skills = userService.findUserSkills(userId);
        if(skills != null){
            baseResponse.setData(skills);
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }
}
