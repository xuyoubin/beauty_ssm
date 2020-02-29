package com.muma.dao;

import com.muma.entity.UserDetail;
import org.apache.ibatis.annotations.Param;


public interface UserDetailDao {

    /**
     * 添加一条详细信息
     */
    void addUserDetail(@Param("userId") int userId,
                       @Param("parentId") int parentId,
                       @Param("code") String code,
                       @Param("createBy") String createBy);

    /**
     * 根据用户ID和邀请码查看上级用户信息的有效效性
     *
     * @param
     * @return
     */
    UserDetail queryByParentIdAndCode(@Param("parentId") Integer parentId, @Param("code") Integer code);
}
