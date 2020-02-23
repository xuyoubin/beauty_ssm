package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.CommentTaskRuleEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;

import java.math.BigDecimal;

/**
 * 评价发布任务
 */
public class CommentTaskOrder extends BaseEntity {

  private static final long serialVersionUID = -8763231320035787392L;
  /**
   * 买家订单id 系统生成
   */
  private Integer orderId;
  /**
   * 商家ID
   */
  private Integer businessId;
  /**
   * 买家ID
   */
  private Integer playerId;
  /**
   * 店铺ID
   */
  private Integer shopId;
  /**
   *订单ID 平台生成
   */
  private String orderNo;
  /**
   * 商品
   */
  private String commodity;
  /**
   * 任务要求 0-自由发挥；1-指定评论；2-好评+图片；3-追评；4-追评+图片 ；
   */
  private CommentTaskRuleEnum taskRule;
  /**
   * 评价内容
   */
  private String comment;
  /**
   * 图片
   */
  private String commentImage;
  /**
   * 状态 状态：0 待审核，1审核通过，2审核不通过
   */
  private StatusEnum status;
  /**
   * 操作状态 0 -未进行；1-进行中 ；2-已完成；3-任务撤销
   */
  private OperateStatusEnum operateStatus;
  /**
   * 平台ID
   */
  private PlatformEnum platformId;
  /**
   * 佣金
   */
  private BigDecimal fee;
  /**
   * 备注
   */
  private String remark;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Integer businessId) {
    this.businessId = businessId;
  }

  public Integer getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Integer playerId) {
    this.playerId = playerId;
  }

  public Integer getShopId() {
    return shopId;
  }

  public void setShopId(Integer shopId) {
    this.shopId = shopId;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getCommodity() {
    return commodity;
  }

  public void setCommodity(String commodity) {
    this.commodity = commodity;
  }

  public CommentTaskRuleEnum getTaskRule() {
    return taskRule;
  }

  public void setTaskRule(CommentTaskRuleEnum taskRule) {
    this.taskRule = taskRule;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getCommentImage() {
    return commentImage;
  }

  public void setCommentImage(String commentImage) {
    this.commentImage = commentImage;
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

  public PlatformEnum getPlatformId() {
    return platformId;
  }

  public void setPlatformId(PlatformEnum platformId) {
    this.platformId = platformId;
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
}
