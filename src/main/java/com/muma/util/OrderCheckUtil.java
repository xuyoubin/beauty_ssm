package com.muma.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 获取订单号是否是淘宝客下单
 * 
 */ 
public class OrderCheckUtil {

    /**
     *
     */
    public static JSONObject checkOrder(String orderId){
        JSONObject result = new JSONObject();
        result.put("success",false);
        result.put("message","");
        if(orderId==null || orderId.length() != 18){
            result.put("message","订单号为18位数字！");
            return result;
        }
        Pattern p = Pattern.compile("\\d{18}");
        Matcher m = p.matcher(orderId);
        if(!m.matches()){
            result.put("message","订单号为18位数字！");
            return result;
        }
        try {
            String url = "http://api.tbk.dingdanxia.com/shop/check_tk_order";
            Map<String, String> map = new HashMap<String, String>();
            map.put("apikey", "yKEM80xbjZam7972CdH7DjbodnYtLDFP");
            map.put("order_no", orderId);
            String json_Str = HttpUtils.httpSend(url, map, "utf-8");
            System.out.println("返回结果："+json_Str);
            if(StringUtils.isNotBlank(json_Str)){
                JSONObject json = JSON.parseObject(json_Str);
            }
        } catch (Exception e) {
            result.put("message","查询订单下单类型异常！");
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public static void main(String[] args) {
        JSONObject re = checkOrder("848547904139770088");
        System.out.println(JSONObject.toJSONString(re));
    }

}
