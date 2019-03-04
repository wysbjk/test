package com.turbid.basicapi.entity.user;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

@Data
public class User {

    private static final String TABLE_CODE= "user";

    private String code;
    private String mobile;
    private String username;
    private String email;
    private String password;
    private String securitycode;
    private String lastLogin;
    private String lastSend;
    private Integer status;
    private String addtime;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(this.TABLE_CODE);
        }
        return code;
    }

}
