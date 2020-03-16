package com.muma.service.impl;


import com.muma.common.PageBean;
import com.muma.dto.ConsumeTaskOrderDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Order;
import com.muma.service.ConsumeTaskOrderService;

public class ConsumeTaskOrderServiceImpl implements ConsumeTaskOrderService {
	/**
	 * 获取一个任务
	 * @return
	 */
	ConsumeTaskOrderDto getOrder(UserInfoDto userInfoDto, String platform, String price, String taskType){
		return null;
	}
	/**
	 * 查询接单历史列表
	 * @return
	 */
	PageBean<Order> queryOrderHistoryList(String pageIndex, String regPhone, String status){
		return null;
	}


}
