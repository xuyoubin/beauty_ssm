package com.muma.service.impl;

import com.google.common.collect.Lists;
import com.muma.dao.OrderDao;
import com.muma.entity.Order;
import com.muma.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderDao orderDao;

	/**
	 * 查询用户未完成列表
	 * @return
	 */
	@Override
	public List<Order> getNotFinishOrderList(String regPhone) {
		List<Order> orderList = Lists.newArrayList();
		List<Order> orderList1 = orderDao.queryNotFinishToday(regPhone);
		if(orderList1 != null && orderList1.size() > 0 ){
			orderList.addAll(orderList1);
		}
		List<Order> orderList2 = orderDao.queryNot20Status(regPhone);
		if(orderList2 != null && orderList2.size() > 0 ){
			orderList.addAll(orderList2);
		}
		List<Order> orderList3 = orderDao.queryNotFinishTomorrow(regPhone);
		if(orderList3 != null && orderList3.size() > 0 ){
			orderList.addAll(orderList3);
		}
		return orderList;
	}
}
