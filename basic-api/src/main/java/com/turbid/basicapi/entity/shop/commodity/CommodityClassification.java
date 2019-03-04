package com.turbid.basicapi.entity.shop.commodity;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

/**
 * 商品分类
 */
@Data
public class CommodityClassification {

    private static final String TABLE_CODE= "CC";

    //编号
    private String code;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(TABLE_CODE);
        }
        return code;
    }

    //分组编号
    private String gs_code;

    //分类名称
    private String name;

    //分类标题
    private String title;

    //分类logo
    private String logo;

    //分类展示图片
    private String image;

    //分类序号
    private Integer index;
}
