package com.muma.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发布任务DTO
 */
public class ProduceTaskOrderDto implements Serializable {


    private static final long serialVersionUID = -4450660240745046481L;
    /**
     * 店铺ID
     */
    private Integer shopId;
    /**
     * 商品名称
     */
    private String commodity;
    /**
     * 商品地址
     */
    private String commodityUrl;
    /**
     * 商品ID
     */
    private String commodityId;
    /**
     * 任务要求 0,1,3
     */
    private String taskRule;
    /**
     * 发布类型 0-销售；2-浏览
     */
    private Integer type;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 浏览入口id 0电脑 1手机
     */
    private Integer entranceId;
    /**
     * 是否给买家打入关键字标签：0是，1否
     */
    private Integer tagFlag;
    /**
     * 是否查询淘宝客订单： 0是，1否
     */
    private Integer tbkFlag;
    /**
     * 是否需要确认收货0-是，1-否
     */
    private Integer defineFlag;
    /**
     * 任务备注
     */
    private String taskRemark;

    /**
     * 商家发布笔数
     */
    private Integer taskNumber;
    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 年龄范围 1：18~25；2:26~35，3:36~45,4:45~以上
     */
    private String age;
    /**
     *性别
     */
    private String sex;
    /**
     * 省份
     */
    private String province;
    /**
     * 信用
     */
    private Integer credit;

    /**
     * 用户标签
     */
    private String tags;
    /**
     * 买家要求备注
     */
    private String buyerRemark;

    /**
     * 排序方式
     */
    private Integer sortFlag;
    /**
     *价格区间128,300
     */
    private String priceRange;
    /**
     * 发货区
     */
    private String place;
    /**
     * 卡位条件备注
     */
    private String conditionsRemark;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getTaskRule() {
        return taskRule;
    }

    public void setTaskRule(String taskRule) {
        this.taskRule = taskRule;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getEntranceId() {
        return entranceId;
    }

    public void setEntranceId(Integer entranceId) {
        this.entranceId = entranceId;
    }

    public Integer getTagFlag() {
        return tagFlag;
    }

    public void setTagFlag(Integer tagFlag) {
        this.tagFlag = tagFlag;
    }

    public Integer getTbkFlag() {
        return tbkFlag;
    }

    public void setTbkFlag(Integer tbkFlag) {
        this.tbkFlag = tbkFlag;
    }

    public Integer getDefineFlag() {
        return defineFlag;
    }

    public void setDefineFlag(Integer defineFlag) {
        this.defineFlag = defineFlag;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public Integer getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Integer sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getConditionsRemark() {
        return conditionsRemark;
    }

    public void setConditionsRemark(String conditionsRemark) {
        this.conditionsRemark = conditionsRemark;
    }
}
