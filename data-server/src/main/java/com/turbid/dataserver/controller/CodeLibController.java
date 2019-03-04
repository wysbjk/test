package com.turbid.dataserver.controller;

import com.turbid.dataserver.service.CodeLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CodeLibController {

    @Autowired
    CodeLib codeLib;

    @GetMapping("/codeLib/{groupcode}")
    public Mono<String> codeLib(@PathVariable("groupcode") String groupcode){
       return Mono.just(codeLib.getCode(groupcode));
    }
}
