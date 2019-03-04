package com.turbid.userserver.service;

import com.turbid.basicapi.entity.BJSON;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "SMS-SERVER")
public interface SMSService {

    @PostMapping(value = "/sendSMS")
    BJSON sendSecurityCode(@RequestParam("mobile")String mobile);
}
