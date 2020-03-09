package com.muma.dao;

import com.muma.entity.Shop;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BusinessDao {
    /**
     * 添加一个店铺
     * @param
     */
    void addShop(Shop shop);
	 /**
     * 根据商家手机号查询店铺列表
     * @return
     */
    List<Shop> queryByRegPhone(@Param("regPhone") String regPhone);




    
}
