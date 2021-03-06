package com.muma.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muma.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/** 
 * 通过银行的Bin号 来获取 银行名称
 * 
 */ 
public class BankNameUtil {

    public static Map<String,String> bankNameMap = new HashMap<>();
    static {
        bankNameMap.put("ICBC","中国工商银行");
        bankNameMap.put("BOC","中国银行");
        bankNameMap.put("CCB","中国建设银行");
        bankNameMap.put("ABC","中国农业银行");
        bankNameMap.put("PSBC","中国邮政储蓄银行");
        bankNameMap.put("CEB","中国光大银行");
        bankNameMap.put("BCM","交通银行");
        bankNameMap.put("CMB","招商银行");
        bankNameMap.put("CMBC","民生银行");
        bankNameMap.put("CIB","兴业银行");
        bankNameMap.put("SPABANK", "平安银行");
        bankNameMap.put("SPDB", "上海浦东发展银行");
        bankNameMap.put("EGBANK", "恒丰银行");
    }

    /**
     * 根据银行卡号获取对应的银行变化如：ICBC
     * @param bankCardId
     * @return
     * DC: "储蓄卡",
     * CC: "信用卡",
     * SCC: "准贷记卡",
     * PC: "预付费卡"
     */
    public static JSONObject checkBankName(String bankCardId){
        JSONObject result = new JSONObject();
        result.put("success",false);
        result.put("message","");
        if(bankCardId==null || bankCardId.length()<16 || bankCardId.length()>19){
            result.put("message","银行卡号码位数不正确！");
            return result;
        }
        try {
            String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json";
            Map<String, String> map = new HashMap<String, String>();
            map.put("cardNo", bankCardId);
            map.put("cardBinCheck", "true");
            String json_Str = HttpUtils.httpSend(url, map, "utf-8");
            System.out.println("返回结果："+json_Str);
            if(StringUtils.isNotBlank(json_Str)){
                JSONObject json = JSON.parseObject(json_Str);
                boolean validated = json.getBoolean("validated");
                if(validated){
                    String bank = json.getString("bank");
                    String cardType = json.getString("cardType");
                    String name =  bankNameMap.get(bank);
                    if(StringUtils.isEmpty(name)){
                        result.put("message","暂不支持该银行卡，请更换其他银行卡！");
                        return result;
                    }
                    if(!"DC".equals(cardType)){
                        result.put("message","暂不支持该类型卡，请更换储蓄卡！");
                        return result;
                    }
                    result.put("success",true);
                    result.put("message",name);
                    return result;
                }else {
                    result.put("message","银行卡号码不正确!");
                    return result;
                }
            }
        } catch (Exception e) {
            result.put("message","查询银行卡号异常！");
            return result;
        }
        return result;
    }

    public static void main(String[] args) {
        JSONObject re = checkBankName("4391880081522983");
        System.out.println(JSONObject.toJSONString(re));
    }

}
