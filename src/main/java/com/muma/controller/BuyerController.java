package com.muma.controller;

import com.muma.controller.base.BaseResult;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.BuyerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.muma.common.HttpContext.getRequset;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyerService buyerService;
    /**
     * 添加平台账号
     * @return
     */
    @RequestMapping(value = "addBuyer.action",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addBuyer(){
        String regPhone = getRequset().getParameter("regPhone");
        try{
            buyerService.addBuyer();
            return new BaseResult(true, "添加账号成功！");
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("添加账号异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }


}
