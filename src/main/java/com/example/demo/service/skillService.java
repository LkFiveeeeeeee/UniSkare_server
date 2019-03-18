package com.example.demo.service;

import com.example.demo.mapper.skillMapper;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Skill;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class skillService {
    @Autowired
    skillMapper skillMapper;

    public int insertSkill(Skill skill){
        int result = skillMapper.insertSkill(skill);
        if(result >= 0){
            return result;
        }else{
            return -1;
        }
    }

    public String updateSkill(Skill skill){
        int result = skillMapper.insertSkill(skill);
        if(result != 0){
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public List<Skill> getAllSkill(){
        return skillMapper.selectAllSkillOrderByTime();
    }

    public List<Skill> getSkillByType(String type){
        return skillMapper.selectSkillByType(type);
    }

    public Skill getSkillBySkillId(int skillId){
        return skillMapper.selectSkillBySkillId(skillId);
    }

    public String deleteSkill(int skillId){
        int result = skillMapper.deleteSkillBySkillId(skillId);
        if(result != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public int updateDisplayPic(String pic,int skillId){
        return skillMapper.updateDisplayPic(pic,skillId);
    }

    public String updateCover(String pic,int skillId){
        String cover = skillMapper.selectCover(skillId);
        skillMapper.updateCover(pic,skillId);
        return cover;
    }

    public String getDisUrl(int skillId){
        return skillMapper.selectDisplayPicBySkillId(skillId);
    }

    public int updateDeletePic(String pic,int skillId){
        return skillMapper.deleteSomePicUrl(pic,skillId);
    }
}
