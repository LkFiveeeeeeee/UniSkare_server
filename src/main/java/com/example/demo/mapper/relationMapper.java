package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface relationMapper {
    @Insert({"INSERT INTO relation VALUE(#{followId},#{fanId});"})
    int insertUserRelation(@Param("followId") int followId,
                              @Param("fanId") int fanId);

    @Select({"SELECT followId FROM relation WHERE fanId=#{fanId}"})
    int[] findFollows(@Param("fanId") int userId);

    @Select({"SELECT fanId FROM relation WHERE followId=#{followId}"})
    int[] findFans(@Param("followId") int userId);


    @Select({"SELECT EXISTS(SELECT * FROM relation WHERE" +
            "followId=#{followId} and fanId=#{fanId}"})
    boolean checkRecordingExist(@Param("followId") int followId,
                                @Param("fanId") int fanId);

    @Delete({"DELETE FROM relation WHERE fanId=#{fanId} and followId=#{followId}"})
    int deleteUserRelation(@Param("followId") int followId,
                              @Param("fanId") int fanId);


}
