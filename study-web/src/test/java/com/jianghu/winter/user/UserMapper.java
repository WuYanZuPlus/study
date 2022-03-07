package com.jianghu.winter.user;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author daniel.hu
 * @date 2019/8/27 10:19
 */
@Mapper
interface UserMapper {

    @Select("select * from t_user where id = #{id}")
    UserEntity get(Long id);

    @Select("select * from t_user")
    List<UserEntity> find();

    List<UserEntity> findByCondition(UserEntity user);

    @Insert("INSERT INTO t_user (account, user_name, mobile, email, nick_name, password, user_level, valid, user_type) "
            + "values (#{account}, #{userName}, #{mobile}, #{email}, #{nickName}, #{password}, #{userLevel}, #{valid}, #{userType}) ")
    void insert(UserEntity user);

    @Delete("delete from t_user where id = #{id}")
    void delete(Long id);

    @Update("UPDATE t_user SET user_name = #{userName}, mobile = #{mobile}, email = #{email} WHERE id = #{id}")
    void update(UserEntity user);
}
