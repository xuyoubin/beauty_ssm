package com.my.db;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.StatusEnum;
import com.muma.enums.YesAndNoEnum;

public class UserDetail extends BaseEntity {


  private static final long serialVersionUID = -5261987733001241788L;
  /**
   * 用户ID
   */
  private Integer userId;
  /**
   * 身份证号码
   */
  private String idCard;
  /**
   *
   */
  private YesAndNoEnum sex;
  private Integer age;
  private String province;
  private String city;
  private Long parentId;
  private String code;
  private Long credit;
  /**
   * 状态 状态：0 -待审核，1-审核通过，2-审核不通过,3-用户拉黑（该系统拉黑，防止该用户再注册） 4-平台拉黑（如淘宝平台）
   */
  private StatusEnum status;
  private String bank_id;
  private String bank_name;
  private String bank_user;
  private String phone;
  private String id_white;
  private String id_black;
  private String photo;

}
