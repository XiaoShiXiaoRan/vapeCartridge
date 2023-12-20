package com.fidnortech.xjx.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.base.BaseController;
import com.fidnortech.xjx.base.UserVO;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysRole;
import com.fidnortech.xjx.system.entity.SysUser;
import com.fidnortech.xjx.system.entity.SysUserRole;
import com.fidnortech.xjx.system.entity.vo.UserRoleVo;
import com.fidnortech.xjx.system.service.SysRoleService;
import com.fidnortech.xjx.system.service.SysUserRoleService;
import com.fidnortech.xjx.system.service.SysUserService;
import com.fidnortech.xjx.utils.DateUtil;
import com.fidnortech.xjx.utils.MailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
*
 * @since 2021-08-11
 */
@Slf4j
@Controller
@RequestMapping("/system/sysUser")
@Api(tags = "运维管理-用户管理")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="获取用户列表")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getList() {
        Page page = this.getPagination();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();

        queryWrapper.in("is_del",0);



        IPage<SysUser> departmentlist = sysUserService.page(page, queryWrapper);



        return ResponseMessage.success(departmentlist);

    }

    @RequestMapping(value = "/getUserListPage", method = RequestMethod.POST)
    @ResponseBody
    @LogRecord(modular = "运维管理",value = "查询")
    @ApiOperation(value="获取用户列表分页")
    public ResponseMessage getUserListPage(String condition,Integer current,Integer size) {
        Page page  =this.getPagination();
//
//        QueryWrapper wrapper = new QueryWrapper();
//
//        wrapper.in("is_del",0);
//
//        wrapper.like(StringUtils.isNotBlank(condition),"user_name",condition).or(true).like(StringUtils.isNotBlank(condition),"name",condition);
//
//        IPage<SysUser> iPage = sysUserService.page(page, wrapper);

        //总条数
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("is_del",0);

        wrapper.like(StringUtils.isNotBlank(condition),"user_name",condition).or(true).like(StringUtils.isNotBlank(condition),"name",condition);

        Integer Total = sysUserService.count(wrapper);

        List<UserRoleVo> UserRolPage = sysUserService.getUserRolePage(condition,(page.getCurrent()-1)*size,size);

        IPage<UserRoleVo> userRoleVoIPage = new Page<>();
        userRoleVoIPage.setRecords(UserRolPage);
        userRoleVoIPage.setTotal(Total);
        userRoleVoIPage.setCurrent(current);
        userRoleVoIPage.setSize(size);

        return ResponseMessage.success(userRoleVoIPage);


    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="保存用户")
    @LogRecord(modular = "运维管理",value = "保存")
    public ResponseMessage save(SysUser sysUser
    ) {
//        SysUser sysUser = new SysUser();
//        sysUser.setId(id);
//        sysUser.setUserName(username);
//        sysUser.setCreateTime(createTime);

//        sysUser.setDepartId(departId);

        //校验账号重复
        QueryWrapper<SysUser> queryNameWrapper = new QueryWrapper();
        queryNameWrapper.eq("user_name", sysUser.getUserName());
        queryNameWrapper.eq("is_del",0);
        List<SysUser> queryNameList = sysUserService.list(queryNameWrapper);

        if (sysUser.getId() != null) {
            if (CollectionUtils.isNotEmpty(queryNameList)) {
                if (!queryNameList.get(0).getId().equals(sysUser.getId())) {
                    return ResponseMessage.error("用户名重复，请重新输入");
                }
            }
            SysUser queryOjb = sysUserService.getById(sysUser.getId());
            if (queryOjb == null) {
                sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            } else {
                sysUser.setPassword(null);
            }
        } else {
            if (CollectionUtils.isNotEmpty(queryNameList)) {
                return ResponseMessage.error("用户名重复，请重新输入");
            }
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
//        sysUser.setName(name);
        sysUser.setIsDel(0);
//        sysUser.setAddress(addr);
//        sysUser.setPhone(phone);
//        sysUser.setSex(sex);
//        sysUser.setBirthDay(birthDay);
//        sysUser.setEmail(email);
//        sysUser.setRoleId(roleId);
        sysUser.setCreateTime(DateUtil.getTodayDate());
//        sysUser.setUpdateTime(DateUtil.getTodayDate());
        sysUserService.saveOrUpdate(sysUser);

//        String[] str = roles.split(",");
//        QueryWrapper<SysUserRole> emptyWrapper = new QueryWrapper();
//        emptyWrapper.eq("user_id", sysUser.getId());
//        sysUserRoleService.remove(emptyWrapper);
//        for (String s : str) {
//            SysUserRole sysUserRole = new SysUserRole();
//            sysUserRole.setUserId(sysUser.getId());
//            sysUserRole.setRoleId(s);
//            sysUserRoleService.save(sysUserRole);
//        }
        return ResponseMessage.success("用户保存成功");
    }


    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="修改密码")
    @LogRecord(modular = "个人中心",value = "保存")
    public ResponseMessage changepassword(
            @RequestParam(value ="id") String id,
            @RequestParam(value = "rawPassword") String rawPassword,
            @RequestParam(value = "newPassword") String newPassword
    ) {
            SysUser queryOjb = sysUserService.getById(id);
            if (queryOjb == null) {
                //旧密码验证是否正确
                if (passwordEncoder.matches(rawPassword,queryOjb.getPassword())){
                    queryOjb.setPassword(newPassword);
                    sysUserService.saveOrUpdate(queryOjb);
                }else {
                    return ResponseMessage.success("该用户原密码错误，请重新输入。");
                }

            } else {
                return ResponseMessage.success("该用户不存在");
            }

        return ResponseMessage.success("密码修改成功，请重新登录!");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="删除用户")
    @LogRecord(modular = "运维管理",value = "删除")
    public ResponseMessage delete(String ids) {
        String[] str = ids.split(",");
//        List<Long> longIds = CommonUtil.StringArray2LongArray(str);
//        sysUserService.removeByIds(Arrays.asList(str));
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.in("user_id", str);
//        sysUserRoleService.remove(queryWrapper);
        //逻辑删除
//        SysUser user = new SysUser();
//        user.setId(ids);
//        user.setIsDel(1);

        final boolean b = sysUserService.removeByIds(Arrays.asList(str));
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("user_id", ids);
//        sysUserRoleService.remove(queryWrapper);

        return ResponseMessage.success("用户删除成功");
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="根据id查询")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getById(String id) {
        SysUser user = sysUserService.getById(id);

        SysRole role = sysRoleService.getById(user.getRoleId());

        UserRoleVo userRoleVo = new UserRoleVo();

        userRoleVo.setId(user.getId());
        userRoleVo.setUserName(user.getUserName());
        userRoleVo.setName(user.getName());
        userRoleVo.setRoleId(user.getRoleId());
        userRoleVo.setRoleName(role.getName());
        userRoleVo.setAddress(user.getAddress());
        userRoleVo.setPhone(user.getPhone());
        userRoleVo.setEmail(user.getEmail());



        return ResponseMessage.success(userRoleVo);
    }

    @RequestMapping(value = "/goGmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage goGmail(){

        //查询所有普通用户邮箱地址
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("user_name","email");

        queryWrapper.eq("role_id","1734157465534156801");

        List<SysUser> userList = sysUserService.list(queryWrapper);

        for (int i = 0; i < userList.size(); i++) {

            SysUser item = userList.get(i);

            String text = String.format(mailUtil.getMailText(),
                    "fidnor.com",
                    String.format(MailUtil.imMail, "XiaoRan"),
                    "XiaoRan",
                    "文字描述",
                    "自定义内容，谷歌邮箱发送  群发退回邮件测试1。",
                    "FidNor");

            mailUtil.sendMailOneMessage(item.getEmail(), item.getUserName()+"您有一封来自 fidnor.com 的邮件！", text);

        }

        return ResponseMessage.success("发送成功");
    }

    @RequestMapping(value = "/goAllGmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage goAllGmail(){

        //查询所有普通用户邮箱地址
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("user_name","email");

        queryWrapper.eq("role_id","1734157465534156801");

        List<SysUser> userList = sysUserService.list(queryWrapper);

        List<String> mailList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {

            SysUser item = userList.get(i);

            mailList.add(item.getEmail());

        }

        String text = String.format(mailUtil.getMailText(),
                "fidnor.com",
                String.format(MailUtil.imMail, "XiaoRan"),
                "XiaoRan",
                "文字描述",
                "自定义内容，群发邮件测试。",
                "FidNor");

        mailUtil.sendMailMessage(mailList, "您有一封来自 fidnor.com 的邮件！", text);

        return ResponseMessage.success("发送成功");
    }
}
