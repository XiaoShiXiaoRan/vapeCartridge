package com.fidnortech.xjx.commodity.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.commodity.entity.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fidnortech.xjx.commodity.entity.CommodtyVo;
import com.fidnortech.xjx.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xjx
 * @since 2023-12-06
 */
public interface CommodityMapper extends BaseMapper<Commodity> {

    List<CommodtyVo> getPage(@Param("current") Long current,@Param("size") Long size, @Param("name") String name, @Param("typeId") String typeId);

}
