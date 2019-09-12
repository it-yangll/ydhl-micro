package com.ydhl.micro.api.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName SearchDTO
 * @Description 查询DTO类
 * @Author yangll
 * @Date 2019-9-10 15:45:09
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "公共的查询字段")
public class SearchDTO {

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private int pageNum = 1;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    private int pageSize = 10;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段（数据库字段）")
    private String sortBy;

    /**
     * 正序倒序
     */
    @ApiModelProperty(value = "升序asc,降序desc")
    private String orderBy;

    /**
     * 是否分页
     */
    @ApiModelProperty(value = "是否分页")
    private Boolean pageable = true;

}
