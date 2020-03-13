package com.muma.dao;

import com.muma.entity.Capital;
import org.apache.ibatis.annotations.Param;

public interface CapitalDao {
    /**
     * 添加一条资金记录
     * @param capital
     */
    void addCapital(Capital capital);

    /**
     * 根据手机号查询资金信息
     * @param regPhone
     * @return
     */
    Capital queryByRegPhone(@Param("regPhone") String regPhone);


}
