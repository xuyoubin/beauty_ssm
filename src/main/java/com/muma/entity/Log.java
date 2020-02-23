package com.my.db;

public class Log {
  private Long id;
  private Long user_id;
  private Long type;
  private String content;
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

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
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
