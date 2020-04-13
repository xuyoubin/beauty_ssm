package com.muma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 获取任务入参实体类
 */
public class TaskBuyerRuleDto {

  /**
   * 年龄类型
   */
  private String age;
   /**
   * 性别
   */
  private String sex;
  /**
   * 省份
   */
  private String province;
  /**
   * 信用限制
   */
  private Integer credit;
  /**
   * 用户标签
   */
  private String tags;
  /**
   *操作状态:0 -未进行；1-进行中 ；2-已完成；3-任务撤销
   */
  private Integer operateStatus;
  /**
   * 开始时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;
  /**
   * 单价
   */
  private BigDecimal price;
  /**
   * 店铺类型
   */
  private List<Integer> platformIds;
  /**
   * 任务类型
   */
  private List<Integer> taskTypes;
  /**
   * 开始的记录
   */
  private Integer index;
  /**
   * 一次查询的条数
   */
  private Integer size;

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

  public Integer getOperateStatus() {
    return operateStatus;
  }

  public void setOperateStatus(Integer operateStatus) {
    this.operateStatus = operateStatus;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public List<Integer> getPlatformIds() {
    return platformIds;
  }

  public void setPlatformIds(List<Integer> platformIds) {
    this.platformIds = platformIds;
  }

  public List<Integer> getTaskTypes() {
    return taskTypes;
  }

  public void setTaskTypes(List<Integer> taskTypes) {
    this.taskTypes = taskTypes;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}
