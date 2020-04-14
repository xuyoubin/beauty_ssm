package com.muma.controller;

import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.UserInfoDto;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.util.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.muma.common.HttpContext.getRequset;

@Controller
@RequestMapping("/common")
public class CommonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 任务步骤入口
     * @return
     */
    @RequestMapping(value = "stepSave.action",method = RequestMethod.POST)
    @Authenticate(permissions = "0,1,2")
    @ResponseBody
    public BaseResult stepSave(){
        String uniqueId = getRequset().getParameter("uniqueId");
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            return new BaseResult(true, "步骤保存成功！");
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("步骤保存异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }

}
