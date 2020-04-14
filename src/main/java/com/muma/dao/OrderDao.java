package com.muma.dao;

import com.muma.entity.Order;
import com.muma.enums.OrderStatusEnum;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderDao {

	/**
     * 插入订单明细
     * @param
     * @return
     */
    int addOrder(Order order);
    /**
     * 根据店铺和手机号码查询最后一单（30,40,60,90,100,110）
     * @param shopId
     * @return
     */
    Order queryByShopId(@Param("shopId") Integer shopId,@Param("buyerPhone") String buyerPhone);
    /**
     * 查询用户未完成任务单（当天需要完成）状态(0,10,20,40) 任务类型(0,1,2,4)
     */
    List<Order> queryNotFinishToday(@Param("buyerPhone") String buyerPhone);
    /**
     * 查询用户隔天购任务 （当天是否到20状态）状态(0,10) 任务类型(3)
     * 如果没有则视为未完成任务
     */
    List<Order> queryNot20Status(@Param("buyerPhone") String buyerPhone);
    /**
     * 查询用户隔天购任务 （隔天是否为提交状态）
     * 如果没有则视为未完成任务 状态(0,10) 任务类型(3)
     */
    List<Order> queryNotFinishTomorrow(@Param("buyerPhone") String buyerPhone);
    /**
     * 查询买家今天完成任务
     */
    Integer queryFinishToday(@Param("buyerPhone") String buyerPhone);
    /**
     * 查询买家最近7天完成任务
     */
    Integer queryFinish7Days(@Param("buyerPhone") String buyerPhone);
    /**
     *查询买家最近30天完成任务
     */
    Integer queryFinish30Days(@Param("buyerPhone") String buyerPhone);

    /**
     * 根据任务ID 查询 未完成任务条数
     * @param taskOrderId
     * @return
     */
    Integer queryByTaskOrderId(@Param("taskOrderId") Integer taskOrderId);
    /**
     * 查询近60天的历史任务数量
     */
    Integer count(@Param("playerPhone") String playerPhone,@Param("businessPhone") String businessPhone,
                  @Param("shopId") String shopId,@Param("statusList") List<Integer> statusList);
    /**
     * 查询近60天的历史任务
     */
    List<Order> queryOrderHistoryList(@Param("playerPhone") String playerPhone,@Param("businessPhone") String businessPhone,
                                      @Param("shopId") String shopId,@Param("statusList") List<Integer> statusList,
                                      @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据主键查询订单信息
     */
    Order queryById(@Param("id") Integer id);
    /**
     * 根据主键更新状态
     */
    void updateOrderStatus(@Param("id") Integer id, @Param("status")OrderStatusEnum orderStatusEnum,
                           @Param("updateBy") String operator);
}
