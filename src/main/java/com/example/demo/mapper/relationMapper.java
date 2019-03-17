package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface relationMapper {
    @Insert({"INSERT INTO relation VALUE(#{followId},#{fanId});"})
    int insertUserRelation(@Param("followId") String followId,
                              @Param("fanId") String fanId);

    @Select({"SELECT followId FROM relation WHERE fanId=#{fanId}"})
    String[] findFollows(@Param("fanId") String userId);

    @Select({"SELECT fanId FROM relation WHERE followId=#{followId}"})
    String[] findFans(@Param("followId") String userId);


    @Select({"SELECT EXISTS(SELECT * FROM relation WHERE" +
            "followId=#{followId} and fanId=#{fanId}"})
    boolean checkRecordingExist(@Param("followId") String followId,
                                @Param("fanId") String fanId);

    @Delete({"DELETE FROM relation WHERE fanId=#{fanId} and followId=#{followId}"})
    int deleteUserRelation(@Param("followId") String followId,
                              @Param("fanId") String fanId);


}
