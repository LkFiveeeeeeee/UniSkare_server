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
    @Autowired
    private MUrelationMapper mUrelationMapper;
    @Autowired
    private starSkillMapper starSkillMapper;


    public String register(String open_id){
        if(!checkExist(open_id)) {
            userMapper.insertUserOnlyPrimayKey(open_id);
        }
        return ConstValue.INSERT_SUCCESS;
    }
    //TODO 修正为正常版本
    public String login(String userId,String nickName,String avatar){
        User user = userMapper.findById(userId);
        user.setUni_nickName(nickName);
        user.setUni_avatarUrl(avatar);
        userMapper._insertUser(user);
        return ConstValue.LOGIN_SUCCESS;
    }

    public User getUserInfoById(String id){
        User user = userMapper.findById(id);
        return user;
    }


    //type
    //1 : momentNum
    //2 : fansNum
    //3 : followsNum
    public int updateInfoNum(String id, int num, int type){
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

    public int updateAvatarAndName(String avatar,String name,String id){
        int result = userMapper.updateAvatarAndnickName(name,avatar,id);
        return result;
    }

    public String fowllowSomeone(String userId,String followId){
        int result = relationMapper.insertUserRelation(followId,userId);
        if(result != 0){
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String deleteFollowRelation(String userId,String followId){
        int result = relationMapper.deleteUserRelation(followId,userId);
        if(result != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public List<User> findFollows(String userId){
        List<User> users = new ArrayList<>();
        String[] followsId = relationMapper.findFollows(userId);
        for(String id:followsId){
            users.add(userMapper.findById(id));
        }
        return users;
    }

    public List<User> findFans(String userId){
        List<User> users = new ArrayList<>();
        String [] fansId = relationMapper.findFans(userId);
        for(String id:fansId){
            users.add(userMapper.findById(id));
        }
        return users;
    }

    public List<Moment> findUserMoments(String userId){
        return momentMapper.selectMomentByUserId(userId);
    }

    public List<Skill> findUserSkills(String userId){
        return skillMapper.selectSkillByUserId(userId);
    }

    public String insertLikeMoment(String userId,int momentId){
        int result = mUrelationMapper.insertMUrelation(userId,momentId);
        if(result != 0){
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String deleteLikeMoment(String userId,int momentId){
        int result = mUrelationMapper.deleteRelation(userId,momentId);
        if(result != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String starSkill(String userId,int skillId){
        int result = starSkillMapper.insertSCrelation(skillId,userId);
        if(result != 0){
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String unstarSkill(String userId,int skillId){
        int result = starSkillMapper.deleteStarRelation(userId,skillId);
        if(result != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String updateAvatar(String avatar,String userId){
        User user = userMapper.findById(userId);
        userMapper.updateAvatar(avatar,userId);
        if(user.isChange_avatar()){
            return user.getUni_avatarUrl();
        }
        return null;
    }

    public String updateNickName(String nickName,String userId){
        int result = userMapper.updateNickName(nickName,userId);
        return (result!=0)?ConstValue.UPDATE_SUCCESS:ConstValue.OPERATION_FAIL;
    }

    public String updateNameAndAvatar(String nickName,String avatar,String userId){
        int result = userMapper.updateAvatarAndnickName(nickName,avatar,userId);
        return (result!=0)?ConstValue.UPDATE_SUCCESS:ConstValue.OPERATION_FAIL;
    }


    public boolean checkExist(String userId){
        return userMapper.checkUserExist(userId);
    }
}
