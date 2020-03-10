package com.muma.service.impl;

import com.muma.common.PageBean;
import com.muma.dao.ShopDao;
import com.muma.entity.Shop;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
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
    private ShopDao shopDao;
    /**
     * 添加店铺
     * @return
     */
    @Override
    public void addShop(String regPhone,String uniqueId,String shopName,String shopUrl,String shopType,String shopWw,String repeatDay) {
        Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
        Precondition.checkState(StringUtils.isNotBlank(shopName), "请填写店铺名称!");
        Precondition.checkState(StringUtils.isNotBlank(shopUrl), "请填写店铺URL!");
        Precondition.checkState(StringUtils.isNotBlank(shopType), "请填写店铺类型!");
        Precondition.checkState(StringUtils.isNotBlank(repeatDay), "请填写店铺复购日期间隔!");
        PlatformEnum platformEnum = PlatformEnum.stateOf(Integer.valueOf(shopType));
        Precondition.checkNotNull(platformEnum, "暂不支持该平台!");
        Shop shop = new Shop();
        shop.setRegPhone(regPhone);
        shop.setUniqueId(uniqueId);
        shop.setShopName(shopName);
        shop.setShopUrl(shopUrl);
        shop.setShopType(platformEnum);
        shop.setShopWw(shopWw);
        shop.setRepeatDay(Integer.valueOf(repeatDay));
        shop.setCreateBy(regPhone);
        shop.setStatus(StatusEnum.CONFIRM_WAIT);
        shopDao.addShop(shop);
    }
    /**
     * 查询所有店铺，查询商家所有店铺
     * @return
     */
    @Override
    public PageBean<Shop> queryShopList(String pageIndex,String regPhone){
        Precondition.checkState(StringUtils.isNotBlank(pageIndex), "pageIndex is null!");
        Integer count = shopDao.count(regPhone);
        PageBean pg = new PageBean(Integer.valueOf(pageIndex), KeyType.PAGE_NUMBER,count);
        int startIndex = pg.getStartIndex();
        List<Shop> list =  shopDao.queryShopList(regPhone,startIndex,KeyType.PAGE_NUMBER);
        pg.setList(list);
        return pg;
    }
}
