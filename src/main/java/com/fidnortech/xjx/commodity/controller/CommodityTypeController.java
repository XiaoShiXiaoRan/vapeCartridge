package com.fidnortech.xjx.commodity.controller;


import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.commodity.entity.CommodityType;
import com.fidnortech.xjx.commodity.service.CommodityTypeService;
import com.fidnortech.xjx.common.ResponseMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.fidnortech.xjx.base.BaseController;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xjx
 * @since 2023-12-06
 */
@RestController
@RequestMapping("/commodityType")
public class CommodityTypeController extends BaseController {

    @Autowired
    private CommodityTypeService commodityTypeService;

    @PostMapping(value = "/getCommodityTypeList")
    @ApiOperation(value="查询商品类别")
    @LogRecord(modular = "查询商品类别",value = "查询")
    public ResponseMessage getCommodityTypeList(){

        return ResponseMessage.success(commodityTypeService.list());

    }

    @PostMapping(value = "/saveCommodityType")
    @ApiOperation(value="保存商品类别")
    @LogRecord(modular = "保存商品类别",value = "保存")
    public ResponseMessage saveCommodityType(CommodityType commodityType){

        return ResponseMessage.success(commodityTypeService.saveOrUpdate(commodityType));

    }

}
