package com.muma.dao;

import com.muma.entity.TaskConditions;
import org.apache.ibatis.annotations.Param;

public interface TaskConditionsDao {

    void addTaskConditions(TaskConditions taskConditions);
	 /**
     * 根据任务ID
     *
     * @return
     */
    TaskConditions queryByTaskOrderId(@Param("taskOrderId") String taskOrderId);


}
