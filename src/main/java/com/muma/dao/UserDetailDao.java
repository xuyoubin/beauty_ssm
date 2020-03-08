package com.muma.dao;

import com.muma.dto.ShareUserDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.UserDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDetailDao {

    /**
     * 添加一条详细信息
     */
    void addUserDetail(UserDetail userDetail);
    /**
     * 根据主键更新信息
     */
     void updateUserDetail(UserDetail userDetail);
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
    UserDetail queryByIdAndCode(@Param("id") Integer id, @Param("code") String code);
    /**
     * 查询邀请次数
     *
     * @param
     * @return
     */
    List<ShareUserDto> queryByParentPhone(@Param("parentPhone") String parentPhone);

    /**
     * 通过身份号码查询是否已经注册
     * @param
     * @return
     */
    UserDetail queryByCardId(@Param("idNumber") String idNumber);
}
