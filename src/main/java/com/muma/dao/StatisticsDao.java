package com.muma.dao;

import com.muma.dto.StatisticsDto;
import com.muma.entity.Statistics;
import org.apache.ibatis.annotations.Param;

/**
 * 销售浏览任务统计
 */
public interface StatisticsDao {
    /**
     * 添加一个统计任务
     * @param
     */
    void addStatistics(Statistics statistics);

    /**
     * 根据任务ID查询
     * @param taskOrderId
     * @return
     */
    StatisticsDto queryTaskOrderId(@Param("taskOrderId") Integer taskOrderId);

}
