package com.example.demo.mapper;

import com.example.demo.model.Moment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface momentMapper {
    @Insert({"INSERT INTO moment VALUE(#{momentId},#{userId},#{content}," +
            "#{pic},#{canSee},#{likesNum},#{time})" +
            "on duplicate key update momentId=#{momentId},userId=#{userId}," +
            "content=#{content},pic=#{pic},canSee=#{canSee},likesNum=#{likesNum}," +
            "time=#{time}"})
    int insertMoment(Moment moment);

    @Select({"Select * FROM moment WHERE userId=#{userId} ORDER BY time DESC"})
    List<Moment> selectMomentByUserId(@Param("userId") String userId);

    @Select({"Select * FROM moment WHERE canSee=1 ORDER BY time DESC"})
    List<Moment> selectAllMoments();

    @Delete({"Delete FROM moment WHERE momentId=#{momentId}"})
    int deleteMomentById(@Param("momentId") int momentId);

}
