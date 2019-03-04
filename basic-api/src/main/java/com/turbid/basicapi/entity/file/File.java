package com.turbid.basicapi.entity.file;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

/**
 * 文件类
 */
@Data
public class File {

    private static final String TABLE_CODE= "file";

    //文件编号
    private String code;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(TABLE_CODE);
        }
        return code;
    }
    //文件组
    private String fg_code;

    //文件标题
    private String title;

    //文件大小
    private Long size;

    //文件地址
    private String url;

    //文件类型
    private String type;

    //添加时间
    private String addtime;
}
