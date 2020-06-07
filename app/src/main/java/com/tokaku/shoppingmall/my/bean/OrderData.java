package com.tokaku.shoppingmall.my.bean;


import java.util.HashMap;

public class OrderData {
    private String orderId;
    private String userId;
    private String name;
    private String phone;
    private String address;
    private HashMap<String,Integer> goods;

    public OrderData(String orderId, String userId, String name, String phone, String address, HashMap<String, Integer> goods) {
        this.orderId = orderId;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.goods = goods;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<String, Integer> goods) {
        this.goods = goods;
    }
}
