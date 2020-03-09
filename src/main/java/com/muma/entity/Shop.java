package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.YesAndNoEnum;

/**
 * 商家店铺
 */
public class Shop extends BaseEntity {

  private static final long serialVersionUID = -4260377774774848589L;
  /**
   * 商家用户手机
   */
  private String regPhone;
  /**
   *店铺ID
   */
  private String shopId;
  /**
   * 商家名称
   */
  private String shopName;
  /**
   * 店铺地址
   */
  private String shopUrl;
  /**
   * 店铺类型
   */
  private PlatformEnum shopType;
  /**
   * 状态：0 -待审核，1-审核通过，2-审核不通过,3-用户拉黑（该系统拉黑，防止该用户再注册）
   */
  private StatusEnum status;
  /**
   * 是否授权0是 1否
   */
  private YesAndNoEnum believe;
  /**
   * 剩余授权时间
   */
  private Integer believeDay;

  /**
   * 商家旺旺号
   */
  private String shopWw;
  /**
   * 复购日期
   */
  private Integer repeatDay;

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public String getShopId() {
    return shopId;
  }

  public void setShopId(String shopId) {
    this.shopId = shopId;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getShopUrl() {
    return shopUrl;
  }

  public void setShopUrl(String shopUrl) {
    this.shopUrl = shopUrl;
  }

  public PlatformEnum getShopType() {
    return shopType;
  }

  public void setShopType(PlatformEnum shopType) {
    this.shopType = shopType;
  }

  public String getShopWw() {
    return shopWw;
  }

  public void setShopWw(String shopWw) {
    this.shopWw = shopWw;
  }

  public Integer getRepeatDay() {
    return repeatDay;
  }

  public void setRepeatDay(Integer repeatDay) {
    this.repeatDay = repeatDay;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public YesAndNoEnum getBelieve() {
    return believe;
  }

  public void setBelieve(YesAndNoEnum believe) {
    this.believe = believe;
  }

  public Integer getBelieveDay() {
    return believeDay;
  }

  public void setBelieveDay(Integer believeDay) {
    this.believeDay = believeDay;
  }
}
