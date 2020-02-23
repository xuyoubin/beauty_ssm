package com.muma.entity;

import com.muma.entity.base.BaseEntity;

/**
 * 入口类型
 */
public class Entrance extends BaseEntity{

  private static final long serialVersionUID = -6252360633154386844L;
  /**
   *入口名称
   */
  private String name;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
