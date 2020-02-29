package com.muma.dao;

import java.util.List;

import com.muma.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 添加一个用户
     */
    void addUser(@Param("regPhone")int regPhone,
                 @Param("password")int password,
                 @Param("roalId")int roalId,
                 @Param("remark")int remark,
                 @Param("createBy")int createBy);

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
    User queryByPhoneAndPwd(@Param("regPhone")String regPhone,@Param("password") String password);

}
