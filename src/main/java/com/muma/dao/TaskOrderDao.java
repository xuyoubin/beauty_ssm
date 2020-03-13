package com.muma.dao;

import com.muma.entity.TaskOrder;
import com.muma.enums.OperateStatusEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.TaskTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售浏览发布任务
 */
public interface TaskOrderDao {
    /**
     * 添加一个任务
     * @param
     */
    void addTaskOrder(TaskOrder taskOrder);
    /**
     * 根据条件查询任务列表
     * @return
     */
    List<TaskOrder> queryTaskOrderList(@Param("regPhone") String regPhone, @Param("shopId") Integer shopId, @Param("type") TaskTypeEnum type,
                                       @Param("operateStatus") OperateStatusEnum operateStatus, @Param("status") StatusEnum status,
                                       @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
    
}
