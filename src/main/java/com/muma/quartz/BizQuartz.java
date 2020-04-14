package com.muma.quartz;


import com.muma.entity.Order;
import com.muma.enums.OrderStatusEnum;
import com.muma.service.ConsumeTaskOrderService;
import com.muma.service.OrderService;
import com.muma.util.KeyType;
import com.muma.util.Precondition;
import com.muma.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 业务相关的作业调度
 * 
           字段               允许值                           允许的特殊字符
	秒	 	0-59	 	, - * /
	分	 	0-59	 	, - * /
	小时	 	0-23	 	, - * /
	日期	 	1-31	 	, - * ? / L W C
	月份	 	1-12 或者 JAN-DEC	 	, - * /
	星期	 	1-7 或者 SUN-SAT	 	, - * ? / L C #
	年（可选）	 	留空, 1970-2099	 	, - * /
	
	*  字符代表所有可能的值
	/  字符用来指定数值的增量
	L  字符仅被用于天（月）和天（星期）两个子表达式，表示一个月的最后一天或者一个星期的最后一天
	6L 可以表示倒数第６天
	
 * @author xuyb
 *
 */
@Component
public class BizQuartz {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());



	@Autowired
	OrderService orderService;
	@Autowired
	ConsumeTaskOrderService consumeTaskOrderService;

	/**
	 * 每隔30分钟定时取消过期任务
	 */
	@Scheduled(cron = "0 0/30 * * * ?")
	public void cancelOrderTask() {
		logger.info("-------------取消过期任务开始-----------------");
		List<Order> orderList = orderService.getNotFinishOrderList(null);
		//判断任务是否过去
		orderList.stream().forEach(order -> {
			try {
				//60为买家确认投诉单，无需取消
				if(!OrderStatusEnum.BUYER_COMPLAIN_SURE.equals(order.getStatus())){
					//剩余时间计算，判断是过期
					int minutes = TimeUtils.getTwoMinutes(order.getCreateTime());
					logger.info("两个时间差为："+minutes);
					if(minutes >= 30){
						consumeTaskOrderService.cancelOrder(order, KeyType.SYSTEM_TASK);
					}
				}
			}catch (Exception e){
                logger.info("取消任务id：{}异常，任务状态：{},异常原因：{}",order.getId(),order.getStatus(),e);
			}
		});
	}
	/**
	 * 每天23点关闭已经完成的任务
	 */
	@Scheduled(cron = "0 0 23 * * ?")
	public void closeTaskTask() {
		logger.info("-------------关闭已经完成的任务开始-----------------");

	}
}
