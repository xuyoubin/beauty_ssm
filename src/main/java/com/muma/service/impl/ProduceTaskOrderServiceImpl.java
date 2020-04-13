package com.muma.service.impl;

import com.muma.common.PageBean;
import com.muma.dao.CapitalDao;
import com.muma.dao.CapitalFlowDao;
import com.muma.dao.ShopDao;
import com.muma.dao.StatisticsDao;
import com.muma.dao.TaskBuyerRuleDao;
import com.muma.dao.TaskConditionsDao;
import com.muma.dao.TaskFreeDao;
import com.muma.dao.TaskOrderDao;
import com.muma.dto.ProduceTaskOrderDto;
import com.muma.entity.Capital;
import com.muma.entity.CapitalFlow;
import com.muma.entity.Shop;
import com.muma.entity.Statistics;
import com.muma.entity.TaskBuyerRule;
import com.muma.entity.TaskConditions;
import com.muma.entity.TaskFree;
import com.muma.entity.TaskOrder;
import com.muma.enums.BuyerAgeEnum;
import com.muma.enums.EntranceEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.PayTypeEnum;
import com.muma.enums.PlatformEnum;
import com.muma.enums.SexEnum;
import com.muma.enums.SortEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;
import com.muma.enums.YesAndNoEnum;
import com.muma.service.ProduceTaskOrderService;
import com.muma.util.IdcardUtils;
import com.muma.util.KeyType;
import com.muma.util.Precondition;
import com.muma.util.TimeUtils;
import com.muma.util.UploadImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**\
 * 发布任务处理类
 */
@Service
public class ProduceTaskOrderServiceImpl implements ProduceTaskOrderService {

    @Autowired
    private TaskFreeDao taskFreeDao;
    @Autowired
    private TaskOrderDao taskOrderDao;
    @Autowired
    private CapitalDao capitalDao;
    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private TaskBuyerRuleDao taskBuyerRuleDao;
    @Autowired
    private TaskConditionsDao taskConditionsDao;
    @Autowired
    private CapitalFlowDao capitalFlowDao;
    @Autowired
    private ShopDao shopDao;


