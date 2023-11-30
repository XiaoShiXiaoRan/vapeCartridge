package com.fidnortech.xjx.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseController {
    public BaseController() {
    }


    public <T> Page<T> getPagination() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request == null) {
            throw new RuntimeException("请检查request对象是否注入!");
        } else {
            String pageSize = request.getParameter("size");
            if (StringUtils.isEmpty(pageSize)) {
                pageSize = "10";
            }

            String currentPage = request.getParameter("current");
            if (StringUtils.isEmpty(currentPage)) {
                currentPage = "1";
            }

            Assert.isTrue(StringUtils.isNumeric(pageSize), "分页参数：PAGE_SIZE参数不是数字");
            Assert.isTrue(StringUtils.isNumeric(currentPage), "分页参数：CURRENT_PAGE参数不算数字");
            int size = Integer.valueOf(pageSize);
            int current = Integer.valueOf(currentPage);
            String isAsc = request.getParameter("isAsc");
            Page  page = new Page(current, size);
            return page;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
    /**
     * 获取当前登录用户信息
     * @return
     */
    protected UserVO getUser(){
        return UserUtil.getUser();
    }


}
