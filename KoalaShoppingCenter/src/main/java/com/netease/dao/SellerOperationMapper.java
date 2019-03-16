package com.netease.dao;

import com.netease.meta.Commodity;
import java.util.List;

/**
 * @Author Muyuxi
 * @Date 2019/2/25
 * @Describtion
 */
public interface SellerOperationMapper {
    /**
     * @Author Muyuxi
     * @Description 查询此卖家相关的所有商品信息 包括其商品售出的数量
     * @Date 2019/2/25
     * @return java.util.List<com.netease.meta.Commodity>
     */
    List<Commodity> queryAllCommoditysBySeller(Integer userID);

    /**
     * @Author Muyuxi
     * @Description 商家发布商品
     * @Date 2019/2/25
     * @return
     */
    Integer insertCommodity(Commodity commodity);

    /**
     * @Author Muyuxi
     * @Description 显示商品的详细信息(该商家的商品的售出数量)
     * @Date 2019/2/25
     * @return com.netease.meta.Commodity
     */
    Commodity queryCommodityInfo(Integer commodityID);

    /**
     * @Author Muyuxi
     * @Description 商家更新商品信息
     * @Date 2019/2/25
     * @return java.lang.Integer
     */

    Integer updateCommodityInfo(Commodity commodity);



    /**
     * @Author Muyuxi
     * @Description 根据商品ID删除商品
     * @Date 2019/3/7
     * @return java.lang.Integer
     */
    Integer deleteUnSellCommodity(Integer commodityID);


    /**
     * @Author Muyuxi
     * @Description 查询商家记录数
     * @Date 2019/3/8
     * @return java.lang.Integer
     */
    Integer queryCountOfCommodity(Integer userID);

}
