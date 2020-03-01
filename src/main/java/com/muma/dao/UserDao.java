package com.muma.dao;

import java.util.List;

import com.muma.dto.UserInfoDto;
import com.muma.entity.User;
import com.muma.enums.RoalEnum;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 添加一个用户
     */
    void addUser(User user);

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
    UserInfoDto queryByPhoneAndPwd(@Param("regPhone")String regPhone, @Param("password") String password);

}
