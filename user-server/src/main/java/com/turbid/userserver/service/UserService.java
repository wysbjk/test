package com.turbid.userserver.service;

import com.turbid.basicapi.entity.user.User;
import com.turbid.userserver.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public Integer addUser(User user){
       if(userMapper.selectUserCountByUserStatus0(user)>0){
            return userMapper.updateSecurityCode(user);
       }else if(userMapper.selectUserCountByUser(user)==0){
           return userMapper.insertUser(user);
       }else {
           return 0;
       }
    }

    //@Cacheable(value = "user", key = "'user_'+#ID")
    public User selectUserByID(String ID) {
        return  userMapper.selectUserByID(ID);
    }

    @CachePut(value = "user", key = "'user_'+#ID")
    public User updateStatus(String ID, String securitycode) {
        userMapper.updateStatus(ID,securitycode);
        return userMapper.selectUserByID(ID);
    }

    @CachePut(value = "user", key = "'user_'+#user.mobile")
    public User updateSecurityCode(User user) {
        userMapper.updateSecurityCode(user);
        return userMapper.selectUserByID(user.getMobile());
    }

    public Integer selectUserCountByID(User user) {
        return userMapper.selectUserCountByUser(user);
    }

    @CachePut(value = "user", key = "'user_'+#ID")
    public User updateEmail(String email, String securitycode,String ID) {
        userMapper.updateEmail(email,securitycode,ID);
        return userMapper.selectUserByID(email) ;
    }

    @CachePut(value = "user", key = "'user_'+#ID")
    public User updateMobile(String mobile, String securitycode,String ID) {
        userMapper.updateMobile(mobile,securitycode, ID);
        return userMapper.selectUserByID(mobile);
    }

    @CachePut(value = "user", key = "'user_'+#ID")
    public User updatePasswordByPassword(String newpassword, String password,String ID) {
        userMapper.updatePasswordByPassword(newpassword,password,ID);
        return userMapper.selectUserByID(ID) ;
    }

    @CachePut(value = "user", key = "'user_'+#ID")
    public User updatePasswordBySecurityCode(String password, String securitycode,String ID) {
        userMapper.updatePasswordBySecurityCode(password,securitycode,ID);
        return userMapper.selectUserByID(ID);
    }
}
