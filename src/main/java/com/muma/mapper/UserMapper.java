package com.muma.mapper;

import com.muma.entity.User;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends Mapper<User> {

	/**
     * 根据手机号查询用户对象
     *
     * @param regPhone
     * @return
     */
    User queryByPhone(@Param("regPhone") String regPhone);
    /**
     * 根据手机号和密码查询用户对象
     *
     * @param regPhone
     * @return
     */
    User queryByPhoneAndPwd(@Param("regPhone") String regPhone, @Param("password") String password);


    /**
     * 根据偏移量查询用户列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 增加积分
     */
    void addScore(@Param("add") int add);
	
}