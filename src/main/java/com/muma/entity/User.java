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
   * 角色
   */
  private RoalEnum roalId;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RoalEnum getRoalId() {
    return roalId;
  }

  public void setRoalId(RoalEnum roalId) {
    this.roalId = roalId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
