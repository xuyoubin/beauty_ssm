package com.muma.service.handle.step;

import com.muma.service.handle.step.impl.StepOneImpl;

public abstract class StepAbstract implements Step {
    /**
     * 初始化步骤
     * @param step
     * @return
     */
    @Override
    public Step initStep(String step){
        return  new StepOneImpl();
    }


    public abstract void save();

    @Override
    public void init() {

    }

    @Override
    public void validator() {

    }
}
