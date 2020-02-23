package com.my.db;

public class UserDetail {
  private Long id;
  private Long user_id;
  private String id_card;
  private String sex;
  private Long age;
  private String province;
  private String city;
  private Long parent_id;
  private String code;
  private Long credit;
  /**
   * 状态 状态：0 -待审核，1-审核通过，2-审核不通过,3-用户拉黑（该系统拉黑，防止该用户再注册） 4-平台拉黑（如淘宝平台）
   */
  private Long status;
  private String bank_id;
  private String bank_name;
  private String bank_user;
  private String phone;
  private String id_white;
  private String id_black;
  private String photo;
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

  public String getId_card() {
    return id_card;
  }

  public void setId_card(String id_card) {
    this.id_card = id_card;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Long getParent_id() {
    return parent_id;
  }

  public void setParent_id(Long parent_id) {
    this.parent_id = parent_id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getCredit() {
    return credit;
  }

  public void setCredit(Long credit) {
    this.credit = credit;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getBank_id() {
    return bank_id;
  }

  public void setBank_id(String bank_id) {
    this.bank_id = bank_id;
  }

  public String getBank_name() {
    return bank_name;
  }

  public void setBank_name(String bank_name) {
    this.bank_name = bank_name;
  }

  public String getBank_user() {
    return bank_user;
  }

  public void setBank_user(String bank_user) {
    this.bank_user = bank_user;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getId_white() {
    return id_white;
  }

  public void setId_white(String id_white) {
    this.id_white = id_white;
  }

  public String getId_black() {
    return id_black;
  }

  public void setId_black(String id_black) {
    this.id_black = id_black;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
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
