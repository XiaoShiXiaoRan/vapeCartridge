package com.fidnortech.xjx.commodity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.commodity.entity.Commodity;
import com.fidnortech.xjx.commodity.entity.CommodtyVo;
import com.fidnortech.xjx.commodity.mapper.CommodityMapper;
import com.fidnortech.xjx.commodity.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fidnortech.xjx.utils.PageData;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xjx
 * @since 2023-12-06
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Override
    public Page<CommodtyVo> getPage(Page page, String name,String typeId) {

        //总数
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("is_del",0);

        Integer count = baseMapper.selectCount(queryWrapper);

        Long current = page.getCurrent();

        Long size = page.getSize();

        List<CommodtyVo> commodityList = baseMapper.getPage(current,size,name,typeId);


        Page<CommodtyVo> pageData = new Page<>();

        pageData.setTotal(count);

        pageData.setCurrent(current);

        pageData.setSize(size);

        pageData.setRecords(commodityList);

        return pageData;
    }
}
