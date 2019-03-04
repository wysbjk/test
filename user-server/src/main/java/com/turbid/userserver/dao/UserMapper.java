package com.turbid.userserver.dao;

import com.turbid.basicapi.entity.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(code,mobile,email,username,password,securitycode,status,lastSend,addtime) values(#{code},#{mobile},#{email},#{username},#{password},#{securitycode},0,date_format(now(),'%Y-%m-%d %H:%i:%s'),date_format(now(),'%Y-%m-%d %H:%i:%s'))")
    int insertUser(User user);

    @Update("update user set securitycode=#{securitycode},lastSend=#{lastSend} where  mobile=#{mobile}")
    int updateSecurityCode(User user);

    @Update("update user set status=1 where  email=#{ID} or mobile=#{ID} and securitycode=#{securitycode}")
    int updateStatus(@Param("ID")String ID,@Param("securitycode")String securitycode);

    @Select("select * from user where email=#{ID} or mobile=#{ID} and status != 0")
    User selectUserByID(@Param("ID")String ID);

    @Select("select count(*) from user where mobile=#{mobile} and status= 0 ")
    Integer selectUserCountByUserStatus0(User user);

    @Select("select count(*) from user where mobile=#{mobile} and status != 0 ")
    Integer selectUserCountByUser(User user);

    @Update("update user set email=#{email} where  email=#{ID} or mobile=#{ID} and code=#{code}")
    Integer updateEmail(@Param("email")String email, @Param("securitycode")String securitycode,@Param("ID")String id);

    @Update("update user set mobile=#{mobile} where  email=#{ID} or mobile=#{ID} and code=#{code}")
    Integer updateMobile(@Param("mobile")String mobile, @Param("securitycode")String securitycode,@Param("ID")String id);

    @Update("update user set password=#{password} where  email=#{ID} or mobile=#{ID} and securitycode=#{securitycode}")
    Integer updatePasswordBySecurityCode(@Param("password")String password, @Param("securitycode")String securitycode,@Param("ID")String id);

    @Update("update user set password=#{newpassword} where  email=#{ID} or mobile=#{ID} and password=#{password}")
    Integer updatePasswordByPassword( @Param("newpassword")String newpassword,@Param("password")String password,@Param("ID")String id);

}
