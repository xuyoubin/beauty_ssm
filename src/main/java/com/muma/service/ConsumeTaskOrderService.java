package com.muma.service;


import com.muma.common.PageBean;
import com.muma.dto.ConsumeTaskOrderDto;
import com.muma.dto.ProduceTaskOrderDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Order;
import com.muma.entity.Shop;
import com.muma.entity.Statistics;
import org.springframework.web.multipart.MultipartFile;

public interface ConsumeTaskOrderService {
	/**
	 * 获取一个任务
	 * @return
	 */
	ConsumeTaskOrderDto getOrder(UserInfoDto userInfoDto, String platform, String price, String taskType);
	/**
	 * 查询接单历史列表
	 * @return
	 */
	PageBean<Order> queryOrderHistoryList(String pageIndex, String playerPhone, String status);


}
