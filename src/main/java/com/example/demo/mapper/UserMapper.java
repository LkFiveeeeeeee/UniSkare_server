package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
        @Insert({"insert into User value(#{uni_uuid},#{uni_avatarUrl}," +
                "#{uni_nickName},#{uni_momentNum},#{uni_fansNum},#{uni_followsNum}" +
                ",#{uni_indiSign},#{uni_isStu},#{uni_passPhone})" +
                "on duplicate key update uni_avatarUrl=#{uni_avatarUrl}," +
                "uni_nickName=#{uni_nickName},uni_momentNum=#{uni_momentNum}," +
                "uni_fansNum=#{uni_fansNum},uni_followsNum=#{uni_followsNum}," +
                "uni_indiSign=#{uni_indiSign},uni_isStu=#{uni_isStu}," +
                "uni_passPhone=#{uni_passPhone};"})
        int _insertUser(User user);




        /*
        *  Update Operation
        * */

        @Update({"UPDATE User SET User.uni_momentNum = #{momentNum}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateMomentNum(@Param("id") int id,@Param("momentNum") int momentNum);

        @Update({"UPDATE User SET User.uni_fansNum = #{fansNum}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateFansNum(@Param("id") int id,@Param("fansNum") int fansNum);

        @Update({"UPDATE User SET User.uni_followsNum = #{followsNum}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateFollowsNum(@Param("id") int id,@Param("followsNum") int followsNum);

        @Update({"UPDATE User SET User.uni_nickName = #{nickName},User.uni_avatarUrl=#{avatar}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateAvatarAndnickName(@Param("nickName") String nickName,
                                   @Param("avatar") String avatar,
                                   @Param("id") int id);

        //TODO 学生认证,手机号认证
        //TODO 更改个性签名
        //TODO 更改个人喜好

        /*
        * Select Operation
        **/
        @Select({"SELECT * FROM User WHERE uni_uuid = #{id}"})
        User findById(@Param("id") int id);
}
