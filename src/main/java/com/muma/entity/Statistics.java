package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;

/**
 * 销售浏览任务统计表
 */
public class Statistics extends BaseEntity {

  private static final long serialVersionUID = -5473571056485803852L;
  /**
   * 商家注册手机号
   */
  private String businessPhone;
  /**
   * 任务id
   */
  private Integer taskOrderId;
  /**
   * 预计总本金
   */
  private BigDecimal taskCountCapital;
  /**
   * 该任务实收金额
   */
  private BigDecimal taskRealIncome;
  /**
   * 已买金额本金
   */
  private BigDecimal taskOverCapital;
  /**
   * 预计总佣金
   */
  private BigDecimal taskCountFee;
  /**
   * 实际总佣金
   */
  private BigDecimal taskOverFee;
  /**
   * 完成笔数
   */
  private Integer taskOverNumber;
  /**
   * 商家发布笔数
   */
  private Integer taskNumber;
  /**
   * 单价
   */
  private BigDecimal price;
  /**
   * 卖家佣金
   */
  private BigDecimal businessFree;
  /**
   * 买家佣金
   */
  private BigDecimal buyerFree;

  public String getBusinessPhone() {
    return businessPhone;
  }

  public void setBusinessPhone(String businessPhone) {
    this.businessPhone = businessPhone;
  }

  public Integer getTaskOrderId() {
    return taskOrderId;
  }

  public void setTaskOrderId(Integer taskOrderId) {
    this.taskOrderId = taskOrderId;
  }

  public BigDecimal getTaskCountCapital() {
    return taskCountCapital;
  }

  public void setTaskCountCapital(BigDecimal taskCountCapital) {
    this.taskCountCapital = taskCountCapital;
  }

  public BigDecimal getTaskRealIncome() {
    return taskRealIncome;
  }

  public void setTaskRealIncome(BigDecimal taskRealIncome) {
    this.taskRealIncome = taskRealIncome;
  }

  public BigDecimal getTaskOverCapital() {
    return taskOverCapital;
  }

  public void setTaskOverCapital(BigDecimal taskOverCapital) {
    this.taskOverCapital = taskOverCapital;
  }

  public BigDecimal getTaskCountFee() {
    return taskCountFee;
  }

  public void setTaskCountFee(BigDecimal taskCountFee) {
    this.taskCountFee = taskCountFee;
  }

  public BigDecimal getTaskOverFee() {
    return taskOverFee;
  }

  public void setTaskOverFee(BigDecimal taskOverFee) {
    this.taskOverFee = taskOverFee;
  }

  public Integer getTaskOverNumber() {
    return taskOverNumber;
  }

  public void setTaskOverNumber(Integer taskOverNumber) {
    this.taskOverNumber = taskOverNumber;
  }

  public Integer getTaskNumber() {
    return taskNumber;
  }

  public void setTaskNumber(Integer taskNumber) {
    this.taskNumber = taskNumber;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getBusinessFree() {
    return businessFree;
  }

  public void setBusinessFree(BigDecimal businessFree) {
    this.businessFree = businessFree;
  }

  public BigDecimal getBuyerFree() {
    return buyerFree;
  }

  public void setBuyerFree(BigDecimal buyerFree) {
    this.buyerFree = buyerFree;
  }

}
