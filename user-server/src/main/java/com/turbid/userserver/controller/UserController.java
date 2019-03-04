package com.turbid.userserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.turbid.basicapi.entity.BJSON;
import com.turbid.basicapi.entity.user.User;
import com.turbid.basicapi.tools.MD5;
import com.turbid.userserver.service.AuthService;
import com.turbid.userserver.service.SMSService;
import com.turbid.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private SMSService smsService;

    /**
     * 用户登录
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/login",consumes = "application/json")
    public Mono<BJSON> login(@RequestBody JSONObject jsonObject){
        BJSON bjson=new BJSON();
        JSONObject object= authService.login(jsonObject.getString("grant_type"),jsonObject.getString("scope"),jsonObject.getString("username"),jsonObject.getString("password"),jsonObject.getString("login_type"));
        if (null!=object){
            bjson.setMassages("登录成功");
            bjson.setData(object);
        }else {
            bjson.setMassages("登录失败");
            bjson.setStatus(400);
        }

        return Mono.just(bjson);
    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/register")
    public Mono<BJSON> register(@RequestBody User user)  {
        BJSON bjson=new BJSON();
        try {
            user.setPassword(MD5.strAdMd5(user.getPassword()));
           user.setSecuritycode(smsService.sendSecurityCode(user.getMobile()).getData().toString());
            if(userService.addUser(user)==1){
                bjson.setMassages("发送成功");
            }else {
                bjson.setStatus(400);
                bjson.setMassages("发送失败");
            }
        }catch (Exception e){
            bjson.setStatus(400);
            bjson.setMassages("发送失败");
        }

        return Mono.just(bjson);
    }


    @PutMapping("/changePasswordBySMS")
    public Mono<BJSON> changePassword(Principal principal, @RequestBody User user){
        BJSON bjson=new BJSON();

        return Mono.just(bjson);
    }

    @GetMapping(value = "/getUserByMobile")
    public Mono<User> getUserByMobile(@RequestParam("ID")String ID){
        User user= userService.selectUserByID(ID);
        return Mono.just(user);
    }

    @GetMapping(value = "/getUseCountrByMobile")
    public Mono<Integer> getUseCountByMobile(@RequestBody User user){
        return Mono.just(userService.selectUserCountByID(user));
    }

    @PutMapping(value = "/updateStatus")
    public Mono<User> updateStatus(@RequestParam("ID")String ID,@RequestParam("securitycode")String securitycode){
        return Mono.just(userService.updateStatus(ID,securitycode));
    }

    @PutMapping(value = "/updateSecurityCode")
    public Mono<User> updateCode(@RequestBody User user){
        return Mono.just(userService.updateSecurityCode(user));
    }

    @PutMapping(value = "/updateEmail")
    public Mono<User> updateEmail(@RequestParam("email")String email,@RequestParam("securitycode")String securitycode,@RequestParam("ID")String ID){
        return Mono.just(userService.updateEmail(email,securitycode,ID));
    }

    @PutMapping(value = "/updateMobile")
    public Mono<User> updateMobile(@RequestParam("mobile")String mobile,@RequestParam("securitycode")String securitycode,@RequestParam("ID")String ID){
        return Mono.just(userService.updateMobile(mobile,securitycode,ID));
    }





}
