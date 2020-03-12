package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.SexEnum;
import com.muma.enums.SortEnum;

/**
 * 卡位条件表
 */
public class TaskConditions extends BaseEntity {


  private static final long serialVersionUID = 8440377425400867016L;
  /**
   * 任务id
   */
  private Integer taskOrderId;
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
   * 备注
   */
  private String remark;

  public Integer getTaskOrderId() {
    return taskOrderId;
  }

  public void setTaskOrderId(Integer taskOrderId) {
    this.taskOrderId = taskOrderId;
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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
