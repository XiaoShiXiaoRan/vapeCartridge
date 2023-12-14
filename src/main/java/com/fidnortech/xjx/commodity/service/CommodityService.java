package com.fidnortech.xjx.commodity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.commodity.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fidnortech.xjx.commodity.entity.CommodtyVo;
import com.fidnortech.xjx.utils.PageData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xjx
 * @since 2023-12-06
 */
public interface CommodityService extends IService<Commodity> {

    Page<CommodtyVo> getPage(Page page, String name,String typeId);

}
