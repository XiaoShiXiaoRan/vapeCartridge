package com.fidnortech.xjx.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fidnortech.xjx.utils.UserUtil;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EntityBase {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id",type = IdType.ID_WORKER_STR )
    private String id;

    @ApiModelProperty(value = "创建人")
//    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
//    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    @ApiModelProperty(value = "修改人")
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    @ApiModelProperty(value = "删除标识 0：否，1：是")
//    @TableField(fill = FieldFill.INSERT)
    private Integer isDel;

    @ApiModelProperty(value = "当前用户", name = "currentUser",hidden = true)
    private transient UserVO currentUser;

    @JSONField( serialize = false)
    public UserVO getCurrentUser() {
        if (this.currentUser == null) {
            this.currentUser = UserUtil.getUser();
        }
        return this.currentUser;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        if(isDel==0){
            isDel = 0;
        }
        this.isDel = isDel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EntityBase){
            if(((EntityBase) obj).getId() == this.id)return true;
        }
        return false;
    }
}
