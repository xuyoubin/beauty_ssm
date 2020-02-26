package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.BuyerRuleEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.SellTaskRuleEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售发布任务
 */
public class TaskOrder extends BaseEntity {

  private static final long serialVersionUID = -8999200277614292243L;
  /**
   * 商家账号id
   */
  private Integer userId;
  /**
   * 商店id
   */
  private Integer shopId;
  /**
   * 商品
   */
  private String commodity;
  /**
   * 主图
   */
  private String mainImg;
  /**
   * 任务要求
   */
  private SellTaskRuleEnum taskRule;
  /**
   * 买家要求
   */
  private BuyerRuleEnum buyerRule;
  /**
   * 买家信用要求
   */
  private Integer credit;
  /**
   * 发布类型
   */
  private TaskTypeEnum type;
  /**
   * 状态 ：0 待审核，1审核通过，2审核不通过
   */
  private StatusEnum status;
  /**
   *操作状态:0 -未进行；1-进行中 ；2-已完成；3-任务撤销
   */
  private OperateStatusEnum operateStatus;
  /**
   * 开始时间
   */
  private Date startTime;
  /**
   * 平台ID
   */
  private Integer platformId;
  /**
   * 关键词
   */
  private String keyword;
  /**
   * 浏览入口id
   */
  private Integer entranceId;
  /**
   * 卡位条件
   */
  private String conditions;
  /**
   * 笔数
   */
  private Integer number;
  /**
   * 单价
   */
  private BigDecimal price;
  /**
   * 佣金
   */
  private BigDecimal fee;
  /**
   * 备注
   */
  private String remark;
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

  public Integer getShopId() {
    return shopId;
  }

  public void setShopId(Integer shopId) {
    this.shopId = shopId;
  }

  public String getCommodity() {
    return commodity;
  }

  public void setCommodity(String commodity) {
    this.commodity = commodity;
  }

  public String getMainImg() {
    return mainImg;
  }

  public void setMainImg(String mainImg) {
    this.mainImg = mainImg;
  }

  public SellTaskRuleEnum getTaskRule() {
    return taskRule;
  }

  public void setTaskRule(SellTaskRuleEnum taskRule) {
    this.taskRule = taskRule;
  }

  public BuyerRuleEnum getBuyerRule() {
    return buyerRule;
  }

  public void setBuyerRule(BuyerRuleEnum buyerRule) {
    this.buyerRule = buyerRule;
  }

  public Integer getCredit() {
    return credit;
  }

  public void setCredit(Integer credit) {
    this.credit = credit;
  }

  public TaskTypeEnum getType() {
    return type;
  }

  public void setType(TaskTypeEnum type) {
    this.type = type;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
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

  public Integer getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Integer platformId) {
    this.platformId = platformId;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Integer getEntranceId() {
    return entranceId;
  }

  public void setEntranceId(Integer entranceId) {
    this.entranceId = entranceId;
  }

  public String getConditions() {
    return conditions;
  }

  public void setConditions(String conditions) {
    this.conditions = conditions;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }
}
