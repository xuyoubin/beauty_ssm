package com.muma.service;


import com.muma.common.PageBean;
import com.muma.entity.Shop;

public interface ProduceTaskOrderService {
	/**
	 * 添加一个任务
	 * @return
	 */
	void addTaskOrder();
	/**
	 * 查询任务列表
	 * @return
	 */
	PageBean<Shop> queryTaskOrder(String pageIndex, String regPhone);
}
