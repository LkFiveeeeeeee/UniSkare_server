package com.example.demo.mapper;

import com.example.demo.model.Skill;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface skillMapper {
    @Insert({"INSERT INTO skill VALUE(#{skillId},#{userId},#{cover}," +
            "#{video},#{displayPic},#{title},#{content},#{price}," +
            "#{unit},#{model},#{type},#{score},#{date})" +
            "on duplicate key update skillId=#{skillId},userId=#{userId}," +
            "cover=#{cover},video=#{video},displayPic=#{displayPic},title=#{title}," +
            "content=#{content},price=#{price},unit=#{unit},model=#{model}," +
            "type=#{type},score=#{score},date=#{date}"})
    int insertSkill(Skill skill);

    @Update({"Update skill SET score = #{score} WHERE skillId=#{skillId}"})
    void updateSkillScore(@Param("score") double score,@Param("skillId") int skillId);

    @Update({"Update skill SET commentNum=commentNum+1 WHERE skillId=#{skillId}"})
    void updateSkillCommentNum(@Param("skillId") int skillId);

    @Select({"SELECT * FROM skill WHERE type = #{type} ORDER BY date DESC"})
    List<Skill> selectSkillByType(@Param("type") String type);

    @Select({"SELECT * FROM skill ORDER BY date DESC"})
    List<Skill> selectAllSkillOrderByTime();

    @Select({"SELECT * FROM skill WHERE skillId=#{skillId}"})
    Skill selectSkillBySkillId(@Param("skillId") int skillId);

    @Select({"SELECT * FROM skill WHERE userId=#{userId}"})
    List<Skill> selectSkillByUserId(@Param("userId") int userId);

    @Delete({"DELETE FROM skill WHERE skillId=#{skillId}"})
    int deleteSkillBySkillId(@Param("skillId") int skillId);
}
