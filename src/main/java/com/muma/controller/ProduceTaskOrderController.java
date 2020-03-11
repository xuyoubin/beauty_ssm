package com.muma.controller;

import com.muma.common.PageBean;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.UserInfoDto;
import com.muma.entity.BuyerRule;
import com.muma.entity.Statistics;
import com.muma.entity.TaskOrder;
import com.muma.enums.PlatformEnum;
import com.muma.enums.RoalEnum;
import com.muma.enums.TaskTypeEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.ProduceTaskOrderService;
import com.muma.util.Authenticate;
import com.muma.util.Precondition;
import org.apache.commons.lang3.StringUtils;
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
                                   TaskOrder taskOrder, Statistics statistics, BuyerRule buyerRule){
        String shopId = getRequset().getParameter("shopId");
        String commodity = getRequset().getParameter("commodity");
        String commodityUrl = getRequset().getParameter("commodityUrl");
        String commodityId = getRequset().getParameter("commodityId");
        String taskRule = getRequset().getParameter("taskRule");
        String type = getRequset().getParameter("type");
        String startTime = getRequset().getParameter("startTime");
        String entranceId = getRequset().getParameter("entranceId");
        String conditions = getRequset().getParameter("conditions");
        String tagFlag = getRequset().getParameter("tagFlag");
        String tbkFlag = getRequset().getParameter("tbkFlag");
        String defineFlag = getRequset().getParameter("defineFlag");
        String taskRemark = getRequset().getParameter("taskRemark"); //任务备注
        String taskNumber = getRequset().getParameter("taskNumber");//发布任务数
        String price = getRequset().getParameter("price");
        String age = getRequset().getParameter("age");
        String sex = getRequset().getParameter("sex");
        String credit = getRequset().getParameter("credit");
        String province = getRequset().getParameter("province");
        String buyerRemark = getRequset().getParameter("buyerRemark");//买家要求备注
        String tags = getRequset().getParameter("tags");
        try{
            Precondition.checkState(StringUtils.isNotBlank(shopId), "请填写店铺!");
            Precondition.checkState(StringUtils.isNotBlank(type), "请填写任务类型!");
            Precondition.checkState(StringUtils.isNotBlank(startTime), "请填写任务开始时间!");
            TaskTypeEnum taskTypeEnum = TaskTypeEnum.stateOf(Integer.valueOf(type));
            Precondition.checkNotNull(taskTypeEnum, "暂不支持该任务类型!");
            taskOrder.setShopId(Integer.valueOf(shopId));
            taskOrder.setCommodity(commodity);
            taskOrder.setCommodityId(commodityId);
            taskOrder.setCommodityUrl(commodityUrl);
            taskOrder.setTaskRule(taskRule);
            taskOrder.setType(taskTypeEnum);
//            taskOrder.setStartTime();




            produceTaskOrderService.addTaskOrder(taskOrder,);


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
        String shopId = getRequset().getParameter("shopId");//参数
        String operateStatus = getRequset().getParameter("operateStatus");//参数
        String status = getRequset().getParameter("status");//参数
        String type = getRequset().getParameter("type");//参数
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
        try{
            UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();

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
