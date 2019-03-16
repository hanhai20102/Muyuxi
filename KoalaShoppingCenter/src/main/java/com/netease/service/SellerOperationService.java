package com.netease.service;

import com.netease.dao.SellerOperationMapper;
import com.netease.exception.SystemException;
import com.netease.meta.Commodity;
import com.netease.meta.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion
 */
@Service
public class SellerOperationService {
    @Autowired
    private SellerOperationMapper sellerOperationMapper;
    private static Logger logger = LoggerFactory.getLogger(SellerOperationService.class);

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Commodity> queryAllCommoditysBySeller(Integer userID) {
        List<Commodity> res = null;
        res = sellerOperationMapper.queryAllCommoditysBySeller(userID);
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = SystemException.class)
    public Integer insertCommodity(Commodity commodity) throws SystemException {
        int count = sellerOperationMapper.queryCountOfCommodity(commodity.getUserID());
        if(count > 999) {
            throw new SystemException(ResultCode.MAX_Commodity);
        }
        Integer res = sellerOperationMapper.insertCommodity(commodity);
        Integer id= -1 ;
        if(res > 0) {
            logger.info("商品 {} 发布成功",commodity.getTitle());
            id = commodity.getCommodityID();
            System.out.println("====插入的商品ID是"+id);
        }
        else throw new SystemException(ResultCode.DATA_OPERATION_ERROR);
        return id;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Commodity queryCommodityInfo(Integer commodityID) throws SystemException {
        Commodity res = sellerOperationMapper.queryCommodityInfo(commodityID);
        if(res==null) {
            logger.error("查询商品信息不存在");
            throw new SystemException(ResultCode.DATA_NOTEXISTS_ERROR);
        }
        logger.info("Query Success");
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = SystemException.class)
    public Integer updateCommodityInfo(Commodity commodity) throws SystemException {
        Integer res = sellerOperationMapper.updateCommodityInfo(commodity);
        Integer id = -1;
        if(res > 0) {
            logger.info("商品 {} 信息更新成功",commodity.getTitle());
            id = commodity.getCommodityID();
        }
        else throw new SystemException(ResultCode.DATA_OPERATION_ERROR);
        return id;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = SystemException.class)
    public void deleteUnSellCommodity(Integer commodityID) {
        //先确认该商品是否是未售出商品
        Commodity c = sellerOperationMapper.queryCommodityInfo(commodityID);
        if(c.getSoldAmount()>0) throw new SystemException(ResultCode.DEL_ERROR);
        else {
            Integer res = sellerOperationMapper.deleteUnSellCommodity(commodityID);
            if(res > 0) logger.info("商品删除成功");
            else throw new SystemException(ResultCode.DATA_OPERATION_ERROR);
        }
    }
}
