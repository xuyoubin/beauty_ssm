package com.muma.dao;

import com.muma.entity.User;
import com.muma.service.base.BaseService;
public interface UserDao extends BaseService<User> {

    /**
     * 根据手机号查询用户对象
     *
     * @param regPhone
     * @return
     */
    User queryByPhone(String regPhone);
    /**
     * 根据手机号和密码查询用户对象
     *
     * @param regPhone
     * @return
     */
    User queryByPhoneAndPwd( String regPhone, String password);

	
}
