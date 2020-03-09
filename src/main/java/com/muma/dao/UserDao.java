package com.muma.dao;

import com.muma.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 添加一个用户
     */
    void addUser(User user);

    /**
     * 更改用户信息
     * @param user
     */
    void updateUser(User user);

	/**
     * 根据手机号查询用户对象
     *
     * @param regPhone
     * @return
     */
    Integer queryByPhone(@Param("regPhone") String regPhone);
    /**
     * 根据手机号和密码查询用户对象
     *
     * @param regPhone
     * @return
     */
    User queryByPhoneAndPwd(@Param("regPhone") String regPhone,@Param("password") String password);

    /**
     * 根据IP地址查询用户
     * @param publicIp
     * @return
     */
    User queryByIp(@Param("publicIp") String publicIp);

}
