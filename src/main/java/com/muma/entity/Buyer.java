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
   * 状态 状态：0 -待审核，1-审核通过，2-审核不通过,3-平台拉黑
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


}
