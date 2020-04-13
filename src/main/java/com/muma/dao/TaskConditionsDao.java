package com.muma.dao;

import com.muma.entity.TaskConditions;
import org.apache.ibatis.annotations.Param;

public interface TaskConditionsDao {

    void addTaskConditions(TaskConditions taskConditions);
	 /**
     * 根据任务ID查询卡位条件
     *
     * @return
     */
    TaskConditions queryByTaskOrderId(@Param("taskOrderId") Integer taskOrderId);


}
