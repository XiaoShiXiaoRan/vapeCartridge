package com.fidnortech.xjx.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.commodity.controller.CommodityTypeController;
import com.fidnortech.xjx.commodity.entity.Commodity;
import com.fidnortech.xjx.commodity.service.CommodityService;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.utils.MailUtil;
import com.fidnortech.xjx.web.entity.customVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Web/Home")
@Api(tags = "Web主页面")
public class WebController {

    @Autowired
    CommodityTypeController commodityTypeController;

    @Autowired
    CommodityService commodityService;

    @Autowired
    private MailUtil mailUtil;

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

    /**
     * 查询标志性产品 4
     */
    @PostMapping("/getIconicProducts")
    @ApiOperation(value="查询标志性产品")
    @LogRecord(modular = "查询标志性产品",value = "查询")
    public ResponseMessage getIconicProducts(){

        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();

        List<String> ids = Arrays.asList("1739945133106798593","1739945205294964737","1739937282921623553","1739938759392108545");

        queryWrapper.select("id","name","image","heat","url","information");

        queryWrapper.eq("is_del",0);

        queryWrapper.in("id",ids);


        return ResponseMessage.success(commodityService.list(queryWrapper));
    }

    /**
     * 发送请求样品邮件
     * Request Sample Email
     *
     * 1613721920914522113
     */

    @PostMapping("/requestSampleEmail")
    @ApiOperation(value="发送请求样品邮件")
    @LogRecord(modular = "发送请求样品邮件",value = "发送请求样品邮件")
    public ResponseMessage requestSampleEmail(customVo custom){

        /**
         * 发送邮件
         */
        try {
            String text = String.format(mailUtil.getMailCustom(),
                    custom.getEmail(),
                    custom.getFirstname(),
                    custom.getLastname(),
                    custom.getEmail(),
                    custom.getPhone(),
                    custom.getMsg());

            mailUtil.sendMailOneMessage("13576154061xjx@gmail.com", "您有一封来自"+ custom.getEmail() +" 的测试样品请求邮件！", text);
        }catch (Exception e){
            log.info("发送异常:"+e);
            return ResponseMessage.error(e.getMessage());
        }

        /**
         * 发送邮件确认回执
         */
        try {
            String text = String.format(mailUtil.getSuccessfullySent());

            mailUtil.sendMailOneMessage(custom.getEmail(), "您有一封来自 FidNor 的测试样品请求回执邮件！", text);
        }catch (Exception e){
            log.info("发送异常:"+e);
            return ResponseMessage.error(e.getMessage());
        }

        return ResponseMessage.success("发送成功");
    }


}
