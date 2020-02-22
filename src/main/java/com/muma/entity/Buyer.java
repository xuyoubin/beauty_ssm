package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.PlatformEnum;

import java.util.Date;


public class Buyer extends BaseEntity {
  /**
   * 买家id
   */
  private Integer userId;
  /**
   * 平台id
   */
  private PlatformEnum platformId;
  /**
   * 平台名称
   */
  private String platformName;
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
  private String verifiId;
  /**
   * 状态 状态：0 待审核，1审核通过，2审核不通过,3平台拉黑
   */
  private Integer status;
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

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
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

  public String getVerifiId() {
    return verifiId;
  }

  public void setVerifiId(String verifiId) {
    this.verifiId = verifiId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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

  @Override
  public String toString() {
    return "Buyer{" +
            "userId=" + userId +
            ", platformId=" + platformId +
            ", platformName='" + platformName + '\'' +
            ", regPhone='" + regPhone + '\'' +
            ", regTime=" + regTime +
            ", level=" + level +
            ", weight=" + weight +
            ", verifiId='" + verifiId + '\'' +
            ", status=" + status +
            ", authImageOne='" + authImageOne + '\'' +
            ", authImageTwo='" + authImageTwo + '\'' +
            ", authImageThree='" + authImageThree + '\'' +
            ", remark='" + remark + '\'' +
            '}';
  }
}
