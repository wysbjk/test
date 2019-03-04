package com.turbid.basicapi.entity.shop.commodity;

import com.turbid.basicapi.tools.CodeLib;
import lombok.Data;

import java.util.List;

/**
 * 商品总类
 */
@Data
public class GeneralCategory {

    private static final String TABLE_CODE= "GC";

    //编号
    private String code;

    public String getCode() {
        if (null==code) {
            return CodeLib.getCode(TABLE_CODE);
        }
        return code;
    }


    //总类名称
    private String name;

    //总类标题
    private String title;

    //总类logo
    private String logo;

    //总类展示图片
    private String image;

    //总类序号
    private Integer index;

    //分组
    private List<CommoditySubgroup> commoditySubgroupList;
}
