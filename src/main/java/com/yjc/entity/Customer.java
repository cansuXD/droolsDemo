package com.yjc.entity;

import java.util.List;

public class Customer {
    //客户姓名
    private String name;
    private List<Order> orderList;
    //订单集合
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }
}
