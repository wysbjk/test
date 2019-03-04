package com.turbid.basicapi.entity.file;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

import java.util.List;

/**
 * 文件组
 */
@Data
public class FileGroup {

    private static final String TABLE_CODE= "filegroup";

    //文件组编号
    private String code;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(TABLE_CODE);
        }
        return code;
    }

    //文件组名称
    private String name;

    private List<File> fileList;
}
