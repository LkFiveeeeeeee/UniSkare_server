package com.example.demo.service;

import com.example.demo.mapper.*;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import com.example.demo.model.Skill;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private relationMapper relationMapper;
    @Autowired
    private momentMapper momentMapper;
    @Autowired
    private skillMapper skillMapper;

    //TODO 修正为正常版本
    public String login(){
        User user = new User();
        user.setUni_avatarUrl("11111");
        user.setUni_uuid(new Long(111));
        user.setUni_passPhone(false);
        user.setUni_followsNum(1324);
        user.setUni_indiSign("我爱吃米饭");
        user.setUni_nickName("秃头码农");
        userMapper._insertUser(user);
        return ConstValue.LOGIN_SUCCESS;
    }

    public User getUserInfoById(int id){
        User user = userMapper.findById(id);
        return user;
    }


    //type
    //1 : momentNum
    //2 : fansNum
    //3 : followsNum
    public int updateInfoNum(int id, int num, int type){
        int result = 0;
        switch (type){
            case 1:
                result = userMapper.updateMomentNum(id,num);
                break;
            case 2:
                result = userMapper.updateFansNum(id,num);
                break;
            case 3:
                result = userMapper.updateFollowsNum(id,num);
                break;
            default:
                break;
        }
        return result;
    }

    public int updateAvatarAndName(String avatar,String name,int id){
        int result = userMapper.updateAvatarAndnickName(name,avatar,id);
        return result;
    }

    public List<User> findFollows(int userId){
        List<User> users = new ArrayList<>();
        int[] followsId = relationMapper.findFollows(userId);
        for(int id:followsId){
            users.add(userMapper.findById(id));
        }
        return users;
    }

    public List<User> findFans(int userId){
        List<User> users = new ArrayList<>();
        int [] fansId = relationMapper.findFans(userId);
        for(int id:fansId){
            users.add(userMapper.findById(id));
        }
        return users;
    }

    public List<Moment> findUserMoments(int userId){
        return momentMapper.selectMomentByUserId(userId);
    }

    public List<Skill> findUserSkills(int userId){
        return skillMapper.selectSkillByUserId(userId);
    }
}
