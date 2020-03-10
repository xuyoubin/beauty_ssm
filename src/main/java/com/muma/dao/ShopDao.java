package com.muma.dao;

import com.muma.entity.Shop;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ShopDao {
    /**
     * 添加一个店铺
     * @param
     */
    void addShop(Shop shop);
    /**
     * 查询所有店铺列表数量
     * @return
     */
    Integer count(@Param("regPhone") String regPhone);
    /**
     * 查询所有店铺列表
     * @return
     */
    List<Shop> queryShopList(@Param("regPhone") String regPhone,@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);




    
}
