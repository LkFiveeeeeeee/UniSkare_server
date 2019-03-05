package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SCrelationMapper {
    @Insert({"INSERT INTO SCrelation VALUE(#{commentId},#{skillId});"})
    void insertSCrelation(@Param("commentId") int commentId,
                            @Param("skillId") int skillId);

    @Select({"SELECT commentId FROM SCrelation WHERE skillId=#{skillId}"})
    int[] findCommentUers(@Param("skillId") int skillId);

    @Select({"SELECT skillId FROM SCrelation WHERE commentId=#{commentId}"})
    int findSkillId(@Param("commentId") int commentId);


    @Select({"SELECT EXISTS(SELECT * FROM SCrelation WHERE" +
            "commentId=#{commentId} and skillId=#{skillId}"})
    boolean checkRecordingExist(@Param("commentId") int commentId,
                                @Param("skillId") int skillId);

    @Delete({"DELETE FROM SCrelation WHERE commentId=#{commentId} and skillId=#{skillId}"})
    void deleteSCrelation(@Param("commentId") int commentId,
                            @Param("skillId") int skillId);


}
