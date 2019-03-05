package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface starSkillMapper {
    @Insert({"INSERT INTO starSkill VALUE(#{skillId},#{starId});"})
    void insertSCrelation(@Param("skillId") int skillId,
                          @Param("starId") int starId);

    @Select({"SELECT skillId FROM starSkill WHERE starId=#{starId}"})
    int[] findStarSkillList(@Param("starId") int starId);


    @Select({"SELECT EXISTS(SELECT * FROM starSkill WHERE" +
            "starId=#{starId} and skillId=#{skillId}"})
    boolean checkRecordingExist(@Param("starId") int starId,
                                @Param("skillId") int skillId);

    @Delete({"DELETE FROM starSkill WHERE starId=#{starId} and skillId=#{skillId}"})
    void deleteStarRelation(@Param("starId") int starId,
                            @Param("skillId") int skillId);
}
