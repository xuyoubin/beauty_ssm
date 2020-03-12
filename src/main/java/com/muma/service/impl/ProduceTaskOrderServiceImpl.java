package com.muma.service.impl;

import com.muma.common.PageBean;
import com.muma.dto.ProduceTaskOrderDto;
import com.muma.entity.Shop;
import com.muma.entity.Statistics;
import com.muma.entity.TaskOrder;
import com.muma.enums.EntranceEnum;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;
import com.muma.enums.YesAndNoEnum;
import com.muma.service.ProduceTaskOrderService;
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
 * 平台账号处理类
 */
@Service
public class ProduceTaskOrderServiceImpl implements ProduceTaskOrderService {

    /**
     * 添加店铺
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTaskOrder(String regPhone, ProduceTaskOrderDto produceTaskOrderDto, MultipartFile mainImage) {
        Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
        Precondition.checkState(StringUtils.isNotBlank(mainImage.getOriginalFilename()), "请上传宝贝主图!");
        //保存图片
        String imageUrl = UploadImageUtil.saveImage(mainImage,UploadImageUtil.IMAGE_TYPE_TASK,regPhone,"宝贝主图");
        TaskOrder taskOrder = buildTaskOrder(regPhone,produceTaskOrderDto,imageUrl);

//
//            statistics.setTaskNumber(Integer.valueOf(taskNumber));
//            statistics.setPrice(new BigDecimal(price));
//
//            buyerRule.setAge(age);
//            buyerRule.setCredit(Integer.valueOf(credit));
//            buyerRule.setProvince(province);
//            buyerRule.setTags(tags);
//            buyerRule.setRemark(buyerRemark);
//            buyerRule.setSex(SexEnum.stateOf(Integer.valueOf(sex)));


    }
    /**
     * 查询所有店铺，查询商家所有店铺
     * @return
     */
    @Override
    public PageBean<Shop> queryTaskOrder(String pageIndex,String regPhone){
        Precondition.checkState(StringUtils.isNotBlank(pageIndex), "pageIndex is null!");
//        Integer count = businessDao.count(regPhone);
        PageBean pg = new PageBean(Integer.valueOf(pageIndex), KeyType.PAGE_NUMBER,0);
//        int startIndex = pg.getStartIndex();
        List<Shop> list =  null;
        pg.setList(list);
        return pg;
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
    private Statistics buildStatistics(String regPhone,ProduceTaskOrderDto produceTaskOrderDto,Integer taskOrderId){
        Precondition.checkNotNull(produceTaskOrderDto.getTaskNumber(), "请填写发布任务数量!");
        Precondition.checkNotNull(produceTaskOrderDto.getPrice(), "请填写单价!");
        Statistics statistics = new Statistics();
        statistics.setBusinessPhone(regPhone);
        statistics.setTaskOrderId(taskOrderId);
        statistics.setType(TaskTypeEnum.stateOf(produceTaskOrderDto.getType()));
        statistics.

    }

    /**
     * 计算任务本金，佣金值
     * @param taskNumber
     * @param price
     * @return
     */
    private Statistics countMoney(Integer taskNumber, BigDecimal price){
        Statistics statistics = new Statistics();
        BigDecimal taskCountCapital = price.multiply(new BigDecimal(taskNumber)).setScale(2,BigDecimal.ROUND_HALF_UP);
        //获取佣金

        return statistics;
    }


}
