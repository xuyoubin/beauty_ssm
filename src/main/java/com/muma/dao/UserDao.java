package com.muma.dao;

import java.util.List;

import com.muma.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

	/**
     * 根据手机号查询用户对象
     *
     * @param userPhone
     * @return
     */
    User queryByPhone(@Param("userPhone") String userPhone);
    /**
     * 根据手机号和密码查询用户对象
     *
     * @param userPhone
     * @return
     */
    User queryByPhoneAndPwd(@Param("userPhone")String userPhone,@Param("pwd") String pwd);
    
    
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
    void addScore(@Param("add")int add);
	
}
