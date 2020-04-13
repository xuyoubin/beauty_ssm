package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.OrderStatusEnum;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;

/**
 * 买家订单表
 */
public class Order extends BaseEntity {

    private static final long serialVersionUID = 2258387237604157382L;
    /**
     * 任务ID
     */
    private Integer taskOrderId;
    /**
     * 任务类型
     */
    private TaskTypeEnum type;
    /**
     * 买家手机
     */
    private String playerPhone;
    /**
     * 商家手机号码
     */
    private String businessPhone;
    /**
     * 店铺名称
     */
    private String shop;
    /**
     * 店铺ID
     */
    private Integer shopId;
    /**
     * 平台订单号
     */
    private String orderNo;
    /**
     * 订单金额
     */
    private BigDecimal money;
    /**
     * 佣金
     */
    private BigDecimal fee;
    /**
     * 0 -初始状态；10-步骤1完成；20-步骤2完成；30-提交订单 ；
     * 40-投诉单；50-退出任务；90-审核通过，100-审核不通过
     */
    private OrderStatusEnum status;
    /**
     * 审核图片1
     */
    private String authImageOne;
    /**
     * 审核图片2
     */
    private String authImageTwo;
    /**
     * 备注
     */
    private String remark;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public TaskTypeEnum getType() {
        return type;
    }

    public void setType(TaskTypeEnum type) {
        this.type = type;
    }

    public String getPlayerPhone() {
        return playerPhone;
    }

    public void setPlayerPhone(String playerPhone) {
        this.playerPhone = playerPhone;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getAuthImageOne() {
        return authImageOne;
    }

    public void setAuthImageOne(String authImageOne) {
        this.authImageOne = authImageOne;
    }

    public String getAuthImageTwo() {
        return authImageTwo;
    }

    public void setAuthImageTwo(String authImageTwo) {
        this.authImageTwo = authImageTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
