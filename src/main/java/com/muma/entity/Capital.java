package com.muma.entity;

import com.muma.entity.base.BaseEntity;
import com.muma.enums.PayTypeEnum;
import com.muma.enums.StatusEnum;

import java.math.BigDecimal;

/**
 * 用户资金表
 */
public class Capital extends BaseEntity {

  private static final long serialVersionUID = -8357478479425634015L;
  /**
   * 用户注册手机
   */
  private String regPhone;
  /**
   * 余额
   */
  private BigDecimal balance;
  /**
   *  冻结金额
   */
  private BigDecimal freeze;
  /**
   *总收入
   */
  private BigDecimal totalIncome;
  /**
   *总支出
   */
  private BigDecimal totalOut;

  public String getRegPhone() {
    return regPhone;
  }

  public void setRegPhone(String regPhone) {
    this.regPhone = regPhone;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getFreeze() {
    return freeze;
  }

  public void setFreeze(BigDecimal freeze) {
    this.freeze = freeze;
  }

  public BigDecimal getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(BigDecimal totalIncome) {
    this.totalIncome = totalIncome;
  }

  public BigDecimal getTotalOut() {
    return totalOut;
  }

  public void setTotalOut(BigDecimal totalOut) {
    this.totalOut = totalOut;
  }
}
