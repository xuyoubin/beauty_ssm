package com.muma.entity;

public class Order {
    private Long id;
    private Long task_order_id;
    private Long type;
    private Long player_id;
    private Long business_id;
    private String shop;
    private String commodity;
    private String order_no;
    private Double money;
    private Double fee;
    private Long status;
    private String auth_image_one;
    private String auth_image_two;
    private String create_by;
    private java.sql.Timestamp create_time;
    private String update_by;
    private java.sql.Timestamp update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTask_order_id() {
        return task_order_id;
    }

    public void setTask_order_id(Long task_order_id) {
        this.task_order_id = task_order_id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public Long getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Long business_id) {
        this.business_id = business_id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getAuth_image_one() {
        return auth_image_one;
    }

    public void setAuth_image_one(String auth_image_one) {
        this.auth_image_one = auth_image_one;
    }

    public String getAuth_image_two() {
        return auth_image_two;
    }

    public void setAuth_image_two(String auth_image_two) {
        this.auth_image_two = auth_image_two;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public java.sql.Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(java.sql.Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public java.sql.Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(java.sql.Timestamp update_time) {
        this.update_time = update_time;
    }
}
