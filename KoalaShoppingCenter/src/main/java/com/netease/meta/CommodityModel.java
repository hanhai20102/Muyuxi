package com.netease.meta;

import java.util.List;

/**
 * @Author Muyuxi
 * @Date 2019/3/4
 * @Describtion springMVC接受List类型的数据
 */
public class CommodityModel {
    private List<FinanceItem> finance;

    public List<FinanceItem> getFinance() {
        return finance;
    }

    public void setFinance(List<FinanceItem> finance) {
        this.finance = finance;
    }
}
