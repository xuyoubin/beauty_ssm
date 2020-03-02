package com.muma.dto;

import com.muma.entity.Buyer;
import com.muma.entity.base.BaseEntity;
import com.muma.enums.RoalEnum;
import com.muma.enums.SexEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.YesAndNoEnum;

import java.util.List;

/**
 * 用户全部信息DTO
 */
public class UserInfoDto {


  private static final long serialVersionUID = 9166348054973519985L;

  /**
   * 注册手机号
   */
  private String regPhone;
  /**
   * 角色
   */
  private RoalEnum roalId;
  /**
   * 身份证号码
   */
  private String idCard;
  /**
   * 真实名称
   */
  private String realName;
  /**
   * 性别
   */
  private SexEnum sex;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 省份
   */
  private String provinceCode;
  /**
   * 城市
   */
  private String cityCode;
  /**
   * 上级买家
   */
  private Long parentId;
  /**
   * 推荐码
   */
  private String code;
  /**
   * 信用
   */
  private Long credit;
  /**
   * 状态 状态：0 -待审核，1-审核通过，2-审核不通过,3-用户拉黑（该系统拉黑，防止该用户再注册） 4-平台拉黑（如淘宝平台）
   */
  private StatusEnum status;
  /**
   * 银行账号
   */
  private String bankId;
  /**
   * 银行名称
   */
  private String bankName;
  /**
   * 开户手机
   */
  private String phone;
  /**
   * 身份证正面
   */
  private String idWhite;
  /**
   * 身份证反面
   */
  private String idBlack;
  /**
   * 本人照片
   */
  private String photo;

  /**
   * 注册平台信息集合
   */
  private List<Buyer> buyerList;

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public RoalEnum getRoalId() {
    return roalId;
  }

  public void setRoalId(RoalEnum roalId) {
    this.roalId = roalId;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public SexEnum getSex() {
    return sex;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getProvinceCode() {
    return provinceCode;
  }

  public void setProvinceCode(String provinceCode) {
    this.provinceCode = provinceCode;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
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

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getIdWhite() {
    return idWhite;
  }

  public void setIdWhite(String idWhite) {
    this.idWhite = idWhite;
  }

  public String getIdBlack() {
    return idBlack;
  }

  public void setIdBlack(String idBlack) {
    this.idBlack = idBlack;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public List<Buyer> getBuyerList() {
    return buyerList;
  }

  public void setBuyerList(List<Buyer> buyerList) {
    this.buyerList = buyerList;
  }
}
