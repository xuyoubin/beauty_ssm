package com.muma.service.impl;


import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.muma.common.PageBean;
import com.muma.dao.BuyerDao;
import com.muma.dao.OrderDao;
import com.muma.dao.TaskBuyerRuleDao;
import com.muma.dao.UserDao;
import com.muma.dto.ConsumeTaskOrderDto;
import com.muma.dto.TaskBuyerRuleDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Buyer;
import com.muma.entity.Order;
import com.muma.entity.TaskBuyerRule;
import com.muma.entity.User;
import com.muma.enums.BuyerAgeEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;
import com.muma.exception.BizException;
import com.muma.service.ConsumeTaskOrderService;
import com.muma.service.OrderService;
import com.muma.util.IPUtil;
import com.muma.util.Precondition;
import com.muma.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 获取任务处理类
 */
@Service
public class ConsumeTaskOrderServiceImpl implements ConsumeTaskOrderService {

	private static final int INDEX = 0;
	private static final int SIZE = 5;

	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskBuyerRuleDao taskBuyerRuleDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
    private OrderService orderService;
	/**
	 * 获取一个任务
	 * @return
	 */
    @Transactional(rollbackFor = Exception.class)
	@Override
	public ConsumeTaskOrderDto getOrder(UserInfoDto userInfoDto, String platform, String price, String taskType){
		// TODO 1.0版本不做平台控制，只要通过认证则默认全部可以接
		// TODO 1.0版本价格不做控制，后期可根据信用限制
		//任务类型 只有销售和浏览
		//获取本机公网IP，防止多用户在同一个公网IP登录
		List<Integer> platformIds = Lists.newArrayList(); //允许接手任务的平台
		String publicIp = IPUtil.publicip();
		if(StringUtils.isNotEmpty(publicIp)){
			User user = userDao.queryByIp(publicIp);
			if(user != null){
				Precondition.checkState(userInfoDto.getRegPhone().equals(user.getRegPhone()) , "网络已经被占用，请切换网络连接!");
			}
		}
		//用户是否通过认证认证
		Precondition.checkState(StatusEnum.CONFIRM_PASS.equals(userInfoDto.getStatus()),"用户未通过审核,请先去填写认证信息");
		//用户可接手平台
		List<Buyer> buyerList = userInfoDto.getBuyerList();
		if(buyerList == null || buyerList.size() == 0){
			throw new BizException("用户未认证平台，请先去填写平台认证信息！");
		}
		for (Buyer buyer : buyerList) {
            if(StatusEnum.CONFIRM_PASS.equals(buyer.getStatus())){
            	platformIds.add(buyer.getId());
			}
		}
		if(platformIds == null && platformIds.size() == 0){
			throw new BizException("用户平台为认证通过，请根据提示修改！");
		}
		/**
		 * 查询约5条的任务记录
		 * 如果不够五条记录递归查询
         * 查询是否有未完成任务
		 */
        List<Order> orderList = orderService.getNotFinishOrderList(userInfoDto.getRegPhone());
        Precondition.checkState(orderList.size() == 0,"当前有未完成任务，请完成后再来！");
        TaskBuyerRuleDto taskBuyerRuleDto = buildTaskBuyerRule(userInfoDto,platformIds,price,taskType);
         //根据规则查询可接手任务
		Integer totalTask = taskBuyerRuleDao.countByTaskBuyerRuleDto(taskBuyerRuleDto);
		Precondition.checkState(totalTask > 0,"当前无匹配的任务，请稍后再来！");
		List<TaskBuyerRule> taskBuyerRules = getRealTaskBuyerRules(taskBuyerRuleDto,totalTask,userInfoDto.getRegPhone());
		for (TaskBuyerRule t : taskBuyerRules) {
			//查询任务
            getTaskOrder(t);
		}
		//生成订单

		return null;
	}
	/**
	 * 查询接单历史列表
	 * @return
	 */
	@Override
	public  PageBean<Order> queryOrderHistoryList(String pageIndex, String regPhone, String status){
		return null;
	}


	/**
	 * 组装任务查询参数
	 * @param userInfoDto
	 * @return
	 */
	private TaskBuyerRuleDto buildTaskBuyerRule(UserInfoDto userInfoDto,List<Integer> platformIds, String price, String taskType ){
		Precondition.checkState(StringUtils.isNotBlank(taskType), "请选择任务类型!");
		TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(Integer.valueOf(taskType));
		Precondition.checkNotNull(taskTypeEnum, "暂不支持该任务类型!");
		TaskBuyerRuleDto taskBuyerRuleDto = new TaskBuyerRuleDto();
		String ageStr = BuyerAgeEnum.stateOfAge(userInfoDto.getAge()).getValue().toString();
		taskBuyerRuleDto.setAge(ageStr);
		taskBuyerRuleDto.setSex(userInfoDto.getSex().getValue().toString());
		taskBuyerRuleDto.setProvince(userInfoDto.getProvinceCode());
//		taskBuyerRuleDto.setCredit(userInfoDto.getCredit());
		taskBuyerRuleDto.setOperateStatus(OperateStatusEnum.TASK_PROCESS.getValue());
//		taskBuyerRuleDto.setPrice(new BigDecimal(price));
		taskBuyerRuleDto.setStartTime(TimeUtils.getNowDate());
        taskBuyerRuleDto.setPlatformIds(platformIds);
        taskBuyerRuleDto.setTaskType(taskTypeEnum.getValue());
        taskBuyerRuleDto.setIndex(INDEX);
        taskBuyerRuleDto.setSize(SIZE);
		return taskBuyerRuleDto;
	}

	/**
	 * 查询多个（5）任务
	 * @param taskBuyerRuleDto
	 * @return
	 */
	private List<TaskBuyerRule> getRealTaskBuyerRules(TaskBuyerRuleDto taskBuyerRuleDto,Integer totalTask,String buyerPhone){
		List<TaskBuyerRule> realTaskBuyerRules = Lists.newArrayList(); //获取实际有用的
		List<TaskBuyerRule> taskBuyerRules = taskBuyerRuleDao.queryByTaskBuyerRuleDto(taskBuyerRuleDto);
		Precondition.checkState(taskBuyerRules != null && taskBuyerRules.size()>0,"当前无匹配的任务，请稍后再来！");
		for (TaskBuyerRule t : taskBuyerRules) {
			Integer shopId = t.getShopId();
			Integer repeatDay = t.getRepeatDay();
            Order order = orderDao.queryByShopId(shopId,buyerPhone);
            String currentTime = TimeUtils.getStringDate();
            String createTime = TimeUtils.dateToStrLong(order.getCreateTime());
            String days = TimeUtils.getTwoDay(currentTime,createTime);
            if(Integer.valueOf(days)>= repeatDay){
                realTaskBuyerRules.add(t);
            }
		}
		if(realTaskBuyerRules.size() < 20){
			Integer currentTotal = taskBuyerRuleDto.getIndex()+taskBuyerRuleDto.getSize();
			if(totalTask > currentTotal){
				taskBuyerRuleDto.setIndex(currentTotal);
				getRealTaskBuyerRules(taskBuyerRuleDto,totalTask,buyerPhone);
			}else {
			    return realTaskBuyerRules;
            }
		}
		return  realTaskBuyerRules;
	}

	private synchronized void getTaskOrder(){

    }



}
