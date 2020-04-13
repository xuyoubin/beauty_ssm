package com.muma.dao;

import com.muma.dto.TaskBuyerRuleDto;
import com.muma.entity.TaskBuyerRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskBuyerRuleDao {

    void addTaskBuyerRule(TaskBuyerRule taskBuyerRule);
	 /**
     * 根据任务ID
     *
     * @return
     */
    TaskBuyerRule queryByTaskOrderId(@Param("taskOrderId") String taskOrderId);

    /**
     * 根据规则查询任务
     * @param taskBuyerRuleDto
     * @return
     */
    List<TaskBuyerRule> queryByTaskBuyerRuleDto(@Param("taskBuyerRuleDto") TaskBuyerRuleDto taskBuyerRuleDto );
    /**
     * 根据规则查询任务数量
     * @param taskBuyerRuleDto
     * @return
     */
    Integer countByTaskBuyerRuleDto( @Param("taskBuyerRuleDto") TaskBuyerRuleDto taskBuyerRuleDto );


}
