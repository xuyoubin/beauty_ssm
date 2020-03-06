package com.muma.dto;

import com.muma.entity.Buyer;
import com.muma.enums.RoalEnum;
import com.muma.enums.SexEnum;
import com.muma.enums.StatusEnum;

import java.util.List;

/**
 * 用户全部信息DTO
 */
public class ShareUserDto {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 注册手机号
   */
  private String regPhone;
  /**
   * 角色
   */
  private RoalEnum roalId;
  /**
   * 真实名称
   */
  private String realName;
  /**
   * 性别
   */
  private SexEnum sex;
  /**
   * 状态：0-初始化 ，1-待审核，2-审核通过，3-审核不通过,4-用户拉黑（该系统拉黑，防止该用户再注册）
   */
  private StatusEnum status;
  /**
   * 完成任务，按销售任务计算
   */
  private Integer finishTaskTotal;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public RoalEnum getRoalId() {
    return roalId;
  }

  public void setRoalId(RoalEnum roalId) {
    this.roalId = roalId;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public SexEnum getSex() {
    return sex;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Integer getFinishTaskTotal() {
    return finishTaskTotal;
  }

  public void setFinishTaskTotal(Integer finishTaskTotal) {
    this.finishTaskTotal = finishTaskTotal;
  }
}
