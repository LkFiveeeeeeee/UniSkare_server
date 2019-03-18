package com.example.demo.mapper;

import com.example.demo.model.Skill;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface skillMapper {
    @Insert({"INSERT INTO skill VALUE(#{skillId},#{userId},#{cover}," +
            "#{video},#{displayPic},#{title},#{content},#{price}," +
            "#{unit},#{model},#{type},#{score},#{date})" +
            "on duplicate key update skillId=#{skillId},userId=#{userId}," +
            "cover=#{cover},video=#{video},displayPic=#{displayPic},title=#{title}," +
            "content=#{content},price=#{price},unit=#{unit},model=#{model}," +
            "type=#{type},score=#{score},date=#{date}"})
    @Options(useGeneratedKeys = true,keyProperty = "skillId",keyColumn = "skillId")
    int insertSkill(Skill skill);

    @Update({"UPDATE skill SET displayPic =concat(displayPic,#{pic}) WHERE skillId=#{id}"})
    int updateDisplayPic(@Param("pic") String pic,@Param("id") int skillId);

    @Update({"UPDATE skill SET displayPic = #{pic} WHERE skillId=#{id}"})
    int deleteSomePicUrl(@Param("pic") String pic,@Param("id") int skillId);

    @Update({"UPDATE skill SET cover = #{cover} WHERE skillId = #{id}"})
    int updateCover(@Param("cover") String cover,@Param("id") int skillId);

    @Update({"Update skill SET score = #{score} WHERE skillId=#{skillId}"})
    void updateSkillScore(@Param("score") double score,@Param("skillId") int skillId);

    @Update({"Update skill SET commentNum=commentNum+1 WHERE skillId=#{skillId}"})
    void plusSkillCommentNum(@Param("skillId") int skillId);

    @Update({"Update skill SET commentNum=commentNum-1 WHERE skillId=#{skillId}"})
    void minusSkillCommentNum(@Param("skillId") int skillId);

    @Select({"SELECT * FROM skill WHERE type = #{type} ORDER BY date DESC"})
    List<Skill> selectSkillByType(@Param("type") String type);

    @Select({"SELECT * FROM skill ORDER BY date DESC"})
    List<Skill> selectAllSkillOrderByTime();

    @Select({"SELECT * FROM skill WHERE skillId=#{skillId}"})
    Skill selectSkillBySkillId(@Param("skillId") int skillId);

    @Select({"SELECT * FROM skill WHERE userId=#{userId}"})
    List<Skill> selectSkillByUserId(@Param("userId") String userId);

    @Select({"SELECT displayPic FROM skill WHERE skillId=#{skillId}"})
    String selectDisplayPicBySkillId(@Param("skillId") int skillId);

    @Select({"SELECT cover FROM skill WHERE skillId=#{id}"})
    String selectCover(@Param("id") int skillId);

    @Delete({"DELETE FROM skill WHERE skillId=#{skillId}"})
    int deleteSkillBySkillId(@Param("skillId") int skillId);
}
