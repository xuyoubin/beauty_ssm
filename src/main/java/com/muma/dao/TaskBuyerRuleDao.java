package com.muma.dao;

import com.muma.entity.TaskBuyerRule;
import org.apache.ibatis.annotations.Param;

public interface TaskBuyerRuleDao {

    void addTaskBuyerRule(TaskBuyerRule taskBuyerRule);
	 /**
     * 根据任务ID
     *
     * @return
     */
    TaskBuyerRule queryByTaskOrderId(@Param("taskOrderId") String taskOrderId);


}
