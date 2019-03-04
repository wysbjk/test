package com.turbid.basicapi.entity.shop.commodity;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

/**
 * 商品类
 */
@Data
public class Commodity {

    private static final String TABLE_CODE= "commodity";

    //商品编号
    private String code;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(TABLE_CODE);
        }
        return code;
    }

    //分类编号
    private String cc_code;

    //商品名称
    private String name;

    //商品标题
    private String title;

    //商品价格
    private Double price;

    //商品图片
    private String image;

    //商品图片组
    private String fg_code;

}
