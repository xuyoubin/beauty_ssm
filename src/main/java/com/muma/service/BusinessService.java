package com.muma.service;


import com.muma.common.PageBean;
import com.muma.entity.Shop;
import java.util.List;

public interface BusinessService {
	/**
	 * 添加店铺
	 * @return
	 */
	void addShop(String regPhone,String uniqueId,String shopName,String shopUrl,String shopType,String shopWw,String repeatDay);
	/**
	 * 查询所有店铺 ，询商家所有店铺
	 * @return
	 */
	PageBean<Shop> queryShopList(String pageIndex,String regPhone);
}
