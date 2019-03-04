package com.turbid.basicapi.tools;

import org.springframework.web.client.RestTemplate;

public class CodeLib {

    public static String  getCode(String groupcode){
        RestTemplate restTemplate =new RestTemplate();
        return restTemplate.getForObject("http://localhost:9001/codeLib/"+groupcode,String.class);
    }



}
