package com.turbid.authserver.service;

import com.turbid.basicapi.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server")
@Component
public interface UserService {

    @GetMapping(value = "/getUserByMobile")
    User getUserByMobile(@RequestParam("ID")String ID);
}
