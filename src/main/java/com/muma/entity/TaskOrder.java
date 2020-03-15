package com.muma.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muma.entity.base.BaseEntity;
import com.muma.enums.EntranceEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;
import com.muma.enums.YesAndNoEnum;

import java.util.Date;

/**
 * 销售发布任务
 */
public class TaskOrder extends BaseEntity {

  private static final long serialVersionUID = -8999200277614292243L;
  /**
   * 商家注册手机号
   */
  private String businessPhone;
  /**
   * 商店id
   */
  private Integer shopId;
  /**
   * 商品名称
   */
  private String commodity;
  /**
   * 商品地址
   */
  private String commodityUrl;
  /**
   * 商品ID
   */
  private String commodityId;
  /**
   * 主图
   */
  private String mainImage;
  /**
   * 任务要求
   */
  private String taskRule;
  /**
   * 发布类型 0-销售；2-浏览
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;
  /**
   * 关键词
   */
  private String keyword;
  /**
   * 浏览入口id
   */
  private EntranceEnum entranceId;
  /**
   * 是否给买家打入关键字标签：0是，1否
   */
  private YesAndNoEnum tagFlag;
  /**
   * 是否查询淘宝客订单： 0是，1否
   */
  private YesAndNoEnum tbkFlag;
  /**
   * 是否需要确认收货0-是，1-否
   */
  private YesAndNoEnum defineFlag;
  /**
   * 审核不通过原因
   */
  private String reason;
  /**
   * 备注
   */
  private String remark;

  public String getBusinessPhone() {
    return businessPhone;
  }

  public void setBusinessPhone(String businessPhone) {
    this.businessPhone = businessPhone;
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

  public String getCommodityUrl() {
    return commodityUrl;
  }

  public void setCommodityUrl(String commodityUrl) {
    this.commodityUrl = commodityUrl;
  }

  public String getCommodityId() {
    return commodityId;
  }

  public void setCommodityId(String commodityId) {
    this.commodityId = commodityId;
  }

  public String getMainImage() {
    return mainImage;
  }

  public void setMainImage(String mainImage) {
    this.mainImage = mainImage;
  }

  public String getTaskRule() {
    return taskRule;
  }

  public void setTaskRule(String taskRule) {
    this.taskRule = taskRule;
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

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public EntranceEnum getEntranceId() {
    return entranceId;
  }

  public void setEntranceId(EntranceEnum entranceId) {
    this.entranceId = entranceId;
  }

  public YesAndNoEnum getTagFlag() {
    return tagFlag;
  }

  public void setTagFlag(YesAndNoEnum tagFlag) {
    this.tagFlag = tagFlag;
  }

  public YesAndNoEnum getTbkFlag() {
    return tbkFlag;
  }

  public void setTbkFlag(YesAndNoEnum tbkFlag) {
    this.tbkFlag = tbkFlag;
  }

  public YesAndNoEnum getDefineFlag() {
    return defineFlag;
  }

  public void setDefineFlag(YesAndNoEnum defineFlag) {
    this.defineFlag = defineFlag;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
