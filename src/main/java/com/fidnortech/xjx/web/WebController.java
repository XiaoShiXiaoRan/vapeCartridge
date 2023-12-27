package com.fidnortech.xjx.web;


import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.commodity.controller.CommodityTypeController;
import com.fidnortech.xjx.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/Web/Home")
@Api(tags = "Web主页面")
public class WebController {

    @Autowired
    CommodityTypeController controller;
    @PostMapping(value = "/getCommodityTypeList")
    @ApiOperation(value="查询商品类别")
    @LogRecord(modular = "查询商品类别",value = "查询")
    public ResponseMessage getCommodityTypeList(){

        return controller.getCommodityTypeList();

    }
}
