package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

public interface MUrelationMapper {
    @Insert({"INSERT INTO murelation VALUE(#{momentId},#{userId});"})
    int insertMUrelation(@Param("userId") String userId,
                            @Param("momentId") int momentId);

    @Select({"SELECT momentId FROM murelation WHERE userId=#{userId}"})
    int[] findMoments(@Param("userId") String userId);

    @Delete({"DELETE FROM murelation WHERE userId=#{userId} AND momentId=#{momentId}"})
    int deleteRelation(@Param("userId") String userId,@Param("momentId") int momentId);

    @Select({"SELECT count(*) from murelation where userId=#{userId} and momentId=#{momentId}"})
    int checkExistLikeRelation(@Param("userId") String userId, @Param("momentId") int momentId);
}
