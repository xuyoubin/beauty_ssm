package com.muma.controller;

import com.muma.common.PageBean;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.UserInfoDto;
import com.muma.enums.RoalEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.BusinessService;
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
@RequestMapping("/business")
public class BusinessController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BusinessService businessService;

    /**
     * 添加商家店铺
     * @return
     */
    @RequestMapping(value = "addShop.action",method = RequestMethod.POST)
    @Authenticate(permissions = "1,2")
    @ResponseBody
    public BaseResult addShop(){
        String uniqueId = getRequset().getParameter("uniqueId");
        String shopName = getRequset().getParameter("shopName");
        String shopUrl = getRequset().getParameter("shopUrl");
        String shopType = getRequset().getParameter("shopType");
        String shopWw = getRequset().getParameter("shopWw");
        String repeatDay = getRequset().getParameter("repeatDay");
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            businessService.addShop(userInfoDto.getRegPhone(),uniqueId,shopName,shopUrl,shopType,shopWw,repeatDay);
            return new BaseResult(true, "添加店铺成功！");
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("添加店铺异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }
    /**
     * 查询店铺列表
     * @returnlist
     */
    @RequestMapping(value = "shopList.action")
    @Authenticate(permissions = "1,2")
    @ResponseBody
    public BaseResult roadList(){
        String pageIndex = getRequset().getParameter("pageIndex");
        String regPhone = getRequset().getParameter("regPhone");
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            //商家强制传参
            if(RoalEnum.BUSINESS_ROAL.equals(userInfoDto.getRoalId())){
                regPhone = userInfoDto.getRegPhone();
            }
            PageBean pg = businessService.queryShopList(pageIndex,regPhone);
            return new BaseResult(true, pg);
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("查询店铺列表异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }


}
