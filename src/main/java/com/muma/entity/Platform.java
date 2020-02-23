package com.muma.entity;

import com.muma.entity.base.BaseEntity;

/**
 * 平台实体类
 */
public class Platform extends BaseEntity {

  private static final long serialVersionUID = -252698927592341173L;
  /**
   * 平台名称
   */
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
