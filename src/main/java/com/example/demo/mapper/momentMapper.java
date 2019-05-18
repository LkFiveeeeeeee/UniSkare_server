package com.example.demo.mapper;

import com.example.demo.model.Moment;
import com.example.demo.model.momentShow;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface momentMapper {
    @Insert({"INSERT INTO moment VALUE(#{momentId},#{userId},#{content}," +
            "#{pic},#{canSee},#{likesNum},#{time})"
            })
    @Options(useGeneratedKeys = true,keyProperty = "momentId")
    int insertMoment(Moment moment);

    @Update({"UPDATE moment SET content=#{content},canSee=#{canSee} WHERE momentId=#{momentId}"})
    int updateMoment(@ModelAttribute Moment moment);

    @Update({"UPDATE moment SET likesNum = likesNum+1 WHERE momentId=#{momentId}"})
    int increaseLikesNum(@Param("momentId") int momentId);

    @Update({"UPDATE moment SET likesNum = likesNum-1 WHERE momentId=#{momentId}"})
    int decreaseLikesNum(@Param("momentId") int momentId);

    @Update({"UPDATE moment SET pic =concat(IFNULL(pic,''),#{pic}) WHERE momentId=#{id}"})
    int updateDisplayPic(@Param("pic") String pic,@Param("id") int momentId);

    @Update({"UPDATE moment SET pic = #{pic} WHERE momentId=#{id}"})
    int deleteSomePicUrl(@Param("pic") String pic,@Param("id") int momentId);


    @Select({"Select uni_uuid as userId, uni_avatarUrl as avatar, " +
            " uni_nickName as userName, content, time, likesNum as likeNum, momentId,pic , canSee " +
            " FROM moment,user WHERE moment.canSee=1 AND " +
            " moment.userId = #{userId} AND" +
            " moment.userId = user.uni_uuid " +
            " ORDER BY time DESC"})
    List<momentShow> selectMomentByUserId(@Param("userId") String userId);

    @Select({"Select uni_uuid as userId, uni_avatarUrl as avatar, " +
            " uni_nickName as userName, content, time, likesNum as likeNum, momentId,pic , canSee " +
            " FROM moment,user WHERE moment.canSee=1 AND moment.userId = user.uni_uuid " +
            " ORDER BY time DESC"})
    List<momentShow> selectAllMoments();

    @Select({"Select * from (Select uni_uuid as userId, uni_avatarUrl as avatar, " +
            " uni_nickName as userName, content, time, likesNum as likeNum, momentId,pic, canSee " +
            " FROM moment,user WHERE moment.canSee=1 AND moment.userId = user.uni_uuid " +
            " ORDER BY time DESC) as temp where momentId=#{momentId}"})
    momentShow selectOneMoment(@Param("momentId") int momentId);

    @Select({"Select uni_uuid as userId, uni_avatarUrl as avatar, " +
            " uni_nickName as userName, p.content, p.time, p.likesNum as likeNum, p.momentId,p.pic , p.canSee " +
            " FROM user, (select moment.* from moment inner join " +
            "murelation on murelation.userId =#{userId} and murelation.momentId = moment.momentId) as p " +
            "WHERE p.canSee=1 AND p.userId = user.uni_uuid " +
            " ORDER BY time DESC"})
    List<momentShow> getStarMoment(@Param("userId") String userId);

    @Select({"SELECT pic FROM moment WHERE momentId=#{momentId}"})
    String selectDisplayPicByMomentId(@Param("momentId") int momentId);


    @Delete({"Delete FROM moment WHERE momentId=#{momentId}"})
    int deleteMomentById(@Param("momentId") int momentId);

}