    /**
     * 添加任务
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTaskOrder(String regPhone, ProduceTaskOrderDto produceTaskOrderDto, MultipartFile mainImage) {
        Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
        Precondition.checkState(StringUtils.isNotBlank(mainImage.getOriginalFilename()), "请上传宝贝主图!");
        Precondition.checkNotNull(produceTaskOrderDto.getShopId(), "请选择店铺!");
        //查询店铺信息
        Shop shop = shopDao.queryById(produceTaskOrderDto.getShopId());
        Precondition.checkNotNull(shop,"店铺信息不存在！");
        //查询可用余额是否充足
        Statistics statistics = buildStatistics(regPhone,produceTaskOrderDto,shop);
        //需要冻结金额 = 本金+商家佣金+原来已经冻结的金额
        Capital capital = capitalDao.queryByRegPhone(regPhone);
        BigDecimal freeze = statistics.getTaskCountCapital().add(statistics.getTaskCountFee()).add(capital.getFreeze()).setScale(2,BigDecimal.ROUND_HALF_UP);
        Precondition.checkState(capital.getBalance().compareTo(freeze) > -1, "余额不足，请充值!");
        //保存图片
        String imageUrl = UploadImageUtil.saveImage(mainImage,UploadImageUtil.IMAGE_TYPE_TASK,regPhone,"宝贝主图");
        TaskOrder taskOrder = buildTaskOrder(regPhone,produceTaskOrderDto,imageUrl);
        TaskBuyerRule buyerRule = buildBuyerRule(regPhone,produceTaskOrderDto,shop);
        TaskConditions conditions = buildTaskConditions(regPhone,produceTaskOrderDto);
        taskOrderDao.addTaskOrder(taskOrder);
        Precondition.checkNotNull(taskOrder.getId(), "taskOrderId is null!");
        statistics.setTaskOrderId(taskOrder.getId());
        buyerRule.setTaskOrderId(taskOrder.getId());
        conditions.setTaskOrderId(taskOrder.getId());
        statisticsDao.addStatistics(statistics);
        taskBuyerRuleDao.addTaskBuyerRule(buyerRule);
        taskConditionsDao.addTaskConditions(conditions);
        //更新余额表
        capital.setFreeze(freeze);
        capital.setUpdateBy(regPhone);
        capitalDao.updateCapital(capital);
        //插入资金流水表，冻结都是立即生效
        CapitalFlow capitalFlow1 = buildCapitalFlow(regPhone,taskOrder.getId(),statistics.getTaskCountCapital(),"发布任务本金冻结！");
        CapitalFlow capitalFlow2 = buildCapitalFlow(regPhone,taskOrder.getId(),statistics.getTaskCountFee(),"发布任务佣金冻结！");
        capitalFlowDao.addCapitalFlow(capitalFlow1);
        capitalFlowDao.addCapitalFlow(capitalFlow2);
    }
    /**
     * 查询所有店铺，查询商家所有店铺
     * @return
     */
    @Override
    public PageBean<Shop> queryTaskOrder(String pageIndex, String regPhone,String shopName,String operateStatus,String status,String taskType){
        Precondition.checkState(StringUtils.isNotBlank(pageIndex), "pageIndex is null!");
        OperateStatusEnum operateStatusEnum = null;
        TaskTypeEnum taskTypeEnum = null;
        StatusEnum statusEnum = null;
        Integer shopId = null;
        if(StringUtils.isNotEmpty(operateStatus)){
             operateStatusEnum = OperateStatusEnum.stateOf(Integer.valueOf(operateStatus));
        }
        if(StringUtils.isNotEmpty(taskType)){
            taskTypeEnum = taskTypeEnum.stateOf(Integer.valueOf(taskType));
        }
        if(StringUtils.isNotEmpty(status)){
            statusEnum = StatusEnum.stateOf(Integer.valueOf(status));
        }
        if(StringUtils.isNotEmpty(shopName)){
            Shop shop = shopDao.queryByName(shopName);
            Precondition.checkNotNull(shop,"店铺信息不存在！");
            shopId  = shop.getId();
        }
        Integer count = taskOrderDao.count(regPhone,shopId,taskTypeEnum,operateStatusEnum,statusEnum);
        PageBean pg = new PageBean(Integer.valueOf(pageIndex), KeyType.PAGE_NUMBER,count);
        int startIndex = pg.getStartIndex();
        List<ProduceTaskOrderDto> list =  taskOrderDao.queryTaskOrderList(regPhone,shopId,taskTypeEnum,operateStatusEnum,statusEnum,startIndex,KeyType.PAGE_NUMBER);
        pg.setList(list);
        return pg;
    }

    /**
     * 计算费用
     * @param taskNumber
     * @param price
     * @return
     */
    @Override
    public Statistics countMoney(String taskNumber,String price,String platform,String taskType ) {
        Precondition.checkNotNull(taskNumber, "请填写任务数量!");
        Precondition.checkNotNull(price, "请填写单价!");
        Precondition.checkNotNull(platform, "请选择平台类型!");
        Precondition.checkNotNull(taskType, "请选择任务类型!");
        PlatformEnum platformEnum = PlatformEnum.stateOf(Integer.valueOf(platform));
        Precondition.checkNotNull(platformEnum, "暂不支持该平台类型!");
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(Integer.valueOf(taskType));
        Precondition.checkNotNull(taskTypeEnum, "暂不支持该任务类型!");
        Statistics statistics = countMoney(Integer.valueOf(taskNumber),new BigDecimal(price),platformEnum,taskTypeEnum);
        statistics.setBuyerFree(null); //屏蔽买家佣金
        return statistics;
    }

