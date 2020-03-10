package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;

/**
 * 销售浏览任务统计表
 */
public class Statistics extends BaseEntity {

  private static final long serialVersionUID = -5473571056485803852L;
  /**
   * 商家注册手机号
   */
  private String businessPhone;
  /**
   * 任务id
   */
  private Integer taskOrderId;
  /**
   * 任务类型 0-销售任务；1-评价任务；2-浏览任务； 3-天浏览第二天购买任务 ；4-复购任务
   */
  private TaskTypeEnum type;
  /**
   * 预计本金
   */
  private BigDecimal corpus;
  /**
   * 实收金额
   */
  private BigDecimal realIncome;
  /**
   * 已买金额
   */
  private BigDecimal totalIncome;
  /**
   * 预计总佣金
   */
  private BigDecimal totalFee;
  /**
   * 实际总佣金
   */
  private BigDecimal realFee;
  /**
   * 完成笔数
   */
  private Integer totalNum;
  /**
   * 商家发布笔数
   */
  private Integer num;
  /**
   * 乐观锁
   */
  private Integer revision;

  public String getBusinessPhone() {
    return businessPhone;
  }

  public void setBusinessPhone(String businessPhone) {
    this.businessPhone = businessPhone;
  }

  public Integer getTaskOrderId() {
    return taskOrderId;
  }

  public void setTaskOrderId(Integer taskOrderId) {
    this.taskOrderId = taskOrderId;
  }

  public TaskTypeEnum getType() {
    return type;
  }

  public void setType(TaskTypeEnum type) {
    this.type = type;
  }

  public BigDecimal getCorpus() {
    return corpus;
  }

  public void setCorpus(BigDecimal corpus) {
    this.corpus = corpus;
  }

  public BigDecimal getRealIncome() {
    return realIncome;
  }

  public void setRealIncome(BigDecimal realIncome) {
    this.realIncome = realIncome;
  }

  public BigDecimal getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(BigDecimal totalIncome) {
    this.totalIncome = totalIncome;
  }

  public BigDecimal getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(BigDecimal totalFee) {
    this.totalFee = totalFee;
  }

  public BigDecimal getRealFee() {
    return realFee;
  }

  public void setRealFee(BigDecimal realFee) {
    this.realFee = realFee;
  }

  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }
}
