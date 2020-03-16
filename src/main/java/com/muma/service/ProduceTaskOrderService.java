package com.muma.service;


import com.muma.common.PageBean;
import com.muma.dto.ProduceTaskOrderDto;
import com.muma.entity.Shop;
import com.muma.entity.Statistics;
import org.springframework.web.multipart.MultipartFile;

public interface ProduceTaskOrderService {
	/**
	 * 添加一个任务
	 * @return
	 */
	void addTaskOrder(String regPhone, ProduceTaskOrderDto produceTaskOrderDto, MultipartFile mainImage);
	/**
	 * 查询任务列表
	 * @return
	 */
	PageBean<Shop> queryTaskOrder(String pageIndex, String regPhone,String shopName,String operateStatus,String status,String taskType);

	/**
	 * 计算费用
	 * @param taskNumber
	 * @param price
	 * @param platform
	 * @param taskType
	 * @return
	 */
	Statistics countMoney(String taskNumber,String price,String platform,String taskType );
}
