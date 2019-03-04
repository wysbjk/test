package com.turbid.basicapi.entity.shop.commodity;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

import java.util.List;

/**
 * 商品分组
 */
@Data
public class CommoditySubgroup {

    private static final String TABLE_CODE= "CS";

    //分组编号
    private String code;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(TABLE_CODE);
        }
        return code;
    }

    //总类编号
    private String gc_code;

    //分组名称
    private String name;

    //分组序号
    private Integer index;

    //分类
    private List<CommodityClassification> list;
}
