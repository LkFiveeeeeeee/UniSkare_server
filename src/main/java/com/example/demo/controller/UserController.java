package com.example.demo.controller;

import com.example.demo.model.CodeResponse;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.model.Skill;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.imageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private imageService imageService;

    private String wxAppid = "wxc9c276e49f2795b7";
    private String wxSecret = "cd8c771af0d123028d7d9801b15c10b7";

    private Logger logger = LoggerFactory.getLogger(UserService.class);


    @RequestMapping(value = "/getLoginCode",method = RequestMethod.POST)
    public BaseResponse verityLoginCode(@RequestParam("code") String code) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String params = "appid=" + wxAppid +"&secret="+wxSecret+"&js_code="+code+"&grant_type=authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?"+params;
        String response = restTemplate.getForObject(url,String.class);
        logger.info("response:" + response);
        ObjectMapper mapper = new ObjectMapper();
        CodeResponse codeResponse = mapper.readValue(response,CodeResponse.class);
        userService.register(codeResponse.getOpenid());
        return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/user/register"
                , codeResponse);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(@RequestParam("userId") String userId,
                              @RequestParam("nickName") String nickName,
                              @RequestParam("avatarUrl") String avatarUrl){
        String result = userService.login(userId,nickName,avatarUrl);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/login",
                null);
        return baseResponse;
    }

    @RequestMapping(value = "/information/{id}",method = RequestMethod.GET)
    public BaseResponse getUserInfo(@PathVariable("id") String id){
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
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/information/updateMomentNum",method = RequestMethod.POST)
    public BaseResponse updateMomentNum(@RequestParam("infoNum") int num,@RequestParam("id") String id){
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
    public BaseResponse updateFansNum(@RequestParam("infoNum") int num,@RequestParam("id") String id){
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
    public BaseResponse updateFollowsNum(@RequestParam("infoNum") int num,@RequestParam("id") String id){
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
                                                @RequestParam("id") String id){
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
    public BaseResponse getFollowsInfo(@PathVariable("id") String id){
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
    public BaseResponse getFansInfo(@PathVariable("id") String id){
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
    public BaseResponse getMoments(@PathVariable("id") String userId){
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
    public BaseResponse getSkills(@PathVariable("id") String userId){
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

    @RequestMapping(value = "/{userId}/follow/{followId}",method = RequestMethod.POST)
    public BaseResponse followSomeone(@PathVariable("userId") String userId,@PathVariable("followId") String followId){
        String result = userService.fowllowSomeone(userId,followId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/{userId}/follow/{followId}",
                null);
        return baseResponse;
    }

    @RequestMapping(value = "/{userId}/unfollow/{followId}",method = RequestMethod.DELETE)
    public BaseResponse deleteFollowRelation(@PathVariable("userId") String userId, @PathVariable("followId") String followId){
        String result = userService.deleteFollowRelation(userId,followId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/{userId}/unfollow/{followId}",
                null);
        return baseResponse;
    }

    @RequestMapping(value = "/{userId}/likes/{momentId}",method = RequestMethod.POST)
    public BaseResponse insertLikesRelation(@PathVariable("userId") String userId,
                                             @PathVariable("momentId") int momentId){
        String result = userService.insertLikeMoment(userId,momentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/{userId}/likes/{momentId}",
                null);
        return baseResponse;
    }


    @RequestMapping(value = "/{userId}/cancels/{momentId}",method = RequestMethod.DELETE)
    public BaseResponse deleteLikesRelation(@PathVariable("userId") String userId,
                                            @PathVariable("momentId") int momentId){
        String result = userService.deleteLikeMoment(userId,momentId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/{userId}/cancels/{momentId}",
                null);
        return baseResponse;
    }


    @RequestMapping(value = "/{userId}/star/{skillId}",method = RequestMethod.POST)
    public BaseResponse starSkill(@PathVariable("userId") String userId,
                                            @PathVariable("skillId") int skillId){
        String result = userService.starSkill(userId,skillId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/{userId}/star/{skillId}",
                null);
        return baseResponse;
    }

    @RequestMapping(value = "/{userId}/unstar/{skillId}",method = RequestMethod.DELETE)
    public BaseResponse unstarSkill(@PathVariable("userId") String userId,
                                  @PathVariable("skillId") int skillId){
        String result = userService.unstarSkill(userId,skillId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/{userId}/unstar/{momentId}",
                null);
        return baseResponse;
    }

    @RequestMapping(value = "/avatar",method = RequestMethod.POST)
    public String updateAvatar(@RequestParam("file")MultipartFile file,
                                     @RequestParam("id") String id){
        String result = imageService.upLoadUserPhoto(file,id);
        int index = result.indexOf('&');
        String avatarUrl = result.substring(index+1);
        result = result.substring(0,index);
        if(!result.equals(ConstValue.UPDATE_SUCCESS)){
            //TODO 错误处理
        }
        return avatarUrl;
    }

    @RequestMapping(value = "/nickName",method = RequestMethod.POST)
    public BaseResponse updateNickName(@RequestParam("nickName") String nickName,
                                       @RequestParam("id") String id){
        String result = userService.updateNickName(nickName,id);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/avatar",
                null);
        if(!result.equals(ConstValue.UPDATE_SUCCESS)){
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/{userId}/update",method = RequestMethod.POST)
    public BaseResponse loginUpdate(@PathVariable("userId") String userId,
                                    @RequestParam("avatar") String avatar,
                                    @RequestParam("nickName") String nickName){
        String result = userService.updateNameAndAvatar(nickName,avatar,userId);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                result,
                "/user/avatar",
                null);
        if(!result.equals(ConstValue.UPDATE_SUCCESS)){
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
        }
        return baseResponse;
    }
}
