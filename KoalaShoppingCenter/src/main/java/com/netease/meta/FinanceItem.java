package com.netease.meta;


import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Muyuxi
 * @Date 2019/2/24
 * @Describtion 账务表示买家购买的一件件商品
 */
@Component
public class FinanceItem extends Commodity{

    private Integer buyerID;        //买家用户ID
    private Double boughtPrice;     // 购买时的价格
    private Integer amount;        // 购买的数量
    private Date purchaseTime;     //购买的时间

    public FinanceItem() {

    }

    public FinanceItem(Integer commodityID, String title, String picture, Integer buyerID, Double boughtPrice, Integer amount, Date purchaseTime) {
        super(commodityID, title, picture);
        this.buyerID = buyerID;
        this.boughtPrice = boughtPrice;
        this.amount = amount;
        this.purchaseTime = purchaseTime;
    }

    public Integer getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Integer buyerID) {
        this.buyerID = buyerID;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public String toString() {
        return "FinanceItem{" +
                "buyerID=" + buyerID +
                ", boughtPrice=" + boughtPrice +
                ", amount=" + amount +
                ", purchaseTime=" + purchaseTime +
                '}';
    }
}