    /**
     * 构建TaskOrder 实体类
     * @param regPhone
     * @param produceTaskOrderDto
     * @param imageUrl
     * @return
     */
    private TaskOrder buildTaskOrder(String regPhone,ProduceTaskOrderDto produceTaskOrderDto,String imageUrl){
        Precondition.checkNotNull(produceTaskOrderDto.getShopId(), "请选择店铺!");
        Precondition.checkState(StringUtils.isNotBlank(produceTaskOrderDto.getCommodity()), "请填写商品名称!");
        Precondition.checkState(StringUtils.isNotBlank(produceTaskOrderDto.getCommodityUrl()), "请填写商品URL!");
        Precondition.checkNotNull(produceTaskOrderDto.getType(), "请选择任务类型!");
        Precondition.checkState(StringUtils.isNotBlank(produceTaskOrderDto.getStartTime()), "请填写任务开始时间!");
        Precondition.checkNotNull(produceTaskOrderDto.getEntranceId(), "请选择任务入口类型!");
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(produceTaskOrderDto.getType());
        Precondition.checkNotNull(taskTypeEnum, "暂不支持该任务类型!");
        EntranceEnum entranceEnum = EntranceEnum.stateOf(produceTaskOrderDto.getEntranceId());
        Precondition.checkNotNull(entranceEnum, "暂不支持该入口类型!");
        TaskOrder taskOrder = new TaskOrder();
        taskOrder.setBusinessPhone(regPhone);
        taskOrder.setShopId(produceTaskOrderDto.getShopId());
        taskOrder.setCommodity(produceTaskOrderDto.getCommodity());
        taskOrder.setCommodityId(produceTaskOrderDto.getCommodityId());
        taskOrder.setCommodityUrl(produceTaskOrderDto.getCommodityUrl());
        taskOrder.setMainImage(imageUrl);
        taskOrder.setTaskRule(produceTaskOrderDto.getTaskRule());
        taskOrder.setType(taskTypeEnum);
        taskOrder.setStatus(StatusEnum.CONFIRM_WAIT);
        taskOrder.setOperateStatus(OperateStatusEnum.TASK_NOT_START);
        taskOrder.setStartTime(TimeUtils.strToDateLong(produceTaskOrderDto.getStartTime()));
        taskOrder.setEntranceId(entranceEnum);
        taskOrder.setKeyword(produceTaskOrderDto.getKeyword());
        taskOrder.setTagFlag(YesAndNoEnum.stateOf(produceTaskOrderDto.getTagFlag()));
        taskOrder.setTbkFlag(YesAndNoEnum.stateOf(produceTaskOrderDto.getTbkFlag()));
        taskOrder.setDefineFlag(YesAndNoEnum.stateOf(produceTaskOrderDto.getDefineFlag()));
        taskOrder.setRemark(produceTaskOrderDto.getTaskRemark());
        taskOrder.setCreateBy(regPhone);
        return taskOrder;
    }

    /**
     * 构建statistics实体类
     * @param regPhone
     * @param produceTaskOrderDto
     * @return
     */
    private Statistics buildStatistics(String regPhone,ProduceTaskOrderDto produceTaskOrderDto,Shop shop){
        Precondition.checkNotNull(produceTaskOrderDto.getTaskNumber(), "请填写发布任务数量!");
        Precondition.checkNotNull(produceTaskOrderDto.getPrice(), "请填写单价!");
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(produceTaskOrderDto.getType());
        Statistics statistics = countMoney(produceTaskOrderDto.getTaskNumber(),produceTaskOrderDto.getPrice(),shop.getShopType(),taskTypeEnum);
        statistics.setBusinessPhone(regPhone);
        statistics.setCreateBy(regPhone);
        statistics.setTaskRealIncome(new BigDecimal("0.00"));
        statistics.setTaskOverCapital(new BigDecimal("0.00"));
        statistics.setTaskOverFee(new BigDecimal("0.00"));
        statistics.setTaskOverNumber(0);
        return statistics;
    }

