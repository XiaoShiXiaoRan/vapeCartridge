package com.fidnortech.xjx.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.article.entity.NewsArticle;
import com.fidnortech.xjx.article.service.NewsArticleService;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.utils.DateUtil;
import com.fidnortech.xjx.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.fidnortech.xjx.base.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 根据文章id查询文章信息
     */
    @PostMapping(value = "/getByIdArticle")
    @ApiOperation(value="根据id获取文章信息")
    @LogRecord(modular = "文章管理",value = "查询")
    public ResponseMessage getByIdArticle(String id){

        NewsArticle newsArticle = newsArticleService.getById(id);

        return ResponseMessage.success(newsArticle);
    }

    /**
     * 根据文章title查询文章信息
     */
    @PostMapping(value = "/getByTitleArticle")
    @ApiOperation(value="根据title获取文章信息")
    @LogRecord(modular = "文章管理",value = "查询")
    public ResponseMessage getByTitleArticle(String title){

        QueryWrapper<NewsArticle> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("title",title);

        NewsArticle newsArticle = newsArticleService.getOne(queryWrapper);

        return ResponseMessage.success(newsArticle);
    }


    /**
     * 新增OR修改文章
     */
    @PostMapping(value = "/saveArticle")
    @ApiOperation(value="保存文章")
    @LogRecord(modular = "文章管理",value = "保存")
    public ResponseMessage saveArticle(NewsArticle newsArticle){

        //增加逻辑判断 title 不能重复

        List<NewsArticle> list = newsArticleService.list();

        for (int i = 0; i < list.size(); i++) {
            NewsArticle item = list.get(i);

            if (item.getTitle().equals(newsArticle.getTitle())){
                return ResponseMessage.error("保存失败，文章标题不能重复请重新输入。");
            }
        }

        newsArticle.setIsDel(0);

        newsArticle.setReleaseTime(DateUtil.getTodayDate());

        newsArticle.setAuthor(UserUtil.getUser().getUserName());

        boolean ok = newsArticleService.saveOrUpdate(newsArticle);

        String message;

        if (ok){
            message = "保存成功";
        }else {
            message = "保存失败";
        }


        return ResponseMessage.success(message);
    }

    @PostMapping(value = "/deleteArticle")
    @ApiOperation(value="删除文章")
    @LogRecord(modular = "文章管理",value = "删除")
    public ResponseMessage deleteArticle(String ids){
        String[] str = ids.split(",");

        boolean ok = false;

        for (int i = 0; i < str.length; i++) {

            NewsArticle data = newsArticleService.getById(str[i]);

            data.setIsDel(1);

            ok = newsArticleService.saveOrUpdate(data);
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
