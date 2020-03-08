package com.muma.service.impl;

import com.muma.dao.BuyerDao;
import com.muma.entity.Buyer;
import com.muma.enums.PlatformEnum;
import com.muma.enums.StatusEnum;
import com.muma.service.BuyerService;
import com.muma.util.Precondition;
import com.muma.util.UploadImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**\
 * 平台账号处理类
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;
    /**
     * 添加平台账号
     * @return
     */
    @Override
    public void addBuyer(String regPhone, String platformId, String nickName, MultipartFile indexImage, MultipartFile infoImage, MultipartFile commentImage) {
        Precondition.checkState(StringUtils.isNotBlank(platformId), "platformId is null!");
        Precondition.checkState(StringUtils.isNotBlank(nickName), "请填写会员名称!");
        PlatformEnum platformEnum = PlatformEnum.stateOf(Integer.valueOf(platformId));
        Precondition.checkNotNull(platformEnum, "暂不支持该平台!");
        String indexImageUrl="";
        String infoImageUrl="";
        String commentImageUrl="";
        if(PlatformEnum.TAO_BAO_PLATFORM.equals(platformEnum)){
            Precondition.checkState(StringUtils.isNotBlank(indexImage.getOriginalFilename()), "请上传支付宝首页截图!");
            Precondition.checkState(StringUtils.isNotBlank(infoImage.getOriginalFilename()), "请上传支付宝信息截图!");
            Precondition.checkState(StringUtils.isNotBlank(commentImage.getOriginalFilename()), "请上传淘宝评价截图!");
        }else {
            Precondition.checkState(StringUtils.isNotBlank(infoImage.getOriginalFilename()), "请上用户信息截图!");
        }
        //根据手机号和平台id查看是否注册过此平台
        Buyer b = buyerDao.queryByRegPhoneAndPlatformId(regPhone,platformEnum);
        Precondition.checkState(b == null, "用户已经注册过该平台!");
        if(PlatformEnum.TAO_BAO_PLATFORM.equals(platformEnum)){
            //保存图片
            indexImageUrl = UploadImageUtil.saveImage(indexImage,UploadImageUtil.IMAGE_TYPE_PLATFORM_INFO,regPhone,"支付宝首页截图");
            infoImageUrl = UploadImageUtil.saveImage(infoImage,UploadImageUtil.IMAGE_TYPE_PLATFORM_INFO,regPhone,"支付宝信息截图");
            commentImageUrl = UploadImageUtil.saveImage(commentImage,UploadImageUtil.IMAGE_TYPE_PLATFORM_INFO,regPhone,"淘宝评价截图");
        }else {
            infoImageUrl = UploadImageUtil.saveImage(infoImage,UploadImageUtil.IMAGE_TYPE_PLATFORM_INFO,regPhone,"用户信息截图");
        }
        Buyer buyer = new Buyer();
        buyer.setRegPhone(regPhone);
        buyer.setPlatformId(platformEnum);
        buyer.setName(nickName);
        buyer.setAuthImageOne(indexImageUrl);
        buyer.setAuthImageTwo(infoImageUrl);
        buyer.setAuthImageThree(commentImageUrl);
        buyer.setStatus(StatusEnum.CONFIRM_WAIT);
        buyer.setCreateBy(regPhone);
        buyerDao.addBuyer(buyer);
    }
}
