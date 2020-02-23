package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.BuyerRuleEnum;
import com.muma.enums.SellTaskRuleEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

public class TaskOrder extends BaseEntity {

  private static final long serialVersionUID = -8999200277614292243L;
  /**
   * 商家账号id
   */
  private Integer userId;
  /**
   * 商家id
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
   * 状态
   */
  private StatusEnum status;
  private Integer operateStatus;
  private Date startTime;
  private Integer platformId;
  private String keyword;
  private Integer entranceId;
  private String conditions;
  private Integer number;
  private BigDecimal price;
  private BigDecimal fee;
  private String remark;
  private Integer revision;



}
