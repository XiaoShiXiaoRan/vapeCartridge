package com.fidnortech.xjx.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjx
 * @since 2023-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_news_article")
@ApiModel(value="NewsArticle对象", description="")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章id")
    @TableId(value = "id",type = IdType.ID_WORKER_STR )
    private String id;

    @ApiModelProperty(value = "文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "文章作者")
    @TableField("author")
    private String author;

    @ApiModelProperty(value = "发布时间")
    @TableField("release_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date releaseTime;

    @ApiModelProperty(value = "文章内容")
    @TableField("article_data")
    private String articleData;

    @ApiModelProperty(value = "删除标识 0：否，1：是")
    @TableField("is_del")
    private Integer isDel;


}
