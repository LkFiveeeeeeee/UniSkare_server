package com.example.demo.service;

import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.model.ConstantValue.ConstValue;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    /*
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
    }*/

    public int updateAvatarAndName(String avatar,String name,String id){
        int result = userMapper.updateAvatarAndnickName(name,avatar,id);
        return result;
    }

    public String followSomeone(String userId, String followId){
        int result = relationMapper.insertUserRelation(followId,userId);
        if(result != 0){
            userMapper.updateFollowsNum(userId);
            userMapper.updateFansNum(followId);
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String deleteFollowRelation(String userId,String followId){
        int result = relationMapper.deleteUserRelation(followId,userId);
        if(result != 0){
            userMapper.decreaseFollowsNum(userId);
            userMapper.decreaseFansNum(followId);
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public PageInfo<userInfo> findFollows(int page, String userId){
        PageHelper.startPage(page,10);
        List<userInfo> users  = relationMapper.findFollows(userId);
        PageInfo<userInfo> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    public PageInfo<userInfo> findFans(int page,String userId){
        PageHelper.startPage(page,10);
        List<userInfo> users = relationMapper.findFans(userId);
        PageInfo<userInfo> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    public PageInfo<momentShow> findUserMoments(int page,String userId){
        PageHelper.startPage(page,10);
        List<momentShow> momentShows = momentMapper.selectMomentByUserId(userId);
        int[] likes = mUrelationMapper.findMoments(userId);
        for(momentShow momentShow:momentShows){
            for(int likeId:likes){
                if(momentShow.getMomentId() == likeId){
                    momentShow.setIslike(true);
                }else{
                    momentShow.setIslike(false);
                }
            }
        }
        PageInfo<momentShow> pageInfo = new PageInfo<>(momentShows);
        return pageInfo;
    }

    public PageInfo<skillShow> findUserSkills(int page,String userId){
        PageHelper.startPage(page,10);
        List<skillShow> skillShows = skillMapper.selectSkillByUserId(userId);
        PageInfo<skillShow> pageInfo = new PageInfo<skillShow>(skillShows);
        return pageInfo;
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
        int result = starSkillMapper.insertStarRelation(skillId,userId);
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
