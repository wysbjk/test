package com.turbid.dataserver.service;

import com.turbid.dataserver.dao.cluster.CodeLibMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeLib {

    @Autowired
   private CodeLibMapper codeLibMapper;

    public String getCode(String groupcode){
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrst";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append(groupcode);
        sb.append("-");
        for (int i = 0; i < 50; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        try {
            if(0< codeLibMapper.selectCodeLib(sb.toString())){
                return getCode(groupcode);
            }else {
                codeLibMapper.insertCodeLib(sb.toString());
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return sb.toString();
    }


}
