package com.netease.test;

import com.netease.dao.BuyerOperationMapper;
import com.netease.dao.SellerOperationMapper;
import com.netease.dao.UserOperationMapper;
import com.netease.exception.SystemException;
import com.netease.meta.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Muyuxi
 * @Date 2019/2/25
 * @Describtion
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDao {
    @Autowired
    private UserOperationMapper userOperationMapper;
    @Autowired
    private BuyerOperationMapper buyerOperationMapper;
    @Autowired
    private SellerOperationMapper sellerOperationMapper;

    @Test
    public void testUserOperation() throws SystemException {
        User user = new User("null","37254660e226ea65ce6f1efd54233424");
        User user1 = userOperationMapper.findUser(user);
        if(user1!=null) System.out.println(user1);
        else throw new SystemException(ResultCode.USER_ERROR);
    }

    @Test
    public void testCommocitys() {
        List<Commodity> commodities = buyerOperationMapper.queryAllCommoditys();
        for (Commodity c : commodities) {
            System.out.println(c);
        }
    }

    @Test
    public void testSeller() throws SystemException {
//        Integer res = sellerOperationMapper.insertCommodity(new Commodity(3,"斗罗大陆","https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2132211606,2010640083&fm=58&bpow=580&bpoh=861","唐家三少",20.0,
//                "史莱克七怪",0,3));
//        if(res>0) System.out.println("插入成功"+res);
//        else throw new SystemException("插入失败");
//
//        List<Commodity> commodities = sellerOperationMapper.queryAllCommoditysBySeller(2);
//        for (Commodity c : commodities) {
//            System.out.println(c);
//
//        }

        Commodity c = new Commodity(3,"终极斗罗",null,"蓝轩宇",20.0,
                "史莱克七怪",0,3);
        if(sellerOperationMapper.updateCommodityInfo(c)>0) System.out.println("修改成功");
        else throw new SystemException(ResultCode.DATA_OPERATION_ERROR);
        Commodity commodity = sellerOperationMapper.queryCommodityInfo(3);
        System.out.println(commodity);

    }

    @Test
    public void addShoppingCart() throws SystemException {
        //Integer res = buyerOperationMapper.insertToShoppingCart(new CartItem(1,"笔记本电脑",1,30,5000.25));
        Map<String,Integer> map = new HashMap<>();
        map.put("amount",80);
        map.put("commodityID",1);
        Integer res = buyerOperationMapper.updateCountForCartItem(map);
        if(res>0) System.out.println("更新成功");
        else throw new SystemException(ResultCode.DATABASE_ERROR);
    }

    @Test
    public void addToFinance() throws SystemException {
        FinanceItem financeItem = new FinanceItem();
        financeItem.setAmount(80);
        financeItem.setCommodityID(1);
        financeItem.setBuyerID(1);
        financeItem.setBoughtPrice(5000.25);
        Integer res = buyerOperationMapper.insertIntoFinance(financeItem);
        if(res>0) System.out.println("插入账务成功");
        else throw new SystemException(ResultCode.DATABASE_ERROR);

    }

    @Test
    public void testShoppingCartShow() {
        List<CartItem> res = buyerOperationMapper.queryShoppingCartCommodity(1);
        System.out.println(res);

        List<FinanceItem> l = buyerOperationMapper.queryPurchasedCommoditys(1);
        System.out.println("zzz");

        List<FinanceItem> ls = buyerOperationMapper.queryAllCommodityWithStatus();
        System.out.println("zzz");

        List<FinanceItem> ss = buyerOperationMapper.queryUnPurchasedCommoditys(1);
        System.out.println("======");
    }

}


