package com.fidnortech.xjx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysDepart;
import com.fidnortech.xjx.system.entity.vo.SysDepartVo;
import com.fidnortech.xjx.system.mapper.SysDepartMapper;
import com.fidnortech.xjx.system.service.SysDepartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 */
@Service
public class SysDepartServiceImpl extends ServiceImpl<SysDepartMapper, SysDepart> implements SysDepartService {



    @Override
    public List<SysDepart> getTree() {

        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();

        wrapper.eq("is_del",0);

        List<SysDepart> list = this.list(wrapper);

        List<SysDepartVo> sysDepartList = new ArrayList<>();
        Map<String, SysDepartVo> map = new HashMap<>();
        for (SysDepart depart : list) {
            SysDepartVo sysDepartVoTemp = new SysDepartVo();
            BeanUtils.copyProperties(depart, sysDepartVoTemp);
            SysDepartVo sysDepartVo = map.get(depart.getParentId() + "");
            if (sysDepartVo == null) {
                sysDepartVoTemp.setChildren(new ArrayList<>());
                map.put(depart.getId() + "", sysDepartVoTemp);
                if (depart.getParentId().equals("0")) {
                    sysDepartList.add(sysDepartVoTemp);
                }
            } else {
                sysDepartVo.getChildren().add(sysDepartVoTemp);
            }
        }
        return getTree(list);
    }


    /*****
     *
     * @return将列表数据转树形数据
     */


    private List<SysDepart> getTree(List<SysDepart> list) {
        List<SysDepart> retList = new ArrayList<SysDepart>();
        for (SysDepart obj : list) {
            //默认设置 parentId null为根节点
            if (obj.getParentId().equals("0")) {
                List<SysDepart> children = getAllChildren(obj.getId(), list);
                if (children.size() > 0) {
                    obj.setChildren(children);
                }
                retList.add(obj);
            }
        }
        return retList;
    }


    /*****
     *
     * @return根据父级id获取所有子节点列表
     */
    public List<SysDepart> getAllChildren(String parentId, List<SysDepart> list) {
        List<SysDepart> retList = new ArrayList<SysDepart>();
        for (SysDepart obj : list) {
            String presentParentId = obj.getParentId();
            if (!presentParentId.equals("0") && presentParentId.equals(parentId)) {
                List<SysDepart> children = this.getAllChildren(obj.getId(), list);
                if (children.size() > 0) {
                    obj.setChildren(children);
                }
                retList.add(obj);
            }
        }
        return retList;
    }




    @Override
    @Cacheable(value = "SysDepart", key = "targetClass + methodName")
    public Map<String, SysDepart> getDepartMap() {
        List<SysDepart> list = this.list();
        Map<String, SysDepart> resultMap = new HashMap<>();
        for (SysDepart sysDepart : list) {
            resultMap.put(sysDepart.getId() + "", sysDepart);
        }
        return resultMap;
    }

    @Override
    public ResponseMessage saveDepart(SysDepart sysDepart) {

        String id = sysDepart.getId();
        QueryWrapper<SysDepart> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", sysDepart.getName());
        if(StringUtils.isNotBlank(id)){
            queryWrapper.ne("id", id);
        }
        List<SysDepart> list = this.list(queryWrapper);
        //处理已经逻辑删除重名的数据

        if (list.size() > 0) {
            //加个循环确保不数组长度大于1条数据
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIsDel() == 0){
                    return ResponseMessage.error("名称重复");
                }
            }

        }


        sysDepart.setIsDel(0);
        return ResponseMessage.success(this.saveOrUpdate(sysDepart));
    }
}
