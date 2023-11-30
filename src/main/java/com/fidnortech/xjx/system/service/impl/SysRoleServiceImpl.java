package com.fidnortech.xjx.system.service.impl;

import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysRole;
import com.fidnortech.xjx.system.entity.vo.SysRoleUserVo;
import com.fidnortech.xjx.system.mapper.SysRoleMapper;
import com.fidnortech.xjx.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
*
 * @since 2021-09-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public Integer selectMaxSort() {
        return sysRoleMapper.selectMaxSort();
    }

    @Override
    public List<SysRoleUserVo> getTreeRole(String name) {
        return sysRoleMapper.getTreeRole(name);
    }

    @Override
    @Transactional
    public ResponseMessage deleteByIds(String ids) {
        String[] srt = ids.split(",");



        this.removeByIds(Arrays.asList(srt));
        List<SysRole> list = this.list();
        //升序
        list.sort(Comparator.comparingInt(SysRole::getSort));
        for (int i = 0; i < list.size(); i++) {
            SysRole obj = list.get(i);
            obj.setSort(i+1);
        }
        this.saveOrUpdateBatch(list);


        return ResponseMessage.success();
    }

}
