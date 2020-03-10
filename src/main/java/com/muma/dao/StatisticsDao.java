package com.muma.dao;

import com.muma.entity.Shop;
import com.muma.entity.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售浏览任务统计
 */
public interface StatisticsDao {
    /**
     * 添加一个统计任务
     * @param
     */
    void addStatistics(Statistics statistics);

}
