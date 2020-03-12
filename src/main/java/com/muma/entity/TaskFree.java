package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;

/**
 * 佣金表
 */
public class TaskFree extends BaseEntity {


  private static final long serialVersionUID = -1915858718035201865L;
  /**
   * 佣金类型
   */
  private TaskTypeEnum type;
  /**
   * 商家佣金值
   */
  private BigDecimal businessFree;

  /**
   *买家佣金值
   */
  private BigDecimal buyerFree;
  /**
   * 单价最小值
   */
  private BigDecimal priceMin;
  /**
   * 单价最大值
   */
  private BigDecimal priceMax;
  /**
   * 其他条件限制 评论佣金计算用该字段 1-普通评论 2-评论+图片
   */
  private Integer condition;
  /**
   * 备注
   */
  private String remark;

  public TaskTypeEnum getType() {
    return type;
  }

  public void setType(TaskTypeEnum type) {
    this.type = type;
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

  public BigDecimal getPriceMin() {
    return priceMin;
  }

  public void setPriceMin(BigDecimal priceMin) {
    this.priceMin = priceMin;
  }

  public BigDecimal getPriceMax() {
    return priceMax;
  }

  public void setPriceMax(BigDecimal priceMax) {
    this.priceMax = priceMax;
  }

  public Integer getCondition() {
    return condition;
  }

  public void setCondition(Integer condition) {
    this.condition = condition;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
