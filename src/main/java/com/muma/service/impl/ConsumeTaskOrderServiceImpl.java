package com.muma.service.impl;


import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.muma.common.PageBean;
import com.muma.dao.BuyerDao;
import com.muma.dao.OrderDao;
import com.muma.dao.ShopDao;
import com.muma.dao.StatisticsDao;
import com.muma.dao.TaskBuyerRuleDao;
import com.muma.dao.TaskConditionsDao;
import com.muma.dao.TaskOrderDao;
import com.muma.dao.UserDao;
import com.muma.dto.ConsumeTaskOrderDto;
import com.muma.dto.StatisticsDto;
import com.muma.dto.TaskBuyerRuleDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Buyer;
import com.muma.entity.Order;
import com.muma.entity.Shop;
import com.muma.entity.Statistics;
import com.muma.entity.TaskBuyerRule;
import com.muma.entity.TaskConditions;
import com.muma.entity.TaskOrder;
import com.muma.entity.User;
import com.muma.enums.BuyerAgeEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.OrderStatusEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;
import com.muma.exception.BizException;
import com.muma.service.ConsumeTaskOrderService;
import com.muma.service.OrderService;
import com.muma.util.IPUtil;
import com.muma.util.KeyType;
import com.muma.util.Precondition;
import com.muma.util.StringReplaceUtil;
import com.muma.util.TimeUtils;
import com.muma.util.UploadImageUtil;
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
	private static final int SIZE = 10;

	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskBuyerRuleDao taskBuyerRuleDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
    private OrderService orderService;
	@Autowired
	private StatisticsDao statisticsDao;
	@Autowired
	private TaskOrderDao taskOrderDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private TaskConditionsDao taskConditionsDao;
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
            	platformIds.add(buyer.getPlatformId().getValue());
			}
		}
		if(platformIds == null && platformIds.size() == 0){
			throw new BizException("用户平台为认证通过，请根据提示修改！");
		}
		//TODO 查询是否有评价任务
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
		if(totalTask == null || totalTask == 0){
			if(buyerList.size() < 4){
				throw new BizException("已认证平台无匹配任务，请完成其他平台认证获取更多任务哦！");
			}else {
				throw new BizException("当前无匹配任务，请稍后再来！");
			}
		}
		StatisticsDto statisticsDto = getCanTaskOrder(taskBuyerRuleDto,totalTask,userInfoDto.getPhone());
		Precondition.checkNotNull(statisticsDto,"当前无匹配任务，请稍后再来！");
		//查询任务信息
		TaskOrder taskOrder = taskOrderDao.queryByTaskOrderId(statisticsDto.getTaskOrderId());
		Shop shop = shopDao.queryById(taskOrder.getShopId());
		TaskConditions taskConditions = taskConditionsDao.queryByTaskOrderId(statisticsDto.getTaskOrderId());
		//生成订单
		Order order = buildOrder(taskOrder,statisticsDto,shop,userInfoDto.getRegPhone());
		orderDao.addOrder(order);
		//返回 ConsumeTaskOrderDto
		ConsumeTaskOrderDto consumeTaskOrderDto = buildConsumeTaskOrderDto(taskOrder,statisticsDto,shop,taskConditions,userInfoDto.getRegPhone(),OrderStatusEnum.INIT);
		return consumeTaskOrderDto;
	}
	/**
	 * 查询接单历史列表 查60天内任务
	 * @return
	 */
	@Override
	public  PageBean<Order> queryOrderHistoryList(String pageIndex, String playerPhone, String status){
		Precondition.checkState(StringUtils.isNotBlank(pageIndex), "pageIndex is null!");
		List<Integer> statusList = null;
		if(StringUtils.isEmpty(status)){
			statusList = Lists.newArrayList(OrderStatusEnum.CANCEL.getValue(),
					OrderStatusEnum.BUYER_COMPLAIN_SURE.getValue(), OrderStatusEnum.BUSINESS_PASS.getValue());
		}else {
			statusList = Lists.newArrayList(Integer.valueOf(status));
		}
		Integer count = orderDao.count(playerPhone,statusList);
		PageBean pg = new PageBean(Integer.valueOf(pageIndex), KeyType.PAGE_NUMBER,count);
		int startIndex = pg.getStartIndex();
		List<Order> list =  orderDao.queryOrderHistoryList(playerPhone,statusList,startIndex, KeyType.PAGE_NUMBER);
		pg.setList(list);
		return pg;
	}


	/**
	 * 组装任务查询参数
	 * @param userInfoDto
	 * @return
	 */
	private TaskBuyerRuleDto buildTaskBuyerRule(UserInfoDto userInfoDto,List<Integer> platformIds, String price, String taskType ){
		Precondition.checkState(StringUtils.isNotBlank(taskType), "请选择任务类型!");
		List<Integer> taskTypes = null;
		if("0".equals(taskType)){//销售任务
			 taskTypes = Lists.newArrayList(TaskTypeEnum.SELLE_TASK.getValue(),
					TaskTypeEnum.LOOK_AND_SHOP.getValue(),TaskTypeEnum.REPEAT_SHOP.getValue());
		}else if("1".equals(taskType)){//浏览
			taskTypes = Lists.newArrayList(TaskTypeEnum.LOOK_TASK.getValue());
		}else {
           throw new BizException("平台暂不支持该任务类型!");
		}
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
        taskBuyerRuleDto.setTaskTypes(taskTypes);
        taskBuyerRuleDto.setIndex(INDEX);
        taskBuyerRuleDto.setSize(SIZE);
		return taskBuyerRuleDto;
	}

	/**
	 * 查询多个（10）任务
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
            if(order != null){
				String currentTime = TimeUtils.getStringDate();
				String createTime = TimeUtils.dateToStrLong(order.getCreateTime());
				String days = TimeUtils.getTwoDay(currentTime,createTime);
				if(Integer.valueOf(days)>= repeatDay){
					realTaskBuyerRules.add(t);
				}
			}else { //未接过该店铺的单子
				realTaskBuyerRules.add(t);
			}
		}
		if(realTaskBuyerRules.size() < SIZE){
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

	/**
	 * 获取一个任务
	 * @param taskBuyerRuleDto
	 * @param totalTask
	 * @param buyerPhone
	 * @return
	 */
	private StatisticsDto getCanTaskOrder(TaskBuyerRuleDto taskBuyerRuleDto,Integer totalTask,String buyerPhone){
		List<TaskBuyerRule> taskBuyerRules = getRealTaskBuyerRules(taskBuyerRuleDto,totalTask,buyerPhone);
		if(taskBuyerRules != null && taskBuyerRules.size() > 0){
			for (int i = 0; i <taskBuyerRules.size() ; i++) {
				//查询任务
				StatisticsDto statisticsDto = getTaskOrder(taskBuyerRules.get(i));
				if(statisticsDto != null){
					statisticsDto.setBuyerRemark(taskBuyerRules.get(i).getRemark());
					return  statisticsDto;
				}
			}
			getCanTaskOrder(taskBuyerRuleDto,totalTask,buyerPhone);
		}
		return null;
	}

	private synchronized StatisticsDto getTaskOrder(TaskBuyerRule taskBuyerRule){
        //判断当前任务是否还有可以接手的任务 for update
		Integer notFinishNumber = orderDao.queryByTaskOrderId(taskBuyerRule.getTaskOrderId());
		StatisticsDto statisticsDto = statisticsDao.queryTaskOrderId(taskBuyerRule.getTaskOrderId());
		Precondition.checkNotNull(statisticsDto, "查询统计任务异常!");
		//已接手任务 = 完成任务+进行中任务；
		Integer totalNumber = statisticsDto.getTaskOverNumber()+notFinishNumber;
		if(statisticsDto.getTaskNumber() > totalNumber){
			return  statisticsDto;
		}else {
			return null;
		}
    }

	/**
	 * 创建Order实体类
	 * @return
	 */
	private Order buildOrder(TaskOrder taskOrder,StatisticsDto statisticsDto,Shop shop,String regPhone){
		Order order = new Order();
		order.setTaskOrderId(taskOrder.getId());
		order.setType(taskOrder.getType());
		order.setPlayerPhone(regPhone);
		order.setBusinessPhone(taskOrder.getBusinessPhone());
		order.setShop(shop.getShopName());
		order.setShopId(shop.getId());
        order.setMoney(statisticsDto.getPrice());
        order.setFee(statisticsDto.getBuyerFree());
        order.setStatus(OrderStatusEnum.INIT);
        order.setCreateBy(regPhone);
		return order;
	}

	/**
	 * 创建ConsumeTaskOrderDto
	 * @return
	 */
	private ConsumeTaskOrderDto buildConsumeTaskOrderDto(TaskOrder taskOrder,StatisticsDto statisticsDto,Shop shop,TaskConditions taskConditions,String regPhone,OrderStatusEnum orderStatusEnum){
		ConsumeTaskOrderDto consumeTaskOrderDto = new ConsumeTaskOrderDto();
		consumeTaskOrderDto.setShopName(StringReplaceUtil.userNameReplaceWithStar(shop.getShopName()));
		consumeTaskOrderDto.setCommodity(taskOrder.getCommodity());
		consumeTaskOrderDto.setTaskRule(taskOrder.getTaskRule());
		consumeTaskOrderDto.setTaskTypeEnum(taskOrder.getType());
		consumeTaskOrderDto.setKeyword(taskOrder.getKeyword());
		consumeTaskOrderDto.setEntranceEnum(taskOrder.getEntranceId());
		consumeTaskOrderDto.setTaskRemark(taskOrder.getRemark());
		consumeTaskOrderDto.setPlatformEnum(shop.getShopType());
		consumeTaskOrderDto.setPrice(statisticsDto.getPrice());
		consumeTaskOrderDto.setOrderStatusEnum(orderStatusEnum);
		consumeTaskOrderDto.setBuyerRemark(statisticsDto.getBuyerRemark());
		consumeTaskOrderDto.setSortFlag(taskConditions.getSortFlag());
		consumeTaskOrderDto.setPriceRange(taskConditions.getPriceRange());
		consumeTaskOrderDto.setPlace(taskConditions.getPlace());
		consumeTaskOrderDto.setConditionsRemark(taskConditions.getRemark());
		consumeTaskOrderDto.setBase64image(UploadImageUtil.base64image(taskOrder.getMainImage()));
		return consumeTaskOrderDto;
	}


}
