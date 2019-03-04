package com.turbid.smsserver.controller;


import com.turbid.basicapi.entity.BJSON;
import com.turbid.smsserver.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController  {

    @Autowired
    SMSService smsService;

    @Value("${com.turbid.securitycode}")
    String securityCode;


    @PostMapping(value = "/sendSMS")
    public BJSON sendSecurityCode(@RequestParam("mobile")String mobile)  {
        BJSON bjson= new BJSON();
        try {
            bjson = smsService.sendSMS(mobile,securityCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bjson;
    }
}
