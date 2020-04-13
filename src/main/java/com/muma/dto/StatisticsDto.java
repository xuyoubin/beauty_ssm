package com.muma.dto;

import com.muma.entity.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 销售浏览任务统计表
 */
public class StatisticsDto {

  /**
   * 任务id
   */
  private Integer taskOrderId;
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
   * 买家佣金
   */
  private BigDecimal buyerFree;

  /**
   * 买家备注
   */
  private String buyerRemark;

  public Integer getTaskOrderId() {
    return taskOrderId;
  }

  public void setTaskOrderId(Integer taskOrderId) {
    this.taskOrderId = taskOrderId;
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

  public BigDecimal getBuyerFree() {
    return buyerFree;
  }

  public void setBuyerFree(BigDecimal buyerFree) {
    this.buyerFree = buyerFree;
  }

  public String getBuyerRemark() {
    return buyerRemark;
  }

  public void setBuyerRemark(String buyerRemark) {
    this.buyerRemark = buyerRemark;
  }
}
