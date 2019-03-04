package com.turbid.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(){

        return "index";
    }

    @RequestMapping("/test")
    public String upload(){

        return "/test/upload";
    }


}
