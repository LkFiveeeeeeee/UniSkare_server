package com.example.demo.mapper;

import com.example.demo.model.User;
import com.example.demo.model.userInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface relationMapper {
    @Insert({"INSERT INTO relation VALUE(#{followId},#{fanId});"})
    int insertUserRelation(@Param("followId") String followId,
                              @Param("fanId") String fanId);

    @Select({"SELECT uni_uuid as userId, uni_avatarUrl as userAvatar, uni_nickName as userName, " +
            "uni_sex as sex , uni_indiSign as userMotto ,1 as isFollow from user "+
            "WHERE user.uni_uuid in (select followId from relation where fanId=#{fanId})"})
    List<userInfo> findFollows(@Param("fanId") String userId);

    @Select({"(SELECT uni_uuid as userId, uni_avatarUrl as userAvatar, uni_nickName as userName, " +
            "uni_sex as sex , uni_indiSign as userMotto , 1 as isFollow from user " +
            "WHERE user.uni_uuid in (select fanId from relation where followId=#{followId})" +
            " and user.uni_uuid in (select followId from relation where fanId=#{followId}))" +
            " union " +
            "(SELECT uni_uuid as userId, uni_avatarUrl as userAvatar, uni_nickName as userName, " +
            "uni_sex as sex , uni_indiSign as userMotto , 0 as isFollow from user " +
            "WHERE user.uni_uuid in (select fanId from relation where followId=#{followId}) " +
            " and user.uni_uuid not in (select followId from relation where fanId=#{followId}))" })
    List<userInfo> findFans(@Param("followId") String userId);


    @Select({"SELECT EXISTS(SELECT * FROM relation WHERE" +
            "followId=#{followId} and fanId=#{fanId}"})
    boolean checkRecordingExist(@Param("followId") String followId,
                                @Param("fanId") String fanId);

    @Delete({"DELETE FROM relation WHERE fanId=#{fanId} and followId=#{followId}"})
    int deleteUserRelation(@Param("followId") String followId,
                              @Param("fanId") String fanId);


}
