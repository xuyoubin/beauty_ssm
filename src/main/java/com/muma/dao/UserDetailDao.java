package com.muma.dao;

import com.muma.dto.UserInfoDto;
import com.muma.entity.UserDetail;
import org.apache.ibatis.annotations.Param;


public interface UserDetailDao {

    /**
     * 添加一条详细信息
     */
    void addUserDetail(UserDetail userDetail);

    /**
     * 根据用户手机号查询用户详细信息
     *
     * @param
     * @return
     */
    UserInfoDto queryByRegPhone(@Param("regPhone") String regPhone);

    /**
     * 根据用户ID和邀请码查看上级用户信息的有效效性
     *
     * @param
     * @return
     */
    UserDetail queryByPhoneAndCode(@Param("regPhone") String regPhone, @Param("code") String code);
}
