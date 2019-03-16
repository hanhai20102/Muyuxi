package com.netease.service;

import com.netease.dao.BuyerOperationMapper;
import com.netease.exception.SystemException;
import com.netease.meta.CartItem;
import com.netease.meta.Commodity;
import com.netease.meta.FinanceItem;
import com.netease.meta.ResultCode;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion
 */
@Service
public class BuyerOperationService {
    @Autowired
    private BuyerOperationMapper buyerOperationMapper;
    private static Logger logger = LoggerFactory.getLogger(SellerOperationService.class);
/*    @Autowired
    private SqlSession sqlSession;*/
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Commodity> queryAllCommoditys() {
        List<Commodity> res = null;
        res = buyerOperationMapper.queryAllCommoditys();
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SystemException.class)
    public void insertToShoppingCart(CartItem cartItem) throws SystemException {
        //加入购物车之间 查询该商品是否已经存在于购物车中
        Integer commodityID = cartItem.getCommodityID();
        Integer exist = buyerOperationMapper.querySCItem(commodityID);
        if(exist > 0) {   //商品已存在购物车 更新购物车中商品的数量即可
            Map<String,Integer> map = new HashMap<>();
            map.put("commodityID",cartItem.getCommodityID());
            map.put("amount",cartItem.getAmount());
            buyerOperationMapper.updateCountForCartItem(map);
        } else {
            Integer res = buyerOperationMapper.insertToShoppingCart(cartItem);
            if(res > 0) logger.info("商品 {} 添加购物车成功",cartItem.getTitle());
            else throw new SystemException(ResultCode.ADD_SHOPPINGCART_ERROR);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SystemException.class)
    public void deleteShoppingCartItem(Integer commodityID) throws SystemException {
        Integer res = buyerOperationMapper.deleteShoppingCartItem(commodityID);
        if(res > 0) logger.info("商品已从购物车中移除");
        else throw new SystemException(ResultCode.DEL_SHOPPINGCART_ERROR);
    }

    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SystemException.class)
    public void updateCountForCartItem(Map<String,Integer> map) throws SystemException {
        Integer res = buyerOperationMapper.updateCountForCartItem(map);
        if(res > 0) logger.info("购物车中商品数量已更新");
        else throw new SystemException(ResultCode.DATA_OPERATION_ERROR);
    }

    @Transactional(propagation = Propagation.REQUIRED ,readOnly = true)
    public List<CartItem> queryShoppingCartCommodity(Integer userID) {
        List<CartItem> res = null;
        res = buyerOperationMapper.queryShoppingCartCommodity(userID);
        return res;
    }


    //清空购物车并将购买的商品写入到finance表
    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SystemException.class)
    public void insertIntoFinance(FinanceItem[] finance,Integer userID) throws SystemException {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        BuyerOperationMapper mapper = sqlSession.getMapper(BuyerOperationMapper.class);
        Map<String,Integer> map = new HashMap<>();
        //批量执行将购物车中的商品添加到finance中
        for (FinanceItem financeItem : finance) {
            financeItem.setBuyerID(userID);
            Integer insertRes = mapper.insertIntoFinance(financeItem);
            if(insertRes == -2147482646) {
                //更新商品表中的商品数量
                map.put("commodityID",financeItem.getCommodityID());
                map.put("soldAmount",financeItem.getAmount());
                Integer res = mapper.updateCountForCommodity(map);
                if(res != -2147482646) throw new SystemException(ResultCode.DATABASE_ERROR);
            } else throw new SystemException(ResultCode.PURCHASE_ERROR);
        }
        //清空购物车中的商品
        Integer delRes = mapper.deleteShoppingCart(userID);
        System.out.println("delres"+delRes);
        if(delRes == -2147482646) {
            logger.info("商品购买成功");
            sqlSession.commit();
        }else throw new SystemException(ResultCode.DATA_OPERATION_ERROR);
    }

    @Transactional(propagation = Propagation.REQUIRED ,readOnly = true)
    public List<FinanceItem> queryPurchasedCommoditys(Integer userID) {
        List<FinanceItem> res = null;
        res = buyerOperationMapper.queryPurchasedCommoditys(userID);
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED ,readOnly = true)
    public List<FinanceItem> queryAllCommodityWithStatus() {
        List<FinanceItem> res = null;
        res = buyerOperationMapper.queryAllCommodityWithStatus();
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED ,readOnly = true)
    public List<FinanceItem> queryUnPurchasedCommoditys(Integer userID) {
        List<FinanceItem> res = null;
        res = buyerOperationMapper.queryUnPurchasedCommoditys(userID);
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public FinanceItem queryCommodityInfo(Integer commodityID) {
        FinanceItem res = buyerOperationMapper.queryCommodityInfo(commodityID);
        if(res==null) {
            logger.error("查询商品信息不存在");
            throw new SystemException(ResultCode.DATA_NOTEXISTS_ERROR);
        }
        logger.info("Query Success");
        return res;
    }
}
