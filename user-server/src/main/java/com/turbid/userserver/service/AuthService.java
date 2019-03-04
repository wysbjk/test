package com.turbid.userserver.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "auth-server",configuration = FeignAutoConfiguration.class,fallback = AuthServiceFallback.class)
public interface AuthService {

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    JSONObject login(@RequestParam(value = "grant_type") String grant_type, @RequestParam(value = "scope") String scope, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "login_type") String login_type);
}

@Component
class AuthServiceFallback implements AuthService {


    @Override
    public JSONObject login(@RequestParam(value = "grant_type") String grant_type,@RequestParam(value = "scope") String scope,@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,@RequestParam(value = "login_type")String login_type) {
        return null;
    }
}
