package com.muma.controller;

import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.UserInfoDto;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.BuyerService;
import com.muma.util.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    @Authenticate(permissions = "0,2")
    @ResponseBody
    public BaseResult addBuyer(@RequestParam("indexImage") MultipartFile indexImage,
                               @RequestParam("infoImage") MultipartFile infoImage,
                               @RequestParam("commentImage") MultipartFile commentImage){
        String platformId = getRequset().getParameter("platformId");
        String nickName = getRequset().getParameter("nickName");
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            buyerService.addBuyer(userInfoDto.getRegPhone(),platformId,nickName,indexImage,infoImage,commentImage );
            return new BaseResult(true, "添加账号成功！");
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("添加账号异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }


}
