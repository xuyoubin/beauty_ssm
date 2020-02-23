package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.YesAndNoEnum;

import java.util.Date;

/**
 *买家平台认证信息
 */
public class Buyer extends BaseEntity {

  private static final long serialVersionUID = -586488352413370705L;
  /**
   * 买家id
   */
  private Integer userId;
  /**
   * 平台id
   */
  private PlatformEnum platformId;
  /**
   * 注册手机号
   */
  private String regPhone;
  /**
   * 注册时间
   */
  private Date regTime;
  /**
   * 账号等级
   */
  private Integer level;
  /**
   * 账号权重
   */
  private Integer weight;
  /**
   * 实名认证 0-是；1否
   */
  private YesAndNoEnum verifiId;
  /**
   * 状态 状态：0 -待审核，1-审核通过，2-审核不通过,3-用户拉黑（该系统拉黑，防止该用户再注册） 4-平台拉黑（如淘宝平台）
   */
  private StatusEnum status;
  /**
   *认证图片1
   */
  private String authImageOne;
  /**
   *认证图片2
   */
  private String authImageTwo;
  /**
   *认证图片3
   */
  private String authImageThree;
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

  public PlatformEnum getPlatformId() {
    return platformId;
  }

  public void setPlatformId(PlatformEnum platformId) {
    this.platformId = platformId;
  }

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public Date getRegTime() {
    return regTime;
  }

  public void setRegTime(Date regTime) {
    this.regTime = regTime;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public YesAndNoEnum getVerifiId() {
    return verifiId;
  }

  public void setVerifiId(YesAndNoEnum verifiId) {
    this.verifiId = verifiId;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
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

  public String getAuthImageThree() {
    return authImageThree;
  }

  public void setAuthImageThree(String authImageThree) {
    this.authImageThree = authImageThree;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
