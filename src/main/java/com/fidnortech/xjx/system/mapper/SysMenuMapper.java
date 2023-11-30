package com.fidnortech.xjx.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fidnortech.xjx.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
* @Entity com..SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getMenuByUserId(@Param("id") String id);

    List<SysMenu> menuApplyList(@Param("userId") String userId);

    /**
     * 用户申请通过菜单列表
     * @author Administrator
     * @date 2022/9/21 17:12
     * @version 1.0
     */
    List<SysMenu> userMenuList(@Param("userId") String userId);

    List<SysMenu> getRootMenu();

}
