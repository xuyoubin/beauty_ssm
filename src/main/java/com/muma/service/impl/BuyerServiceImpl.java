package com.muma.service.impl;

import com.muma.dao.BuyerDao;
import com.muma.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**\
 * 平台账号处理类
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;
    /**
     * 添加平台账号
     * @return
     */
    @Override
    public void addBuyer() {

    }
}
