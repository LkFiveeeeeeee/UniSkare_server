package com.example.demo.mapper;

import com.example.demo.model.Skill;
import com.example.demo.model.SkillPage;
import com.example.demo.model.skillShow;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface skillMapper {
    @Insert({"INSERT INTO skill VALUE(#{skillId},#{userId},#{cover}," +
            "#{video},#{displayPic},#{title},#{content},#{price}," +
            "#{unit},#{model},#{fullType},#{type},#{score},#{date},#{commentNum})" +
            "on duplicate key update skillId=#{skillId},userId=#{userId}," +
            "cover=#{cover},video=#{video},displayPic=#{displayPic},title=#{title}," +
            "content=#{content},price=#{price},unit=#{unit},model=#{model}," +
            "type=#{type},score=#{score},date=#{date},commentNum=#{commentNum}"})
    @Options(useGeneratedKeys = true,keyProperty = "skillId")
    int insertSkill(Skill skill);

    @Update({"UPDATE skill SET title=#{title},content=#{content},fullType=#{fullType},type=#{type} WHERE skillId=#{skillId}"})
    int updateSkill(@ModelAttribute Skill skill);

    @Update({"UPDATE skill SET displayPic =concat(IFNULL(displayPic,''),#{pic}) WHERE skillId=#{id}"})
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

    @Select({"SELECT skillId,user.uni_avatarUrl as image, user.uni_nickName as name, " +
            "skill.content as textBody, skill.title , skill.cover" +
            " FROM user,skill " +
            " WHERE user.uni_uuid = skill.userId" +
            " ORDER BY date DESC"})
    List<skillShow> selectAllSkillOrderByTime();

    @Select({"SELECT skillId,user.uni_avatarUrl as image, user.uni_nickName as name, " +
            "skill.content as textBody, skill.title , skill.cover" +
            " FROM user,skill " +
            " WHERE user.uni_uuid = skill.userId and skill.fullType=#{fullType}" +
            " ORDER BY date DESC"})
    List<skillShow> selectSkillOrderByTime(@Param("fullType") String fullType);

    @Select({"SELECT skillId,user.uni_avatarUrl as image, user.uni_nickName as name, " +
            "skill.content as textBody, skill.title , skill.cover" +
            " FROM user,skill " +
            " WHERE user.uni_uuid = skill.userId and skill.title LIKE '%${text}%' " +
            " ORDER BY date DESC"})
    List<skillShow> searchSkill(@Param("text") String text);

    @Select({"SELECT * FROM skill WHERE skillId=#{skillId}"})
    SkillPage selectSkillBySkillId(@Param("skillId") int skillId);

    @Select({"SELECT skillId,user.uni_avatarUrl as image, user.uni_nickName as name, " +
            "skill.content as textBody, skill.title , skill.cover" +
            " FROM user,skill " +
            " WHERE user.uni_uuid = skill.userId and user.uni_uuid = #{userId} " +
            " ORDER BY date DESC"})
    List<skillShow> selectSkillByUserId(@Param("userId") String userId);


    @Select({"SELECT p.skillId,user.uni_avatarUrl as image, user.uni_nickName as name, " +
            "p.content as textBody, p.title , p.cover " +
            "From user, " +
            "(select skill.* from skill inner join starskill on starskill.starId=#{userId} " +
            "and starskill.skillId=skill.skillId) as p " +
            " WHERE user.uni_uuid = p.userId " +
            " ORDER BY date DESC"})
    List<skillShow> getStarSkill(@Param("userId") String userId);

    @Select({"SELECT displayPic FROM skill WHERE skillId=#{skillId}"})
    String selectDisplayPicBySkillId(@Param("skillId") int skillId);

    @Select({"SELECT cover FROM skill WHERE skillId=#{id}"})
    String selectCover(@Param("id") int skillId);

    @Delete({"DELETE FROM skill WHERE skillId=#{skillId}"})
    int deleteSkillBySkillId(@Param("skillId") int skillId);
}
