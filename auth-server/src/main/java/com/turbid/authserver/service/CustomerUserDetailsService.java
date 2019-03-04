package com.turbid.authserver.service;

import com.turbid.basicapi.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest =  requestAttributes.getRequest();
        String login_type= servletRequest.getParameter("login_type").toString().trim();
        User user= userService.getUserByMobile(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }else {
            String password=user.getPassword();
            if(!login_type.equals("password")&&login_type!="password"){
                password=user.getCode();
                if (user.getLastSend()==null){
                    throw new UsernameNotFoundException("验证码失效");
                }
            }
            return  new org.springframework.security.core.userdetails.User(username,password,true,true,true,true,AuthorityUtils.NO_AUTHORITIES);
        }
    }
}
