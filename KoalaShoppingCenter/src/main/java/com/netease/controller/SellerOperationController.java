package com.netease.controller;

import com.netease.meta.Commodity;
import com.netease.meta.ResponseMessage;
import com.netease.meta.ResultCode;
import com.netease.service.SellerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion
 */
@Controller
@RequestMapping("/seller")
public class SellerOperationController {

    @Autowired
    private SellerOperationService sellerOperationService;

    @RequestMapping(value = "/queryAllCommoditysBySeller/{userID}")
    @ResponseBody
    public ResponseMessage queryAllCommoditysBySeller(@PathVariable("userID") Integer userID) {
        List<Commodity> res = sellerOperationService.queryAllCommoditysBySeller(userID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("result",res);
    }

    @RequestMapping(value = "/insertCommodity",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseMessage insertCommodity(Commodity commodity) {
        commodity.setSoldAmount(0);
        Integer commodityID = sellerOperationService.insertCommodity(commodity);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("commodityID",commodityID);
    }

    @RequestMapping(value = "/queryCommodityInfo/{commodityID}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage queryCommodityInfo(@PathVariable("commodityID") Integer commodityID) {
        Commodity commodityInfo = sellerOperationService.queryCommodityInfo(commodityID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("commodityInfo",commodityInfo);
    }

    @RequestMapping(value = "/updateCommodityInfo",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseMessage updateCommodityInfo(Commodity commodity) {
        Integer commodityID = sellerOperationService.updateCommodityInfo(commodity);
        return ResponseMessage.setStatus(ResultCode.SUCCESS).add("commodityID",commodityID);
    }


    @RequestMapping("/uploadImg")
    @ResponseBody
    public ResponseMessage uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String originFileName = file.getOriginalFilename() ;
        String newFileName = null;
        if(file!=null && originFileName!=null && originFileName.length()>0){
            String pic_path= request.getServletContext().getRealPath("/")+"/static/image/";
            newFileName = UUID.randomUUID()+originFileName.substring(originFileName.lastIndexOf("."));  //新的文件名
            File newFile = new File(pic_path+newFileName);
            file.transferTo(newFile);
            return ResponseMessage.setStatus(ResultCode.SUCCESS).add("imgUrl","../static/image/"+newFileName);
        }
        return ResponseMessage.setStatus(ResultCode.DATA_FORMAT_ERROR);

    }

    @RequestMapping(value = "/deleteUnSellCommodity/{commodityID}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseMessage deleteUnSellCommodity(@PathVariable("commodityID") Integer commodityID) {
        sellerOperationService.deleteUnSellCommodity(commodityID);
        return ResponseMessage.setStatus(ResultCode.SUCCESS);
    }










}
