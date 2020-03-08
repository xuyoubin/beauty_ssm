package com.muma.dao;

import com.muma.entity.Buyer;
import com.muma.entity.Goods;
import com.muma.enums.PlatformEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuyerDao {

    void addBuyer(Buyer buyer);
	 /**
     * 根据用户手机号
     *
     * @return
     */
    List<Buyer> queryBuyerListByRegPhone(@Param("regPhone") String regPhone);

    /**
     * 根据手机号和平台id查看是否注册过此平台
     * @param regPhone
     * @param platformId
     * @return
     */
    Buyer queryByRegPhoneAndPlatformId(@Param("regPhone") String regPhone, @Param("platformId") PlatformEnum platformEnum);


    
}
