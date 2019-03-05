package com.example.demo.service;

import com.example.demo.mapper.momentMapper;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Moment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class momentService {
    @Autowired
    momentMapper momentMapper;

    public String insertMoment(Moment moment){
        int result = momentMapper.insertMoment(moment);
        if(result == 1){
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public String updateMoment(Moment moment){
        int result = momentMapper.insertMoment(moment);
        if(result != 0){
            return ConstValue.UPDATE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public List<Moment> getAllMoment(){
        return momentMapper.selectAllMoments();
    }

    public String deleteMoment(int momentId){
        int result = momentMapper.deleteMomentById(momentId);
        if(result != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }
}
