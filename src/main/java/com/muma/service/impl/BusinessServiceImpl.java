package com.muma.service.impl;

import com.muma.common.PageBean;
import com.muma.dao.BusinessDao;
import com.muma.entity.Shop;
import com.muma.enums.PlatformEnum;
import com.muma.service.BusinessService;
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
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;
    /**
     * 添加店铺
     * @return
     */
    @Override
    public void addShop(String regPhone,String shopId,String shopName,String shopUrl,String shopType,String shopWw,String repeatDay) {
        Precondition.checkState(StringUtils.isNotBlank(shopId), "请填写会员名称!");
//        PlatformEnum platformEnum = PlatformEnum.stateOf(Integer.valueOf(platformId));
//        Precondition.checkNotNull(platformEnum, "暂不支持该平台!");

    }
    /**
     * 查询商家所有店铺
     * @return
     */
    @Override
    public List<Shop> queryShopListByRegPhone(String regPhone) {
        return businessDao.queryByRegPhone(regPhone);
    }
    /**
     * 查询所有店铺
     * @return
     */
    @Override
    public PageBean<Shop> queryShopList(String pageIndex){
        Integer count = businessDao.count();
        PageBean pg = new PageBean(Integer.valueOf(pageIndex), KeyType.PAGE_NUMBER,count);
        int startIndex = pg.getStartIndex();
        List<Shop> list =  businessDao.queryShopList(Integer.valueOf(pageIndex),KeyType.PAGE_NUMBER);
        pg.setList(list);
        return pg;
    }
}
