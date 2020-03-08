package com.muma.service;


import org.springframework.web.multipart.MultipartFile;

public interface BuyerService {
	/**
	 * 添加平台账号
	 * @return
	 */
	void addBuyer(String regPhone,String platformId,String nickName,MultipartFile indexImage,MultipartFile infoImage, MultipartFile commentImage);

}
