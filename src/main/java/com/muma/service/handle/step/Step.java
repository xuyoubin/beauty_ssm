package com.muma.service.handle.step;

public interface Step {
    /**
     * 步骤保存方法
     */
    public Step initStep(String step);
    /**
     * 步骤初始化方法
     */
    public void init();
    /**
     * 步骤校验方法
     */
    public void validator();
}
