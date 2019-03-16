package com.netease.meta;

import org.springframework.stereotype.Component;

/**
 * @Author Muyuxi
 * @Date 2019/2/24
 */
@Component
public class Commodity {
    private Integer commodityID;  //商品ID
    private String title;         //标题
    private String abstractMsg;   //信息摘要
    private Double currentPrice;   //商品当前价格
    private String text;          //商品详细信息
    private String picture;       //图片url地址
    private Integer soldAmount;   //商品已卖出的数量
    private Integer userID;   //标注商品属于哪个商家 商家的userID

    public Commodity() {

    }

    public Commodity(Integer commodityID, String title ) {
        this.commodityID = commodityID;
        this.title = title;
    }

    public Commodity(Integer commodityID, String title, String picture) {
        this(commodityID, title);
        this.picture = picture;
    }

    public Commodity(Integer commodityID, String title, String picture,String abstractMsg, Double currentPrice, String text, Integer soldAmount, Integer userID) {
        this(commodityID,title,picture);
        this.abstractMsg = abstractMsg;
        this.currentPrice = currentPrice;
        this.text = text;
        this.soldAmount = soldAmount;
        this.userID = userID;
    }

    public Integer getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(Integer commodityID) {
        this.commodityID = commodityID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractMsg() {
        return abstractMsg;
    }

    public void setAbstractMsg(String abstractMsg) {
        this.abstractMsg = abstractMsg;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(Integer soldAmount) {
        this.soldAmount = soldAmount;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "commodityName: "+title+" price: "+currentPrice+"已卖出"+soldAmount+"件";
    }


}
