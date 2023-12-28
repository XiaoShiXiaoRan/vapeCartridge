package com.fidnortech.xjx.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.commodity.controller.CommodityTypeController;
import com.fidnortech.xjx.commodity.entity.Commodity;
import com.fidnortech.xjx.commodity.service.CommodityService;
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
    CommodityTypeController commodityTypeController;

    @Autowired
    CommodityService commodityService;

    @PostMapping(value = "/getCommodityTypeList")
    @ApiOperation(value="查询商品类别")
    @LogRecord(modular = "查询商品类别",value = "查询")
    public ResponseMessage getCommodityTypeList(){

        return commodityTypeController.getCommodityTypeList();

    }

    /**
     * 根据商品类型id 查询商品list 根据热度排序
     */
    @PostMapping("/getListByTypeId")
    @ApiOperation(value="根据商品类型id 查询商品list 根据热度排序")
    @LogRecord(modular = "查询商品list",value = "查询")
    public ResponseMessage getListByTypeId(String typeId){

        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id","name","image","heat","url");

        queryWrapper.eq("is_del",0);

        queryWrapper.eq("type_id",typeId);

        queryWrapper.orderByDesc("heat");

        queryWrapper.last("LIMIT 6");


        return ResponseMessage.success(commodityService.list(queryWrapper));
    }

}
