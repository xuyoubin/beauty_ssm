package com.muma.service.impl;

import com.muma.common.PageBean;
import com.muma.entity.Shop;
import com.muma.service.ProduceTaskOrderService;
import com.muma.util.KeyType;
import com.muma.util.Precondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public void addTaskOrder() {
//        Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
//        Precondition.checkState(StringUtils.isNotBlank(shopName), "请填写店铺名称!");
//        Precondition.checkState(StringUtils.isNotBlank(shopUrl), "请填写店铺URL!");
//        Precondition.checkState(StringUtils.isNotBlank(shopType), "请填写店铺类型!");
//        Precondition.checkState(StringUtils.isNotBlank(repeatDay), "请填写店铺复购日期间隔!");
//        PlatformEnum platformEnum = PlatformEnum.stateOf(Integer.valueOf(shopType));
//        Precondition.checkNotNull(platformEnum, "暂不支持该平台!");


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
}