    /**
     * 计算任务本金，佣金值
     * @param taskNumber
     * @param price
     * @return
     */
    private Statistics countMoney(Integer taskNumber, BigDecimal price ,PlatformEnum platformEnum,TaskTypeEnum taskTypeEnum ){
        Statistics statistics = new Statistics();
        BigDecimal businessFree = null;
        BigDecimal buyerFree = null;
        //计算总本金
        BigDecimal taskCountCapital = price.multiply(new BigDecimal(taskNumber)).setScale(2,BigDecimal.ROUND_HALF_UP);
        //获取佣金
        List<TaskFree> taskFreeList = taskFreeDao.queryTaskFreeListByType(platformEnum,taskTypeEnum);
        Precondition.checkNotNull(taskFreeList,"查询佣金费异常");
        for(int i=0;i<taskFreeList.size();i++){
            TaskFree taskFree = taskFreeList.get(i);
            BigDecimal priceMin = taskFree.getPriceMin();
            BigDecimal priceMax = taskFree.getPriceMax();
            if(price.compareTo(priceMin)> -1 && price.compareTo(priceMax) == -1){
                businessFree = taskFree.getBusinessFree();
                buyerFree = taskFree.getBuyerFree();
                break;
            }
        }
        Precondition.checkNotNull(businessFree,"商家佣金计算异常！");
        Precondition.checkNotNull(buyerFree,"买家佣金计算异常！");
        //计算卖家总佣金
        BigDecimal taskCountFee = businessFree.multiply(new BigDecimal(taskNumber)).setScale(2,BigDecimal.ROUND_HALF_UP);
        statistics.setTaskNumber(taskNumber);
        statistics.setPrice(price);
        statistics.setBusinessFree(businessFree);
        statistics.setBuyerFree(buyerFree);
        statistics.setTaskCountCapital(taskCountCapital);
        statistics.setTaskCountFee(taskCountFee);
        return statistics;
    }
    /**
     * 构建BuyerRule 实体类
     */
     private TaskBuyerRule buildBuyerRule(String regPhone,ProduceTaskOrderDto produceTaskOrderDto,Shop shop){
         Precondition.checkNotNull(produceTaskOrderDto.getPrice(), "请填写单价!");
         Precondition.checkNotNull(produceTaskOrderDto.getType(), "请选择任务类型!");
         TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(produceTaskOrderDto.getType());
         Precondition.checkNotNull(taskTypeEnum, "暂不支持该任务类型!");
         TaskBuyerRule buyerRule = new TaskBuyerRule();
         buyerRule.setAge(StringUtils.isBlank(produceTaskOrderDto.getAge()) ? BuyerAgeEnum.getValueStr() : produceTaskOrderDto.getAge());
         buyerRule.setSex(StringUtils.isBlank(produceTaskOrderDto.getSex()) ? SexEnum.getValueStr() : produceTaskOrderDto.getSex());
         buyerRule.setProvince(StringUtils.isBlank(produceTaskOrderDto.getProvince()) ? IdcardUtils.cityCodeStr : produceTaskOrderDto.getProvince());
         buyerRule.setCredit(produceTaskOrderDto.getCredit());
         buyerRule.setTags(produceTaskOrderDto.getTags());
         buyerRule.setRemark(produceTaskOrderDto.getBuyerRemark());
         buyerRule.setPrice(produceTaskOrderDto.getPrice());
         buyerRule.setOperateStatus(OperateStatusEnum.TASK_NOT_START);
         buyerRule.setStartTime(TimeUtils.strToDateLong(produceTaskOrderDto.getStartTime()));
         buyerRule.setPlatform(shop.getShopType());
         buyerRule.setRepeatDay(shop.getRepeatDay());
         buyerRule.setShopId(shop.getId());
         buyerRule.setTaskType(taskTypeEnum);
         buyerRule.setCreateBy(regPhone);
         return buyerRule;
     }

    /**
     * 构建TaskConditions 实体类
     * @param produceTaskOrderDto
     * @return
     */
     private TaskConditions buildTaskConditions(String regPhone,ProduceTaskOrderDto produceTaskOrderDto){
         TaskConditions conditions = new TaskConditions();
         if(produceTaskOrderDto.getSortFlag() != null ){
             conditions.setSortFlag(SortEnum.stateOf(produceTaskOrderDto.getSortFlag()));
         }
         conditions.setPriceRange(produceTaskOrderDto.getPriceRange());
         conditions.setPlace(produceTaskOrderDto.getPlace());
         conditions.setRemark(produceTaskOrderDto.getConditionsRemark());
         conditions.setCreateBy(regPhone);
         return conditions;
     }

    /**
     * 构建CapitalFlow实体
     * @param regPhone
     * @param orderId
     * @param payMoney
     * @param remark
     * @return
     */
     private CapitalFlow buildCapitalFlow(String regPhone,Integer orderId,BigDecimal payMoney,String remark){
         CapitalFlow capitalFlow = new CapitalFlow();
         capitalFlow.setRegPhone(regPhone);
         capitalFlow.setOrderId(orderId);
         capitalFlow.setType(PayTypeEnum.FREEZE_OUT);
         capitalFlow.setPayMoney(payMoney);
         capitalFlow.setStatus(StatusEnum.CONFIRM_PASS);
         capitalFlow.setCreateBy(regPhone);
         capitalFlow.setRemark(remark);
         return capitalFlow;
     }

}
