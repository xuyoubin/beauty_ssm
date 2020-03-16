package com.muma.controller;

import com.muma.common.PageBean;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.ConsumeTaskOrderDto;
import com.muma.dto.UserInfoDto;
import com.muma.enums.RoalEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.ConsumeTaskOrderService;
import com.muma.util.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.muma.common.HttpContext.getRequset;

@Controller
@RequestMapping("/consumeTask")
public class ConsumeTaskOrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumeTaskOrderService consumeTaskOrderService;
    /**
     * 买家获取任务
     * @return
     */
    @RequestMapping(value = "getOrder.action",method = RequestMethod.POST)
    @Authenticate(permissions = "0")
    @ResponseBody
    public BaseResult getOrder(){
        String platform = getRequset().getParameter("platform");
        String price = getRequset().getParameter("price"); //参数最大价格
        String taskType = getRequset().getParameter("taskType");//任务类型
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            ConsumeTaskOrderDto consumeTaskOrderDto = consumeTaskOrderService.getOrder(userInfoDto,platform,price,taskType);
            return new BaseResult(true, consumeTaskOrderDto);
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("获取任务异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }
    /**
     * 查询买家历史接单列表
     * @returnlist
     */
    @RequestMapping(value = "orderHistoryList.action")
    @Authenticate(permissions = "0,2")
    @ResponseBody
    public BaseResult orderHistoryList(){
        String pageIndex = getRequset().getParameter("pageIndex");
        String regPhone = getRequset().getParameter("regPhone");//参数
        String status = getRequset().getParameter("status");//参数
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            //商家强制传参
            if(RoalEnum.BUSINESS_ROAL.equals(userInfoDto.getRoalId())){
                regPhone = userInfoDto.getRegPhone();
            }
            PageBean pg = consumeTaskOrderService.queryOrderHistoryList(pageIndex,regPhone,status);
            return new BaseResult(true, pg);
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("查询接单列表异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }

}
