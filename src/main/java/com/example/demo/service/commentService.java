package com.example.demo.service;

import com.example.demo.mapper.SCrelationMapper;
import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.skillMapper;
import com.example.demo.model.Comment;
import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentService {
    @Autowired
    private commentMapper commentMapper;
    @Autowired
    private SCrelationMapper sCrelationMapper;
    @Autowired
    private skillMapper skillMapper;

    public String insertComment(Comment comment){
        commentMapper.insertComment(comment);
        int keyId = comment.getCommentId();
        sCrelationMapper.insertSCrelation(keyId,comment.getSkillId());
        Skill skill = skillMapper.selectSkillBySkillId(comment.getSkillId());
        skillMapper.plusSkillCommentNum(comment.getSkillId());
        double score = (skill.getScore() * skill.getCommentNum() + comment.getScore())
                /(skill.getCommentNum() + 1);
        skillMapper.updateSkillScore(score,comment.getSkillId());
        return ConstValue.INSERT_SUCCESS;
    }

    public List<Comment> getComments(int skillId){
        return commentMapper.selectCommentInfo(skillId);
    }

    public String deleteComment(int commentId){
        int skillId = sCrelationMapper.findSkillId(commentId);
        Skill skill = skillMapper.selectSkillBySkillId(skillId);
        Comment comment = commentMapper.selectCommentByCommentId(commentId);
        double score = skill.getScore() * skill.getCommentNum() - comment.getScore();
        score = score/(skill.getCommentNum() - 1);
        skillMapper.minusSkillCommentNum(skillId);
        skillMapper.updateSkillScore(score,skillId);
        sCrelationMapper.deleteSCrelation(commentId,skillId);
        if(commentMapper.deleteComment(commentId) != 0){
            return ConstValue.DELETE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }
}
