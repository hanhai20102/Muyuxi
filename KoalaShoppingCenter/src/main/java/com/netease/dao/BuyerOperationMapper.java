package com.netease.dao;

import com.netease.meta.Commodity;
import com.netease.meta.CartItem;
import com.netease.meta.FinanceItem;
import java.util.List;
import java.util.Map;

/**
 * @Author Muyuxi
 * @Date 2019/2/25
 * @Describtion
 */
public interface BuyerOperationMapper {

    /**
     * @Author Muyuxi
     * @Description 查询所有的商品（游客模式）
     * @Date 2019/2/25
     * @return java.util.List<com.netease.meta.Commodity>
     */
    List<Commodity> queryAllCommoditys();

    /**
     * @Author Muyuxi
     * @Description 将商品加入购物车
     * @Date 2019/2/25
     * @return java.lang.Integer
     */

    Integer insertToShoppingCart(CartItem cartItem);

    /**
     * @Author Muyuxi
     * @Description 删除购物车中的项目
     * @Date 2019/2/25
     * @return java.lang.Integer
     */

    Integer deleteShoppingCartItem(Integer commodityID);


    /**
     * @Author Muyuxi
     * @Description 清空购物车
     * @Date 2019/2/25
     * @return java.lang.Integer
     */

    Integer deleteShoppingCart(Integer userID);

    /**
     * @Author Muyuxi
     * @Description 更新用户的购买数量
     * @Date 2019/2/25
     * @return java.lang.Integer
     */
    Integer updateCountForCartItem(Map<String,Integer> param);

    /**
     * @Author Muyuxi
     * @Description 查询购物车中的商品
     * @Date 2019/2/25
     * @return java.util.List<com.netease.meta.CartItem>
     */
    List<CartItem> queryShoppingCartCommodity(Integer userID);

    /**
     * @Author Muyuxi
     * @Description 查询购物车中的商品
     * @Date 2019/3/5
     * @return com.netease.meta.CartItem
     */
    Integer querySCItem(Integer commodityID);

    /**
     * @Author Muyuxi
     * @Description 购买商品
     * @Date 2019/2/25
     * @return java.lang.Integer
     */

    Integer insertIntoFinance(FinanceItem financeItem);


    /**
     * @Author Muyuxi
     * @Description 查询用户已经购买过的商品的集合
     * @Date 2019/2/25
     * @return java.util.List<com.netease.meta.FinanceItem>
     */
    List<FinanceItem> queryPurchasedCommoditys(Integer userID);

    /**
     * @Author Muyuxi
     * @Description 查询所有的商品信息 并显示该买家是否购买的状态
     * @Date 2019/2/25
     * @return java.util.List<com.netease.meta.FinanceItem>
     */
    List<FinanceItem> queryAllCommodityWithStatus();

    /**
     * @Author Muyuxi
     * @Description 查询未购买的商品
     * @Date 2019/2/25
     * @return java.util.List<com.netease.meta.FinanceItem>
     */
    List<FinanceItem> queryUnPurchasedCommoditys(Integer userID);



    /**
     * @Author Muyuxi
     * @Description 更新商品表中的售出数量
     * @Date 2019/3/1
     * @return java.lang.Integer
     */
    Integer updateCountForCommodity(Map<String,Integer> param);




    /**
     * @Author Muyuxi
     * @Description 查询商品详细信息
     * @Date 2019/3/4
     * @return com.netease.meta.FinanceItem
     */
    FinanceItem queryCommodityInfo(Integer commodityID);









}
