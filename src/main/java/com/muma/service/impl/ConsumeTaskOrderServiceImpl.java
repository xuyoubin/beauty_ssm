package com.muma.service.impl;


import com.google.common.collect.Lists;
import com.muma.common.PageBean;
import com.muma.dao.BuyerDao;
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
import com.muma.util.IPUtil;
import com.muma.util.Precondition;
import com.muma.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 获取任务处理类
 */
@Service
public class ConsumeTaskOrderServiceImpl implements ConsumeTaskOrderService {

	@Autowired
	private UserDao userDao;
	/**
	 * 获取一个任务
	 * @return
	 */
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
		 */








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
	private TaskBuyerRuleDto buildTaskBuyerRule(UserInfoDto userInfoDto,List<String> platformIds, String price, String taskType ){
		Precondition.checkState(StringUtils.isNotBlank(taskType), "请选择任务类型!");
		TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(Integer.valueOf(taskType));
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
        taskBuyerRuleDto.setTaskType(taskType);
		return taskBuyerRuleDto;
	}


}
