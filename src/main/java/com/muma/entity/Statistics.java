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
   * 商家用户id
   */
  private Integer userId;
  /**
   * 任务id
   */
  private Integer taskOrderId;
  /**
   * 任务类型 0-销售任务；1-评价任务；3-浏览任务
   */
  private TaskTypeEnum type;
  /**
   * 商家（店铺名称）
   */
  private String shopName;
  /**
   * 平台id
   */
  private Integer platformId;
  /**
   * 商品
   */
  private String commodity;
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

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public Integer getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Integer platformId) {
    this.platformId = platformId;
  }

  public String getCommodity() {
    return commodity;
  }

  public void setCommodity(String commodity) {
    this.commodity = commodity;
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
