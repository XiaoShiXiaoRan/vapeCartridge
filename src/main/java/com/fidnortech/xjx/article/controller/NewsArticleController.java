package com.fidnortech.xjx.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.article.entity.NewsArticle;
import com.fidnortech.xjx.article.service.NewsArticleService;
import com.fidnortech.xjx.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2023-12-01
 */
@Slf4j
@Controller
@RestController
@RequestMapping("/article")
@Api(tags = "数据管理-文章管理")
public class NewsArticleController extends BaseController {

    @Autowired
    private NewsArticleService newsArticleService;

    @PostMapping(value = "/getArticleListPage")
    @ApiOperation(value="获取角色列表分页")
    @LogRecord(modular = "文章管理",value = "查询")
    public ResponseMessage getArticleListPage(String title){


        Page<NewsArticle> page = this.getPagination();

        QueryWrapper<NewsArticle> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id","title","author","release_time");

        queryWrapper.eq("is_del", 0);
        queryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        queryWrapper.orderByDesc("release_time");


        IPage<NewsArticle> pageData = newsArticleService.page(page,queryWrapper);

        return ResponseMessage.success(pageData);
    }

}
