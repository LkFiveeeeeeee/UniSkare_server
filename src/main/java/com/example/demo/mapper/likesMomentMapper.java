package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface likesMomentMapper {
    @Insert({"INSERT INTO likesMoment VALUE(#{momentsId},#{likesId});"})
    void insertMomentRelation(@Param("momentsId") int momentsId,
                              @Param("likesId") int likesId);

    @Select({"SELECT momentsId FROM likesMoment WHERE likesId=#{likesId}"})
    int[] findUserLikeMoments(@Param("likesId") int userId);

    @Select({"SELECT likesId FROM likesMoment WHERE momentsId=#{momentsId}"})
    int[] findThisMomentLovers(@Param("momentsId") int momentsId);

    @Select({"SELECT EXISTS(SELECT * FROM likesMoment WHERE" +
            "likesId=#{likesId} and momentsId=#{momentsId}"})
    boolean checkRecordingExist(@Param("momentsId") int momentsId,
                                @Param("likesId") int likesId);

    @Delete({"DELETE FROM likesMoment WHERE likesId=#{likesId}"})
    void deleteRelation(@Param("likesId") int likesId);

}
