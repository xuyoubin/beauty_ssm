package com.muma.entity;

import com.muma.entity.base.BaseEntity;

public class Notice extends BaseEntity {

  private static final long serialVersionUID = -8156510561904414668L;
  /**
   * 用户ID
   */
  private Integer user_id;
  /**
   * 标题
   */
  private String title;
  /**
   * 排序
   */
  private Integer sortNo;
  /**
   * 内容
   */
  private String content;

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getSortNo() {
    return sortNo;
  }

  public void setSortNo(Integer sortNo) {
    this.sortNo = sortNo;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
