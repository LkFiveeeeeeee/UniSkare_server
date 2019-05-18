package com.example.demo.service;

import com.example.demo.mapper.skillMapper;
import com.example.demo.mapper.starSkillMapper;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillPage;
import com.example.demo.model.skillShow;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class skillService {
    @Autowired
    skillMapper skillMapper;
    @Autowired
    starSkillMapper starSkillMapper;

    public int insertSkill(Skill skill){
        int result = skillMapper.insertSkill(skill);
        if(result >= 0){
            return skill.getSkillId();
        }else{
            return -1;
        }
    }

    public String updateSkill(Skill skill){
        int result = skillMapper.updateSkill(skill);
        if(result != 0){
            return ConstValue.INSERT_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public PageInfo<skillShow> getAllSkill(int page){
        PageHelper.startPage(page,10);
        List<skillShow> skillShows = skillMapper.selectAllSkillOrderByTime();
        PageInfo<skillShow> pageInfo = new PageInfo<skillShow>(skillShows);
        return pageInfo;
    }

    public PageInfo<skillShow> getSkillByFullType(int page, int type){
        PageHelper.startPage(page,10);
        List<skillShow> skillShows = new ArrayList<>();
        if(type == 0){
            skillShows = skillMapper.selectSkillOrderByTime("学科技能");
        }else if(type == 1){
            skillShows = skillMapper.selectSkillOrderByTime("兴趣爱好");
        }else{
            skillShows = skillMapper.selectSkillOrderByTime("情感经验");
        }
        PageInfo<skillShow> pageInfo = new PageInfo<skillShow>(skillShows);
        return pageInfo;
    }

    public List<Skill> getSkillByType(String type){
        return skillMapper.selectSkillByType(type);
    }

    public SkillPage getSkillBySkillId(String userId,int skillId){
        SkillPage skill = skillMapper.selectSkillBySkillId(skillId);
        boolean result = starSkillMapper.checkRecordingExist(userId,skillId);
        if(result){
            skill.setStar(true);
        }else{
            skill.setStar(false);
        }
        return skill;
    }

    public String deleteSkill(int skillId){
        int result = skillMapper.deleteSkillBySkillId(skillId);
        if(result != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }

    public PageInfo<skillShow> searchSkill(int page,String skillName){
        PageHelper.startPage(page,10);
        List<skillShow> skillShows = skillMapper.searchSkill(skillName);
        PageInfo<skillShow> pageInfo = new PageInfo<skillShow>(skillShows);
        return pageInfo;
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
