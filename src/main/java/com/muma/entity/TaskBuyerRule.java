package com.muma.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muma.entity.base.BaseEntity;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.SexEnum;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

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
   *性别 0,1
   */
  private String sex;
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
   *操作状态:0 -未进行；1-进行中 ；2-已完成；3-任务撤销
   */
  private OperateStatusEnum operateStatus;
  /**
   * 单价
   */
  private BigDecimal price;
  /**
   * 开始时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;
  /**
   * 店铺类型
   */
  private PlatformEnum platform;

  /**
   * 复购日期
   */
  private Integer repeatDay;
  /**
   * 任务类型
   */
  private TaskTypeEnum taskType;
  /**
   * 店铺ID
   */
  private Integer shopId;
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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public PlatformEnum getPlatform() {
    return platform;
  }

  public void setPlatform(PlatformEnum platform) {
    this.platform = platform;
  }

  public OperateStatusEnum getOperateStatus() {
    return operateStatus;
  }

  public void setOperateStatus(OperateStatusEnum operateStatus) {
    this.operateStatus = operateStatus;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Integer getRepeatDay() {
    return repeatDay;
  }

  public void setRepeatDay(Integer repeatDay) {
    this.repeatDay = repeatDay;
  }

  public TaskTypeEnum getTaskType() {
    return taskType;
  }

  public void setTaskType(TaskTypeEnum taskType) {
    this.taskType = taskType;
  }

  public Integer getShopId() {
    return shopId;
  }

  public void setShopId(Integer shopId) {
    this.shopId = shopId;
  }
}
