package com.muma.dao;

import com.muma.entity.CapitalFlow;
import com.muma.enums.StatusEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CapitalFlowDao {
    /**
     * 添加一条资金流水
     * @param capitalFlow
     */
    void addCapitalFlow(CapitalFlow capitalFlow);

    /**
     * 根据主键更新信息
     */
    void updateStatus(@Param("id") Integer id, @Param("status") StatusEnum statusEnum,@Param("updateBy") String updateBy);
    /**
     * 根据手机号查询资金流水
     * @param regPhone
     * @return
     */
    List<CapitalFlow> queryByRegPhone(@Param("regPhone") String regPhone,@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);


}
