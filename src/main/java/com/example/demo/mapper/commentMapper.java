package com.example.demo.mapper;

import com.example.demo.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface commentMapper {

    @Insert({"INSERT INTO comment VALUE(#{commentId},#{userId},#{score}," +
            "#{content},#{pic},#{time});"})
    @Options(useGeneratedKeys = true,keyProperty = "commentId",keyColumn = "commentId")
    int insertComment(Comment comment);

    @Select({"SELECT * FROM comment WHERE screlation.skillId=#{skillId} " +
            "and comment.commentId = screlation.commentId" })
    List<Comment> selectCommentInfo(@Param("skillId") int skillId);

    @Select({"SELECT * FROM comment WHERE commentId=#{commentId}"})
    Comment selectCommentByCommentId(@Param("commentId") int commentId);

    @Delete({"DELETE FROM comment WHERE commentId=#{commentId}"})
    int deleteComment(@Param("commentId") int commentId);
}
