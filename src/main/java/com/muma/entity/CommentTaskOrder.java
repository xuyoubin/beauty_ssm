package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.PlatformEnum;

import java.math.BigDecimal;

public class CommentTaskOrder extends BaseEntity {
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
  private String taskRule;
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
  private Integer status;
  /**
   * 操作状态 0 -未进行；1-进行中 ；2-已完成；3-任务撤销
   */
  private Integer operateStatus;
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



}
