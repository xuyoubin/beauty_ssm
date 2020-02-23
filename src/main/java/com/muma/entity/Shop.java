package com.muma.entity;

import com.muma.entity.base.BaseEntity;

/**
 * 商家店铺
 */
public class Shop extends BaseEntity {

  private static final long serialVersionUID = -4260377774774848589L;
  /**
   * 商家ID
   */
  private Integer userId;
  /**
   * 商家名称
   */
  private String shopName;
  /**
   * 复购日期
   */
  private Integer repeatDay;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public Integer getRepeatDay() {
    return repeatDay;
  }

  public void setRepeatDay(Integer repeatDay) {
    this.repeatDay = repeatDay;
  }
}
