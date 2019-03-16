package com.netease.controller;

import com.netease.exception.SystemException;
import com.netease.meta.*;
import com.netease.service.BuyerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion
 */
@Controller
@RequestMapping("/buyer")
public class BuyerOperationController {
    @Autowired
    private BuyerOperationService buyerOperationService ;

    @RequestMapping("/queryAllCommoditys")
    @ResponseBody
    public ResponseMessage queryAllCommoditys() {
        //查询前调用pagehelper的分页方法 传入页码和每页的条数
        //PageHelper.startPage(pn,1);
        //startPage紧跟的查询就是分页查询
        List<Commodity> res = buyerOperationService.queryAllCommoditys();
        //使用pageInfo包装查询后的list 交给页面 封装了详细的分页信息包括查询出来的数据  navigatePages为连续显示的页面数
        //PageInfo page = new PageInfo(res,3);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("result",res);
    }

    @RequestMapping(value = "/insertToShoppingCart",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage insertToShoppingCart(CartItem cartItem,HttpSession session) throws SystemException {
        User user = (User) session.getAttribute("user");
        if(user == null) return ResponseMessage.setStatus(ResultCode.UNKNOWN_ERROR);
        cartItem.setBuyerID(user.getUserID());
        buyerOperationService.insertToShoppingCart(cartItem);
        return ResponseMessage.setStatus(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/deleteShoppingCartItem/{commodityID}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseMessage deleteShoppingCartItem(@PathVariable("commodityID") Integer commodityID) {
        buyerOperationService.deleteShoppingCartItem(commodityID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/updateCountForCartItem",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseMessage updateCountForCartItem(@RequestParam(value = "commodityID") Integer commodityID,@RequestParam(value = "count") Integer amount) {
        Map<String,Integer> map = new HashMap<>();
        map.put("commodityID",commodityID);
        map.put("amount",amount);
        buyerOperationService.updateCountForCartItem(map);
        return ResponseMessage.setStatus(ResultCode.SUCCESS);
    }


    @RequestMapping(value = "/queryShoppingCartCommodity/{userID}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage queryShoppingCartCommodity(@PathVariable(value = "userID") Integer userID ) {
        List<CartItem> result = null;
        result = buyerOperationService.queryShoppingCartCommodity(userID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("result",result);
    }


    //清空购物车
    @RequestMapping(value = "/insertIntoFinance",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage insertIntoFinance(@RequestBody FinanceItem[] finance, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) return ResponseMessage.setStatus(ResultCode.UNKNOWN_ERROR);
        buyerOperationService.insertIntoFinance(finance,user.getUserID());
        return ResponseMessage.setStatus(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/queryPurchasedCommoditys",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage queryPurchasedCommoditys(Integer userID) {
        List<FinanceItem> result = null;
        result = buyerOperationService.queryPurchasedCommoditys(userID);
        double total = 0;
        //统计购买的所有商品的价格
        for (FinanceItem fin : result) {
            int amount = fin.getAmount();
            double price = fin.getBoughtPrice();
            total += (amount*price);
        }
        total = (double) Math.round(total*100)/100;
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("result",result).add("total",total);
    }

    @RequestMapping(value = "/queryAllCommodityWithStatus",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage queryAllCommodityWithStatus() {
        List<FinanceItem> result = null;
        result = buyerOperationService.queryAllCommodityWithStatus();
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("result",result);
    }

    @RequestMapping(value = "/queryUnPurchasedCommoditys/{userID}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage queryUnPurchasedCommoditys(@PathVariable("userID") Integer userID) {
        List<FinanceItem> result = null;
        result = buyerOperationService.queryUnPurchasedCommoditys(userID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("result",result);
    }


    @RequestMapping(value = "queryCommodityInfo/{commodityID}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage queryCommodityInfo(@PathVariable("commodityID") Integer commodityID) {
        FinanceItem commodityInfo =  buyerOperationService.queryCommodityInfo(commodityID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("commodityInfo",commodityInfo);
    }



}
