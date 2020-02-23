package com.muma.entity;

import com.muma.entity.base.BaseEntity;

/**
 * 菜单实体类
 */
public class Menu extends BaseEntity {

  private static final long serialVersionUID = -6534761559521552959L;
  /**
   * 菜单名称
   */
  private String name;
  /**
   * 菜单地址
   */
  private String url;
  /**
   * 排序顺序
   */
  private Integer sortNo;
  /**
   * 父级菜单id
   */
  private Integer pid;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getSortNo() {
    return sortNo;
  }

  public void setSortNo(Integer sortNo) {
    this.sortNo = sortNo;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }
}
