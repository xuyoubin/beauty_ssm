package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.RoalEnum;

/**
 * 用户登录表
 */
public class User extends BaseEntity {

  private static final long serialVersionUID = 8034989383095978999L;
  /**
   * 注册手机号
   */
  private String regPhone;
  /**
   * 密码
   */
  private String password;

  /**
   *公网IP地址
   */
  private String ipAddress;
  /**
   * 设备唯一识别号
   */
  private String uniqueId;

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }
}
