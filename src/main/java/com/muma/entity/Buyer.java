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
   * 注册手机号
   */
  private String regPhone;
  /**
   * 平台id
   */
  private PlatformEnum platformId;
  /**
   * 会员名称
   */
  private String name;
  /**
   * 注册时间
   */
  private Date regTime;
  /**
   * 心值
   */
  private Integer heart;
  /**
   * 账号等级
   */
  private Integer level;
  /**
   * 淘气值,京享值
   */
  private Integer naughty;
  /**
   * 是否开通花呗，白条：0是，1否
   */
  private YesAndNoEnum open;
  /**
   * 实名认证 0-是；1否
   */
  private YesAndNoEnum believe;
  /**
   * 状态：0初始化，1 待审核，2审核通过，3审核不通过,
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
   * 认证图片4
   */
  private String authImageFour;
  /**
   * 备注
   */
  private String remark;

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public PlatformEnum getPlatformId() {
    return platformId;
  }

  public void setPlatformId(PlatformEnum platformId) {
    this.platformId = platformId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getRegTime() {
    return regTime;
  }

  public void setRegTime(Date regTime) {
    this.regTime = regTime;
  }

  public Integer getHeart() {
    return heart;
  }

  public void setHeart(Integer heart) {
    this.heart = heart;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getNaughty() {
    return naughty;
  }

  public void setNaughty(Integer naughty) {
    this.naughty = naughty;
  }

  public YesAndNoEnum getOpen() {
    return open;
  }

  public void setOpen(YesAndNoEnum open) {
    this.open = open;
  }

  public YesAndNoEnum getBelieve() {
    return believe;
  }

  public void setBelieve(YesAndNoEnum believe) {
    this.believe = believe;
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

  public String getAuthImageFour() {
    return authImageFour;
  }

  public void setAuthImageFour(String authImageFour) {
    this.authImageFour = authImageFour;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
