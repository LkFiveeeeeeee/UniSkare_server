package com.example.demo.mapper;

import com.example.demo.model.Moment;
import com.example.demo.model.momentShow;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface momentMapper {
    @Insert({"INSERT INTO moment VALUE(#{momentId},#{userId},#{content}," +
            "#{pic},#{canSee},#{likesNum},#{time})" +
            "on duplicate key update momentId=#{momentId},userId=#{userId}," +
            "content=#{content},pic=#{pic},canSee=#{canSee},likesNum=#{likesNum}," +
            "time=#{time}"})
    @Options(useGeneratedKeys = true,keyProperty = "momentId")
    int insertMoment(Moment moment);

    @Update({"UPDATE moment SET content=#{content},canSee=#{canSee} WHERE momentId=#{momentId}"})
    int updateMoment(@ModelAttribute Moment moment);

    @Update({"UPDATE moment SET pic =concat(IFNULL(pic,''),#{pic}) WHERE momentId=#{id}"})
    int updateDisplayPic(@Param("pic") String pic,@Param("id") int momentId);

    @Update({"UPDATE moment SET pic = #{pic} WHERE momentId=#{id}"})
    int deleteSomePicUrl(@Param("pic") String pic,@Param("id") int momentId);


    @Select({"Select * FROM moment WHERE userId=#{userId} ORDER BY time DESC"})
    List<Moment> selectMomentByUserId(@Param("userId") String userId);

    @Select({"Select uni_uuid as userId, uni_avatarUrl as avatar, " +
            " uni_nickName as userName, content, time, likesNum as likeNum, momentId,pic " +
            " FROM moment,user WHERE moment.canSee=1 AND moment.userId = user.uni_uuid " +
            " ORDER BY time DESC"})
    List<momentShow> selectAllMoments();

    @Select({"SELECT * FROM moment WHERE momentId=#{id}"})
    Moment selectMomentByMomentId(@Param("id") int momentId);

    @Select({"SELECT pic FROM moment WHERE momentId=#{momentId}"})
    String selectDisplayPicByMomentId(@Param("momentId") int momentId);


    @Delete({"Delete FROM moment WHERE momentId=#{momentId}"})
    int deleteMomentById(@Param("momentId") int momentId);

}
