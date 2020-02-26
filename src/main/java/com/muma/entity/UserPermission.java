package com.muma.entity;

import com.muma.entity.base.BaseEntity;

/**
 * 菜单权限表
 */
public class UserPermission extends BaseEntity {

  private static final long serialVersionUID = 1203572725363206952L;
  /**
   * 用户ID
   */
  private Integer userId;
  /**
   * 菜单ID
   */
  private Integer menuId;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getMenuId() {
    return menuId;
  }

  public void setMenuId(Integer menuId) {
    this.menuId = menuId;
  }
}
