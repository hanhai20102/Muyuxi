package com.netease.meta;

import org.springframework.stereotype.Component;

/**
 * @Author Muyuxi
 * @Date 2019/2/24
 * @Description 购物车项目类
 */
@Component
public class CartItem extends Commodity{

    private Integer amount;
    private Integer buyerID;  //买家的userID

    public CartItem() {

    }

    public CartItem(Integer commodityID, String title, Integer buyerID, Integer amount, Double price) {
        super(commodityID, title);
        this.amount = amount;
        this.buyerID = buyerID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Integer buyerID) {
        this.buyerID = buyerID;
    }

    @Override
    public String toString() {
        return "商品名称： "+this.getTitle() +" 在购物车中";
    }
}
