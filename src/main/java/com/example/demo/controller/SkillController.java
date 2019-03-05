package com.example.demo.controller;

import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Response.BaseResponse;
import com.example.demo.model.Response.Code;
import com.example.demo.model.Skill;
import com.example.demo.service.skillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private skillService skillService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse insertSkill(@ModelAttribute Skill skill){
        String result = skillService.insertSkill(skill);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/skill/insert"
                , null);
        if(result.equals(ConstValue.INSERT_SUCCESS)){
            return baseResponse;
        }else {
            baseResponse.setStatus(Code.METHOD_NOT_ALLOWED);
            return baseResponse;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BaseResponse updateSkill(@ModelAttribute Skill skill){
        String result = skillService.updateSkill(skill);
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , result
                , "/skill/update"
                , null);
        if(result.equals(ConstValue.INSERT_SUCCESS)){
            return baseResponse;
        }else {
            baseResponse.setStatus(Code.METHOD_NOT_ALLOWED);
            return baseResponse;
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public BaseResponse getAllSkills(){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/skill/update"
                , null);
        List<Skill> skills = skillService.getAllSkill();
        if(skills != null){
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
            baseResponse.setData(skills);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }


    @RequestMapping(value = "/all/{type}",method = RequestMethod.GET)
    public BaseResponse getSkillsByType(@PathVariable("type") String type){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/skill"
                , null);
        List<Skill> skills = skillService.getSkillByType(type);
        if(skills != null){
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
            baseResponse.setData(skills);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/{skillId}",method = RequestMethod.GET)
    public BaseResponse getSkillsBySkillId(@PathVariable("skillId") int skillId){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/skill"
                , null);
        Skill skills = skillService.getSkillBySkillId(skillId);
        if(skills != null){
            baseResponse.setMessage(ConstValue.QUERY_SUCCESS);
            baseResponse.setData(skills);
        }else{
            baseResponse.setStatus(Code.NOT_FOUND);
        }
        return baseResponse;
    }

    @RequestMapping(value = "/delete/{skillId}",method = RequestMethod.DELETE)
    public BaseResponse deleteSkillBySkillId(@PathVariable("skillId") int skillId){
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , Code.NO_MESSAGE_AVAIABLE
                , "/skill"
                , null);
        String result = skillService.deleteSkill(skillId);
        if(result.equals(ConstValue.DELETE_SUCCESS)){
            baseResponse.setMessage(result);
        }else{
            baseResponse.setStatus(Code.NOT_ACCEPTABLE);
        }
        return baseResponse;
    }



}
