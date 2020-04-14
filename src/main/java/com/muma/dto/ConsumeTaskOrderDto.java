package com.muma.dto;

import com.muma.enums.EntranceEnum;
import com.muma.enums.OrderStatusEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.SortEnum;
import com.muma.enums.TaskTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发布任务DTO
 */
public class ConsumeTaskOrderDto  {

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
    private TaskTypeEnum taskTypeEnum;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 浏览入口id 0电脑 1手机
     */
    private EntranceEnum entranceEnum;
    /**
     * 任务备注
     */
    private String taskRemark;
    /**
     * 平台类型
     */
    private PlatformEnum platformEnum;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 订单状态
     */
    private OrderStatusEnum orderStatusEnum;
    /**
     * 买家要求备注
     */
    private String buyerRemark;
    /**
     * 排序方式
     */
    private SortEnum sortFlag;
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

    /**
     * 主图
     */
    private String base64image;

    /**
     *剩余时间
     */
    private Integer remainTime;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getTaskRule() {
        return taskRule;
    }

    public void setTaskRule(String taskRule) {
        this.taskRule = taskRule;
    }

    public TaskTypeEnum getTaskTypeEnum() {
        return taskTypeEnum;
    }

    public void setTaskTypeEnum(TaskTypeEnum taskTypeEnum) {
        this.taskTypeEnum = taskTypeEnum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public EntranceEnum getEntranceEnum() {
        return entranceEnum;
    }

    public void setEntranceEnum(EntranceEnum entranceEnum) {
        this.entranceEnum = entranceEnum;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public PlatformEnum getPlatformEnum() {
        return platformEnum;
    }

    public void setPlatformEnum(PlatformEnum platformEnum) {
        this.platformEnum = platformEnum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderStatusEnum getOrderStatusEnum() {
        return orderStatusEnum;
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public SortEnum getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(SortEnum sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getConditionsRemark() {
        return conditionsRemark;
    }

    public void setConditionsRemark(String conditionsRemark) {
        this.conditionsRemark = conditionsRemark;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }

    public Integer getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Integer remainTime) {
        this.remainTime = remainTime;
    }
}
