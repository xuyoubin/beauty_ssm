package com.muma.dao;

import com.muma.entity.TaskFree;
import com.muma.enums.TaskTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskFreeDao {

    /**
     * 添加一个佣金费用
     * @param taskFree
     */
    void addTaskFree(TaskFree taskFree);
	 /**
     * 根据类型查询佣金集合
     *
     * @return
     */
    List<TaskFree> queryTaskFreeListByType(@Param("type") TaskTypeEnum taskTypeEnum);



}
