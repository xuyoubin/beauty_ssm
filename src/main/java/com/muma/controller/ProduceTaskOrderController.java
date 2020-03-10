package com.muma.controller;

import com.muma.common.PageBean;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.UserInfoDto;
import com.muma.enums.RoalEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
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
@RequestMapping("/produceTask")
public class ProduceTaskOrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 添加一个任务
     * @return
     */
    @RequestMapping(value = "addTaskOrder.action",method = RequestMethod.POST)
    @Authenticate(permissions = "1,2")
    @ResponseBody
    public BaseResult addTaskOrder(@RequestParam("mainImg") MultipartFile mainImg){
        String shopId = getRequset().getParameter("shopId");
        String commodity = getRequset().getParameter("commodity");
        String commodityUrl = getRequset().getParameter("commodityUrl");
        String shopType = getRequset().getParameter("commodityId");
        String type = getRequset().getParameter("type");
        String startTime = getRequset().getParameter("startTime");
        String entranceId = getRequset().getParameter("entranceId");
        String conditions = getRequset().getParameter("conditions");
        String number = getRequset().getParameter("number");
        String price = getRequset().getParameter("price");
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();

            return new BaseResult(true, "添加任务成功！");
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("添加任务异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }
    /**
     * 查询任务列表
     * @returnlist
     */
    @RequestMapping(value = "taskOrderList.action")
    @Authenticate(permissions = "1,2")
    @ResponseBody
    public BaseResult roadList(){
        String pageIndex = getRequset().getParameter("pageIndex");
        String regPhone = getRequset().getParameter("regPhone");//参数
        String shopId = getRequset().getParameter("shopId");//参数
        String operateStatus = getRequset().getParameter("operateStatus");//参数
        String status = getRequset().getParameter("status");//参数
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            //商家强制传参
            if(RoalEnum.BUSINESS_ROAL.equals(userInfoDto.getRoalId())){
                regPhone = userInfoDto.getRegPhone();
            }
            PageBean pg = null;
            return new BaseResult(true, pg);
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("查询任务列表异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }


}
