package com.fidnortech.xjx.commodity.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.article.entity.NewsArticle;
import com.fidnortech.xjx.commodity.entity.Commodity;
import com.fidnortech.xjx.commodity.entity.CommodtyVo;
import com.fidnortech.xjx.commodity.service.CommodityService;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.utils.PageData;
import org.apache.commons.lang3.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.fidnortech.xjx.base.BaseController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xjx
 * @since 2023-12-06
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    @Autowired
    private CommodityService commodityService;

    @PostMapping(value = "/getCommodityListPage")
    @ApiOperation(value="获取商品列表分页")
    @LogRecord(modular = "文章管理",value = "查询")
    public ResponseMessage getArticleListPage(String name,String typeId){

        Page<CommodtyVo> page = this.getPagination();

        Page<CommodtyVo> commodityPage = commodityService.getPage(page,name,typeId);

        return ResponseMessage.success(commodityPage);
    }

    @PostMapping(value = "/getByIdCommodity")
    @ApiOperation(value="根据商品id查询")
    @LogRecord(modular = "商品管理",value = "查询")
    public ResponseMessage getByIdCommodity(String id){
        return ResponseMessage.success(commodityService.getById(id));
    }

    @PostMapping(value = "/saveCommodity")
    @ApiOperation(value="保存商品")
    @LogRecord(modular = "商品管理",value = "保存")
    public ResponseMessage saveCommodity(MultipartHttpServletRequest request) throws IOException {



        MultipartFile image =  request.getFile("image");

        Commodity commodity = new Commodity();

        String id = request.getParameter("id");
        Integer typeId = Integer.valueOf(request.getParameter("typeId"));
        String information = request.getParameter("information");
        String name = request.getParameter("name");



        if (StringUtils.isBlank(id)){
            //商品名不能重复
            List<Commodity> list = commodityService.list();

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(name)){
                    return ResponseMessage.error("保存失败，文章标题不能重复请重新输入。");
                }
            }

        }
        commodity.setTypeId(typeId);
        commodity.setInformation(information);
        commodity.setName(name);

        commodity.setIsDel(0);

        if (image != null){
            byte[] img = image.getBytes();

            commodity.setImage(img);
        }

        try {
            commodityService.saveOrUpdate(commodity);
        }catch (Exception e){
            return ResponseMessage.error(e.getMessage());
        }

        return ResponseMessage.success("保存成功");
    }


    @PostMapping(value = "/deleteCommodity")
    @ApiOperation(value="删除商品")
    @LogRecord(modular = "文章管理",value = "删除")
    public ResponseMessage deleteCommodity(String ids){
        String[] str = ids.split(",");

        boolean ok = false;

        for (int i = 0; i < str.length; i++) {

            Commodity data = commodityService.getById(str[i]);

            data.setIsDel(1);

            ok = commodityService.saveOrUpdate(data);
        }

        String message;

        if (ok){
            message = "删除成功";
        }else {
            message = "删除失败";
        }

        return ResponseMessage.success(message);
    }

}



















