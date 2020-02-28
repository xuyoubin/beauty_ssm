package com.muma.dao.impl;

import com.muma.dao.UserDao;
import com.muma.entity.User;
import com.muma.mapper.UserMapper;
import com.muma.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl extends BaseServiceImpl<User> implements UserDao {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据手机号查询用户对象
     *
     * @param regPhone
     * @return
     */
    @Override
    public User queryByPhone(String regPhone) {
        return userMapper.queryByPhone(regPhone);
    }
    /**
     * 根据手机号和密码查询用户对象
     *
     * @param regPhone
     * @return
     */
    @Override
    public User queryByPhoneAndPwd( String regPhone, String password){
        return userMapper.queryByPhoneAndPwd(regPhone,password);
    }
}
