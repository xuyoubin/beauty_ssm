package com.muma.service;

import com.muma.entity.Order;

import java.util.List;

public interface OrderService {

	/**
	 * 查询用户未完成列表
	 * @return
	 */
	List<Order> getNotFinishOrderList(String regPhone);

}
