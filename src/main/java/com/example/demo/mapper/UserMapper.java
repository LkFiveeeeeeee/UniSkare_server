package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

        @Insert({"insert into User(uni_uuid) value(#{uni_uuid})"})
        int insertUserOnlyPrimayKey(String uni_uuid);

        @Insert({"insert into User value(#{uni_uuid},#{uni_avatarUrl}," +
                "#{uni_nickName},#{uni_sex},#{uni_momentNum},#{uni_fansNum},#{uni_followsNum}" +
                ",#{uni_indiSign},#{uni_isStu},#{uni_passPhone},#{change_nickName},#{change_avatar})" +
                "on duplicate key update uni_avatarUrl=#{uni_avatarUrl}," +
                "uni_nickName=#{uni_nickName},uni_sex=#{uni_sex},uni_momentNum=#{uni_momentNum}," +
                "uni_fansNum=#{uni_fansNum},uni_followsNum=#{uni_followsNum}," +
                "uni_indiSign=#{uni_indiSign},uni_isStu=#{uni_isStu}," +
                "uni_passPhone=#{uni_passPhone}," +
                "change_nickName=#{change_nickName}," +
                "change_avatar=#{change_avatar};"})
        int _insertUser(User user);

        @Select({"SELECT EXISTS(SELECT * FROM USER WHERE uni_uuid=#{id})"})
        boolean checkUserExist(@Param("id") String id);




        /*
        *  Update Operation
        * */
        @Update({"UPDATE User SET uni_avatarUrl=#{avatar},change_avatar=1 " +
                "WHERE User.uni_uuid=#{id};"})
        int updateAvatar(@Param("avatar") String avatar,@Param("id") String id);

        @Update({"UPDATE User SET uni_nickName=#{nickName},change_nickName=1 " +
                "WHERE User.uni_uuid=#{id};"})
        int updateNickName(@Param("nickName") String nickName,@Param("id") String id);

        @Update({"UPDATE User SET User.uni_momentNum = #{momentNum}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateMomentNum(@Param("id") String id,@Param("momentNum") int momentNum);

        @Update({"UPDATE User SET User.uni_fansNum = #{fansNum}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateFansNum(@Param("id") String id,@Param("fansNum") int fansNum);

        @Update({"UPDATE User SET User.uni_followsNum = #{followsNum}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateFollowsNum(@Param("id") String id,@Param("followsNum") int followsNum);

        @Update({"UPDATE User SET User.uni_nickName = #{nickName},User.uni_avatarUrl=#{avatar}" +
                " WHERE User.uni_uuid = #{id};"})
        int updateAvatarAndnickName(@Param("nickName") String nickName,
                                   @Param("avatar") String avatar,
                                   @Param("id") String id);

        //TODO 学生认证,手机号认证
        //TODO 更改个性签名
        //TODO 更改个人喜好

        /*
        * Select Operation
        **/
        @Select({"SELECT * FROM User WHERE uni_uuid = #{id}"})
        User findById(@Param("id") String id);
}
