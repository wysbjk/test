package com.turbid.basicapi.entity;


import lombok.Data;

@Data
public class BJSON {

    public BJSON(){

    }

    public BJSON(Integer status, String massages, Object data) {
        this.status = status;
        this.massages = massages;
        this.data = data;
    }

    private Integer status =200;
    private String massages ="SUCCESS";
    private Object data;
}
