package com.muma.controller;

import com.alibaba.fastjson.JSON;
import com.muma.common.PageBean;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.ProduceTaskOrderDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Statistics;
import com.muma.enums.RoalEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.ProduceTaskOrderService;
import com.muma.util.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private ProduceTaskOrderService produceTaskOrderService;

    /**
     * 添加一个任务
     * @return
     */
    @RequestMapping(value = "addTaskOrder.action",method = RequestMethod.POST)
    @Authenticate(permissions = "1,2")
    @ResponseBody
    public BaseResult addTaskOrder(@RequestParam("mainImage") MultipartFile mainImage,
                                   @ModelAttribute ProduceTaskOrderDto produceTaskOrder){
        try{
            logger.info("发布任务提交信息："+JSON.toJSONString(produceTaskOrder));
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            produceTaskOrderService.addTaskOrder(userInfoDto.getRegPhone(),produceTaskOrder,mainImage);
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
    public BaseResult taskOrderList(){
        String pageIndex = getRequset().getParameter("pageIndex");
        String regPhone = getRequset().getParameter("regPhone");//参数
        String shopName = getRequset().getParameter("shopName");//参数
        String operateStatus = getRequset().getParameter("operateStatus");//参数
        String status = getRequset().getParameter("status");//参数
        String taskType = getRequset().getParameter("taskType");//发布类型 参数
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
            //商家强制传参
            if(RoalEnum.BUSINESS_ROAL.equals(userInfoDto.getRoalId())){
                regPhone = userInfoDto.getRegPhone();
            }
            PageBean pg = produceTaskOrderService.queryTaskOrder(pageIndex,regPhone,shopName,operateStatus,status,taskType);
            return new BaseResult(true, pg);
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("查询任务列表异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }
    /**
     * 根据任务数量和单价计算
     * 预计总本金，预计总佣金，佣金等值
     * @returnlist
     */
    @RequestMapping(value = "countMoney.action")
    @Authenticate(permissions = "1,2")
    @ResponseBody
    public BaseResult countMoney(){
        String taskNumber = getRequset().getParameter("taskNumber");//发布任务数
        String price = getRequset().getParameter("price");
        String platform = getRequset().getParameter("platform");
        String taskType = getRequset().getParameter("taskType");
        try{
            Statistics statistics =  produceTaskOrderService.countMoney(taskNumber,price,platform,taskType);
            return new BaseResult(true, statistics);
        }catch (BizException e){
            return new BaseResult(false,e.getMessage());
        } catch (Exception e){
            logger.error("计算费用异常：{}",e);
            return new BaseResult(false, ResultEnum.INNER_ERROR.getMsg());
        }
    }



}
