package com.muma.dao;

import com.muma.entity.Buyer;
import com.muma.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuyerDao {
	 /**
     * 根据用户ID查询用户注册的平台信息
     *
     * @return
     */
    List<Buyer> queryBuyerListByUserId(Integer userId);


    
}
