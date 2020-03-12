package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.SexEnum;

/**
 * 任务买家限制表
 */
public class TaskBuyerRule extends BaseEntity {


  private static final long serialVersionUID = 7518003941027674755L;
  /**
   * 任务id
   */
  private Integer taskOrderId;
  /**
   * 年龄范围 1：18~25；2:26~35，3:36~45,4:45~以上
   */
  private String age;
  /**
   *性别
   */
  private SexEnum sex;
  /**
   * 省份
   */
  private String province;
  /**
   * 信用
   */
  private Integer credit;

  /**
   * 用户标签
   */
  private String tags;
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

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public SexEnum getSex() {
    return sex;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public Integer getCredit() {
    return credit;
  }

  public void setCredit(Integer credit) {
    this.credit = credit;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
