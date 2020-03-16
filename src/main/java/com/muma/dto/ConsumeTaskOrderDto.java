package com.muma.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发布任务DTO
 */
public class ConsumeTaskOrderDto implements Serializable {


    private static final long serialVersionUID = -4450660240745046481L;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 商品名称
     */
    private String commodity;
    /**
     * 任务要求 0,1,3
     */
    private String taskRule;
    /**
     * 发布类型 0-销售；2-浏览
     */
    private Integer type;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 浏览入口id 0电脑 1手机
     */
    private Integer entranceId;
    /**
     * 任务备注
     */
    private String taskRemark;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 买家要求备注
     */
    private String buyerRemark;
    /**
     * 排序方式
     */
    private Integer sortFlag;
    /**
     *价格区间128,300
     */
    private String priceRange;
    /**
     * 发货区
     */
    private String place;
    /**
     * 卡位条件备注
     */
    private String conditionsRemark;


}
