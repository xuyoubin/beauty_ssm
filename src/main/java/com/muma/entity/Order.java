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
     * 买家ID
     */
    private Integer playerId;
    /**
     * 用户ID
     */
    private Integer businessId;
    /**
     * 商家（店铺名称）
     */
    private String shop;
    /**
     * 商品
     */
    private String commodity;
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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
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
}
