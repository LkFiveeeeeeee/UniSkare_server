package com.example.demo.service;

import com.example.demo.mapper.MUrelationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.momentMapper;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import com.example.demo.model.momentShow;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class momentService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    momentMapper momentMapper;
    @Autowired
    MUrelationMapper mUrelationMapper;

    public int insertMoment(Moment moment){
        int result = momentMapper.insertMoment(moment);
        if(result == 1){
            userMapper.updateMomentNum(moment.getUserId());
            return moment.getMomentId();
        }else{
            return -1;
        }
    }

    public momentShow selectOneMomentById(int momentId){
        return momentMapper.selectOneMoment(momentId);
    }

    public String updateMoment(Moment moment){
        int result = momentMapper.updateMoment(moment);
        if(result != 0){
            return ConstValue.UPDATE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public PageInfo<momentShow> getAllMoment(int index,String userId){

        PageHelper.startPage(index,10);
        List<momentShow> momentShows = momentMapper.selectAllMoments();
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

    public String deleteMoment(int momentId,String userId){
        int result = momentMapper.deleteMomentById(momentId);
        if(result != 0){
            userMapper.decreaseMomentNum(userId);
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }


    public int updateDisplayPic(String pic,int momentId){
        return momentMapper.updateDisplayPic(pic,momentId);
    }


    public String getDisUrl(int momentId){
        return momentMapper.selectDisplayPicByMomentId(momentId);
    }

    public int updateDeletePic(String pic,int momentId){
        return momentMapper.deleteSomePicUrl(pic,momentId);
    }
}
