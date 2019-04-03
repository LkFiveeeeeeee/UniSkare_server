package com.example.demo.mapper;

import com.example.demo.model.Comment;
import com.example.demo.model.commentInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface commentMapper {

    @Insert({"INSERT INTO comment VALUE(#{commentId},#{userId},#{score}," +
            "#{content},#{pic},#{time});"})
    @Options(useGeneratedKeys = true,keyProperty = "commentId",keyColumn = "commentId")
    int insertComment(Comment comment);

    @Select({"WITH commentInfo as (select comment.* from comment,screlation where screlation.skillId=#{skillId} " +
            "and comment.commentId=screlation.commentId) " +
            "select uni_avatarUrl as avatar, uni_nickName as nickName, commentId, userId, score, content, pic, time " +
            "from user, commentInfo where commentInfo.userId = uni_uuid"})
    List<commentInfo> selectCommentInfo(@Param("skillId") int skillId);

    @Select({"WITH commentInfo as (select comment.* from comment,screlation where screlation.skillId=#{skillId} " +
            "and comment.commentId=screlation.commentId) " +
            "select uni_avatarUrl as avatar, uni_nickName as nickName, commentId, userId, score, content, pic, time " +
            "from user, commentInfo where commentInfo.userId = uni_uuid LIMIT 2"})
    List<commentInfo> selectCommentInfoOnTwo(@Param("skillId") int skillId);

    @Select({"SELECT * FROM comment WHERE commentId=#{commentId}"})
    Comment selectCommentByCommentId(@Param("commentId") int commentId);

    @Delete({"DELETE FROM comment WHERE commentId=#{commentId}"})
    int deleteComment(@Param("commentId") int commentId);
}
