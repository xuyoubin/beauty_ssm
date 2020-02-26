package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.PayTypeEnum;
import com.muma.enums.StatusEnum;

import java.math.BigDecimal;

/**
 * 用户资金明细表
 */
public class UserMoney extends BaseEntity {

  private static final long serialVersionUID = -263387528916903510L;
  /**
   * 用户id
   */
  private Integer userId;
  /**
   * 任务订单Id
   */
  private Integer orderId;
  /**
   *  支付类型
   *  0-佣金收入；1-评价佣金收入；2-提现支出；3-惩罚支出；
   * 20-商家充值；21-本金支出；22-佣金支出；23-评价佣金支出；
   * 80-退款；90-冻结资金；100-邀请收入；
   */
  private PayTypeEnum type;
  /**
   *操作金额
   */
  private BigDecimal payMoney;
  /**
   * 可用余额
   */
  private BigDecimal totalMoney;
  /**
   *状态：0 待审核，1审核通过，2审核不通过,
   */
  private StatusEnum status;
  /**
   * 备注
   */
  private String remark;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public PayTypeEnum getType() {
    return type;
  }

  public void setType(PayTypeEnum type) {
    this.type = type;
  }

  public BigDecimal getPayMoney() {
    return payMoney;
  }

  public void setPayMoney(BigDecimal payMoney) {
    this.payMoney = payMoney;
  }

  public BigDecimal getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(BigDecimal totalMoney) {
    this.totalMoney = totalMoney;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
