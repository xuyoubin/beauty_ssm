package com.muma.dao;

import com.muma.entity.Buyer;
import com.muma.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuyerDao {
	 /**
     * 根据用户手机号
     *
     * @return
     */
    List<Buyer> queryBuyerListByRegPhone(@Param("regPhone") String regPhone);


    
}
