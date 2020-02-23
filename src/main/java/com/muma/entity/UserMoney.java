package com.my.db;

public class UserMoney {
  private Long id;
  private Long user_id;
  private Long order_id;
  private Long type;
  private Double pay_money;
  private Double total_money;
  private Long status;
  private String remark;
  private String create_by;
  private java.sql.Timestamp create_time;
  private String update_by;
  private java.sql.Timestamp update_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getOrder_id() {
    return order_id;
  }

  public void setOrder_id(Long order_id) {
    this.order_id = order_id;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Double getPay_money() {
    return pay_money;
  }

  public void setPay_money(Double pay_money) {
    this.pay_money = pay_money;
  }

  public Double getTotal_money() {
    return total_money;
  }

  public void setTotal_money(Double total_money) {
    this.total_money = total_money;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }

  public String getUpdate_by() {
    return update_by;
  }

  public void setUpdate_by(String update_by) {
    this.update_by = update_by;
  }

  public java.sql.Timestamp getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
  }
}
