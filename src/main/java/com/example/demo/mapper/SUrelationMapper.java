package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SUrelationMapper {
    @Insert({"INSERT INTO surelation VALUE(#{userId},#{skillId});"})
    void insertSUrelation(@Param("userId") int userId,
                          @Param("skillId") int skillId);

    @Select({"SELECT skillId FROM surelation WHERE userId=#{userId}"})
    int[] findSkills(@Param("userId") int userId);

    @Delete({"DELETE FROM surelation WHERE userId=#{userId}"})
    void deleteRelation(@Param("userId") int userId);
}
