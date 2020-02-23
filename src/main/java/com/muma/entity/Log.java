package com.muma.entity;

import com.muma.enums.LogTypeEnum;

/**
 * 日志表
 */
public class Log {
  /**
   * 用户ID
   */
  private Integer userId;
  /**
   * 操作类型
   */
  private LogTypeEnum type;
  /**
   * 内容
   */
  private String content;
  /**
   * 备注
   */
  private String remark;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public LogTypeEnum getType() {
    return type;
  }

  public void setType(LogTypeEnum type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
